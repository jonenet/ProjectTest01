package com.example.annotationdemo;


import com.example.annotationdemo.anno.TestAnnotation;
import com.example.annotationdemo.processer.AnnoUseClass;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.processing.AbstractProcessor;

@TestAnnotation(id = 1, msg = "test")
public class TestClass {

    @TestAnnotation(id = 3)
    int index;

    public static void main(String[] args) {
        try {
            AbstractProcessor processor;
            Class<?> annoClass = Class.forName("com.example.annotationdemo.processer.AnnoUseClass");
            Constructor<?> constructor = annoClass.getConstructor();

            Object o1 = constructor.newInstance();

            Object o = annoClass.newInstance();

            Method cal = annoClass.getMethod("cal", int.class, int.class);
            Object invoke = cal.invoke(o1, 1, 2);

            Field age = annoClass.getDeclaredField("age");
            age.setAccessible(true);
            age.set(o, 2);


            System.out.println("age = " + ((AnnoUseClass) o).getAge());
            System.out.println(invoke);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        testSelf();
    }

    public static void testSelf() {
        boolean annotationPresent = TestClass.class.isAnnotationPresent(TestAnnotation.class);
        if (annotationPresent) {
            TestAnnotation annotation = TestClass.class.getAnnotation(TestAnnotation.class);
//            System.out.println(annotation.id());
//            System.out.println(annotation.msg());

            try {
                Field field = TestClass.class.getDeclaredField("index");
                TestAnnotation annotation1 = field.getAnnotation(TestAnnotation.class);
//                int id = annotation1.id();
//                System.out.println(id);
                System.out.println(field.getName());

                Method testAnno = TestClass.class.getDeclaredMethod("testAnno");
//                Annotation[] annotations = testAnno.getAnnotations();
//                for (Annotation annotation2 : annotations) {
//                    System.out.println(annotation2.annotationType().getSimpleName());
//                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @TestAnnotation
    public void testAnno() {

    }
}
