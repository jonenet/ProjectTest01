package com.example.annotationdemo;


import com.example.annotationdemo.anno.TestAnnotation;
import com.example.annotationdemo.processer.AnnoUseClass;
import com.example.annotationdemo.test.CloneTestClass;
import com.example.annotationdemo.test.bwl.Caretaker;
import com.example.annotationdemo.test.bwl.Originator;
import com.example.annotationdemo.test.daili.DynamicProxyTicketManager;
import com.example.annotationdemo.test.daili.ProxyUtils;
import com.example.annotationdemo.test.daili.TicketManager;
import com.example.annotationdemo.test.daili.TicketManagerImpl;
import com.example.annotationdemo.test.zrl.FirstHandler;
import com.example.annotationdemo.test.zrl.FiveHandler;
import com.example.annotationdemo.test.zrl.ThirdHandler;
import com.example.annotationdemo.test.zrl.ZRLHandler;
import com.example.annotationdemo.test.zrl.ZRLLevel;
import com.example.annotationdemo.test.zrl.ZRLRequest;
import com.example.annotationdemo.test.zrl.ZRLResponse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;

import javax.annotation.processing.AbstractProcessor;

@TestAnnotation(id = 1, msg = "test")
public class TestClass {

    @TestAnnotation(id = 3)
    int index;

    public static void main(String[] args) {
//        testAnnoMethod();
//        testCloneMethod();
//        testZRLMethod();
//        testBwlMethod();
//        testProxyMethod();
        testSocket();
    }

    private static void testSocket() {
        try {
            InetAddress byAddress = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
//            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
//            System.out.println(inetAddress.getHostAddress());
            System.out.println(byAddress.getCanonicalHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testProxyMethod() {
        //这里有两层代理，代理日志和代理身份信息认证
//        TicketManager tm=new LogProxy(new StaticProxyTicketManager(new TicketManagerImpl()));
//        tm.soldTicket();
//        tm.changeTicket();
//        tm.returnTicket();

        DynamicProxyTicketManager dynamicProxyTicketManager=new DynamicProxyTicketManager();
        TicketManagerImpl ticketManager = new TicketManagerImpl();
        TicketManager tmDymic=(TicketManager) dynamicProxyTicketManager.newProxyInstance(ticketManager);

        tmDymic.soldTicket();
        tmDymic.changeTicket();
        tmDymic.returnTicket();

        ProxyUtils.generateClassFile(ticketManager.getClass(), "TicketManagerProxy");

    }


    /**
     * 备忘录
     */
    private static void testBwlMethod() {
        Originator ori = new Originator();
        Caretaker caretaker = new Caretaker();
        ori.setState1("中国");
        ori.setState2("强盛");
        ori.setState3("繁荣");
        System.out.println("===初始化状态===\n" + ori);

        caretaker.setMemento("001", ori.createMemento());
        ori.setState1("软件");
        ori.setState2("架构");
        ori.setState3("优秀");
        System.out.println("===修改后状态===\n" + ori);

        ori.restoreMemento(caretaker.getMemento("001"));
        System.out.println("===恢复后状态===\n" + ori);
    }


    /**
     * 责任链
     */
    private static void testZRLMethod() {
        ZRLHandler handler1 = new FirstHandler();
        ZRLHandler handler3 = new ThirdHandler();
        ZRLHandler handler5 = new FiveHandler();

        // this level = 1 , level = 3
        handler1.setNextHandler(handler3);
        handler3.setNextHandler(handler5);

        // this level = 1 ,request level = 3
        ZRLResponse response = handler1.handleRequest(new ZRLRequest(new ZRLLevel(6)));
//        if (null != response) {
//            response.print(4);
//        } else {
//            System.out.println("done");
//        }
    }


    private static void testCloneMethod() {
        CloneTestClass instance = CloneTestClass.getInstance();
        System.out.println(instance);
        CloneTestClass clone = instance.clone();
        System.out.println(clone);
    }


    private static void testAnnoOthers() {
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
    }

    public static void testAnnoSelf() {
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
