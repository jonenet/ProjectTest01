package com.example.testannotationprocesser.processer;

import com.example.testannotation.anno.TestAnnoAware;
import com.example.testannotationprocesser.Type;
import com.example.testannotationprocesser.utils.Constants;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * Created by ex-zhoulai on 2018/4/28.
 * 
 */

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedOptions("testOption")
@SupportedAnnotationTypes("com.example.testannotation.anno.TestAnnoAware")
public class TestAutoProcesser extends AbstractProcessor {


    private Messager messager;
    private Filer filer;
    private Elements elementUtils;
    private Types typeUtils;
    private static final String SUFFIX_AUTOWIRED = "$$Router$$Autowired";


    /**
     * Contain field need autowired and his super class.
     */
    private Map<TypeElement, List<Element>> parentAndChild = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();


        Map<String, String> options = processingEnv.getOptions();
        if (!options.isEmpty()) {
            String testOption = options.get("testOption");
            messager.printMessage(Diagnostic.Kind.NOTE, "" + testOption);
        }
        messager.printMessage(Diagnostic.Kind.NOTE, "init");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        //1.拿到所有的注解并遍历
        Set<? extends Element> routerNodes = roundEnv.getElementsAnnotatedWith(TestAnnoAware.class);
        if (null != routerNodes) {
            messager.printMessage(Diagnostic.Kind.NOTE, "start process");
            //获取所有的TestAnnoAware注解
            for (Element element : routerNodes) {
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                messager.printMessage(Diagnostic.Kind.NOTE, "className: " + element.getSimpleName().toString());
                try {
                    messager.printMessage(Diagnostic.Kind.NOTE, enclosingElement.getQualifiedName());
                    if (element.getModifiers().contains(Modifier.PRIVATE)) {
                        throw new IllegalAccessException("The autowired fields CAN NOT BE 'private'!!! please check field ["
                                + element.getSimpleName() + "] in class [" + enclosingElement.getQualifiedName() + "]");
                    }
                    if (parentAndChild.containsKey(enclosingElement)) {
                        parentAndChild.get(enclosingElement).add(element);
                    } else {
                        List<Element> childs = new ArrayList<>();
                        childs.add(element);
                        parentAndChild.put(enclosingElement, childs);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            TypeElement type_ISyringe = elementUtils.getTypeElement(Constants.ISYRINGE);
            TypeMirror activityTm = elementUtils.getTypeElement(Constants.ACTIVITY).asType();
            ParameterSpec objectParamSpec = ParameterSpec.builder(TypeName.OBJECT, "target").build();

            if (!MapUtils.isEmpty(parentAndChild)) {
                MethodSpec.Builder injectMethod = MethodSpec.methodBuilder("inject")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        //等于添加了一个Object类型的参数
                        .addParameter(objectParamSpec);

                for (Map.Entry<TypeElement, List<Element>> entry : parentAndChild.entrySet()) {
                    TypeElement parent = entry.getKey();
                    List<Element> value = entry.getValue();
                    String name = parent.getQualifiedName().toString();
                    messager.printMessage(Diagnostic.Kind.NOTE, name);

                    //包名
                    String packageName = name.substring(0, name.lastIndexOf("."));
                    //类名
                    String fileName = parent.getSimpleName() + SUFFIX_AUTOWIRED;
                    messager.printMessage(Diagnostic.Kind.NOTE, fileName);
                    //////////////////////////
                    //生成类
                    TypeSpec.Builder classBuild = TypeSpec.classBuilder(fileName)
                            .addSuperinterface(ClassName.get(type_ISyringe))
                            .addModifiers(Modifier.PUBLIC);

//                       ISyringe substitute = (ISyringe) target;
                    injectMethod.addStatement("$T substitute = ($T)target", ClassName.get(parent), ClassName.get(parent));

                    //这里是一个类中所有的成员变量
                    for (Element element1 : value) {
                        TestAnnoAware annotation = element1.getAnnotation(TestAnnoAware.class);
                        String fieldName = element1.getSimpleName().toString();
                        String originalValue = "substitute." + fieldName;//substitute.name  substitute就是跳转后的activity
                        String statment = "substitute." + fieldName + " = substitute.";//substitute.name = substitute.getIntent().getXxx(...)

                        //如果是Activity
                        if (typeUtils.isSubtype(parent.asType(), activityTm)) {
                            statment += "getIntent().";
                        }
                        //getXxx("name");
                        statment = buildStatement(originalValue, statment, typeExchange(element1), true);
                        injectMethod.addStatement(statment, StringUtils.isEmpty(annotation.value()) ? fieldName : annotation.value());
                    }
                    classBuild.addMethod(injectMethod.build()).build();
//                    String packageName = processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();

                    try {
                        JavaFile.builder(packageName, classBuild.build()).build().writeTo(filer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // 在gradle的控制台打印信息,注解上面的类名
            }


//                TypeMirror activityType = elementUtils.getTypeElement("android.app.Activity").asType();
//                TypeMirror typeMirror = element.asType();
//                TestAnnoAware annotation = element.getAnnotation(TestAnnoAware.class);
//
//                //如果是activity类型
//                if (typeUtils.isSubtype(typeMirror, activityType)) {
//                    String value = annotation.value();
//                    messager.printMessage(Diagnostic.Kind.NOTE, value);
//                }


            // 创建main方法
//            MethodSpec main = MethodSpec.methodBuilder("main")
//                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                    .returns(void.class)
//                    .addParameter(String[].class, "args")
//                    .addStatement("$T.out.println($S)", System.class, "自动创建的")
//                    .addStatement("")
//                    .addJavadoc("ccccc")
//                    .build();
//
//            // 创建HelloWorld类
//            TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
//                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                    .addMethod(main)
//                    .build();
//            try {
//                JavaFile javaFile = JavaFile.builder(packageName, helloWorld)//
//                        .addFileComment(" This codes are generated automatically. Do not modify!")
//                        .build();
//                javaFile.writeTo(filer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return true;
        }


        return false;
    }


    /**
     * @param originalValue bundleKey in the bundle of Intent
     * @param statement     original statement
     * @param type          type of data in the  bundle
     * @param isActivity    true as Activity, false as Fragment/Fragment_V4
     * @return statement
     */
    private String buildStatement(String originalValue, String statement, int type, boolean isActivity) {

        //Activity.getIntent().getXXExtra(); Fragment.getIntent().getXX();

        if (type == Type.BOOLEAN.ordinal()) {
            statement += (isActivity ? ("getBooleanExtra($S, " + originalValue + ")") : ("getBoolean($S)"));
        } else if (type == Type.BYTE.ordinal()) {
            statement += (isActivity ? ("getByteExtra($S, " + originalValue + ")") : ("getByte($S)"));
        } else if (type == Type.SHORT.ordinal()) {
            statement += (isActivity ? ("getShortExtra($S, " + originalValue + ")") : ("getShort($S)"));
        } else if (type == Type.INT.ordinal()) {
            statement += (isActivity ? ("getIntExtra($S, " + originalValue + ")") : ("getInt($S)"));
        } else if (type == Type.LONG.ordinal()) {
            statement += (isActivity ? ("getLongExtra($S, " + originalValue + ")") : ("getLong($S)"));
        } else if (type == Type.CHAR.ordinal()) {
            statement += (isActivity ? ("getCharExtra($S, " + originalValue + ")") : ("getChar($S)"));
        } else if (type == Type.FLOAT.ordinal()) {
            statement += (isActivity ? ("getFloatExtra($S, " + originalValue + ")") : ("getFloat($S)"));
        } else if (type == Type.DOUBLE.ordinal()) {
            statement += (isActivity ? ("getDoubleExtra($S, " + originalValue + ")") : ("getDouble($S)"));
        } else if (type == Type.STRING.ordinal()) {
            statement += (isActivity ? ("getStringExtra($S)") : ("getString($S)"));
        } else if (type == Type.PARCELABLE.ordinal()) {
            statement += (isActivity ? ("getParcelableExtra($S)") : ("getParcelable($S)"));
        } else if (type == Type.OBJECT.ordinal()) {
            statement = "jsonService.parseObject(substitute." +
                    (isActivity ? "getIntent()." : "getArguments().") +
                    (isActivity ? "getStringExtra($S)" : "getString($S)") + ", $T.class)";
        }

        return statement;
    }

    /**
     * Diagnostics out the true java type
     *
     * @param element Raw type
     * @return Type class of java
     */
    public int typeExchange(Element element) {
        TypeMirror typeMirror = element.asType();

        // Primitive
        if (typeMirror.getKind().isPrimitive()) {
            return element.asType().getKind().ordinal();
        }

        switch (typeMirror.toString()) {
            case Constants.BYTE:
                return Type.BYTE.ordinal();
            case Constants.SHORT:
                return Type.SHORT.ordinal();
            case Constants.INTEGER:
                return Type.INT.ordinal();
            case Constants.LONG:
                return Type.LONG.ordinal();
            case Constants.FLOAT:
                return Type.FLOAT.ordinal();
            case Constants.DOUBEL:
                return Type.DOUBLE.ordinal();
            case Constants.BOOLEAN:
                return Type.BOOLEAN.ordinal();
            case Constants.STRING:
                return Type.STRING.ordinal();
            default:    // Other side, maybe the PARCELABLE or OBJECT.
//                if (types.isSubtype(typeMirror, parcelableType)) {  // PARCELABLE
//                    return Type.PARCELABLE.ordinal();
//                } else {    // For others
                return Type.OBJECT.ordinal();
//                }
        }
    }


}
