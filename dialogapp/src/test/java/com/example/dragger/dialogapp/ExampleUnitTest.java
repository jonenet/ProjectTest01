package com.example.dragger.dialogapp;

import com.alibaba.fastjson.JSON;
import com.example.dragger.dialogapp.proxy.ProxyHandler;
import com.example.dragger.dialogapp.proxy.RealSubject;
import com.example.dragger.dialogapp.proxy.Subject;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    public class A {
        public String show(D obj) {
            return ("A and D");
        }

        public String show(A obj) {
            return ("A and A");
        }

    }

    public class B extends A {
        public String show(B obj) {
            return ("B and B");
        }

        public String show(A obj) {
            return ("B and A");
        }
    }

    public class C extends B {

    }

    public class D extends B {

    }


    @Test
    public void testClass() {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println("1--" + a1.show(b)); // A and A
        System.out.println("2--" + a1.show(c)); // A and A
        System.out.println("3--" + a1.show(d)); // A and D
        System.out.println("4--" + a2.show(b)); // B and A
        System.out.println("5--" + a2.show(c)); // B and A
        System.out.println("6--" + a2.show(d)); // A and D
        System.out.println("7--" + b.show(b));  // B and B
        System.out.println("8--" + b.show(c));  // B and B
        System.out.println("9--" + b.show(d));  // A and D
    }


    private static class Father {
        public void methods() {
            System.out.println("methods in Father");
        }
    }

    private static class Son extends Father {
        @Override
        public void methods() {
            System.out.println("methdos in Son");
        }
    }


    @Test
    public void testSuper() {
        Father obj = new Son();

        if (obj instanceof Father) {
            System.out.println("obj instanceof Father");
        }
        if (obj instanceof Son) {
            System.out.println("obj instanceof Son");
        }
        System.out.println("obj is " + obj.getClass().getName());

    }


    public class Payer {
        void pay() {
            System.out.println("Payer implement...");
        }
    }

    public interface IPay {
        void pay();
    }

    public class Payer1 extends Payer {

        private class InnerPayer implements IPay {
            public void pay() {
                Payer1.this.pay();
            }
        }

        IPay startPay() {
            return new InnerPayer();
        }
    }


    //Lev 1
    class Food {
        public void eat() {
            System.out.println("food eat");
        }
    }

    //Lev 2
    class Fruit extends Food {
        @Override
        public void eat() {
            System.out.println("Fruit eat");
        }
    }

    class Meat extends Food {
    }

    //Lev 3
    class Apple extends Fruit {
    }

    class Banana extends Fruit {
    }

    class Pork extends Meat {
    }

    class Beef extends Meat {
    }

    //Lev 4
    class RedApple extends Apple {
    }

    class GreenApple extends Apple {
    }

    class Plate<T> {
        private T item;

        public Plate(T t) {item = t;}

        public void set(T t) {item = t;}

        public T get() {return item;}
    }

    @Test
    public void testGene() {
//        Plate<? extends Fruit> p=new Plate<>(new Apple());

        //不能存入任何元素
//        p.set(new Fruit());    //Error
//        p.set(new Apple());    //Error
//        p.set(new RedApple());
//
//        //读取出来的东西只能存放在Fruit或它的基类里。
//        Fruit newFruit1=p.get();
//        Object newFruit2=p.get();
//        Apple newFruit3=p.get();    //Error

    }


    public class JavaSerialize {

        public void start() throws IOException, ClassNotFoundException {
            User u = new User();
            ArrayList<User> friends = new ArrayList<>();
            u.setUserName("张三");
            u.setPassWord("123456");
            u.setUserInfo("张三是一个很牛逼的人");
            u.setFriends(friends);

            User f1 = new User();
            f1.setUserName("李四");
            f1.setPassWord("123456");
            f1.setUserInfo("李四是一个很牛逼的人");

            User f2 = new User();
            f2.setUserName("王五");
            f2.setPassWord("123456");
            f2.setUserInfo("王五是一个很牛逼的人");

            for (int i = 0; i < 1; i++) {
                friends.add(f1);
                friends.add(f2);
            }

            long t1 = System.currentTimeMillis();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(out);
            for (int i = 0; i < 10; i++) {
                obj.writeObject(u);
            }
            System.out.println("java serialize: " + (System.currentTimeMillis() - t1) + "ms; 总大小：" + out.toByteArray().length);

            long t2 = System.currentTimeMillis();
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new java.io.ByteArrayInputStream(out.toByteArray())));
            User user = (User) ois.readObject();
            System.out.println("java deserialize: " + (System.currentTimeMillis() - t2) + "ms; User: " + user);

            long t3 = System.currentTimeMillis();
            String s = JSON.toJSONString(u);
            System.out.println("java serialize: " + (System.currentTimeMillis() - t3) + "ms; 总大小：" + s.length());
            long t4 = System.currentTimeMillis();
            User user1 = JSON.parseObject(s, User.class);
            System.out.println("fastJson serialize: " + (System.currentTimeMillis() - t4) + "ms; user1: " + user1);


        }
    }

    @Test
    public void testSerialize() {
        try {
            new JavaSerialize().start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public class InnerDemo01 {
        class Bar {
            public void show() {
                //do ..
            }
        }

        public Bar method() {
            //String str="wuranghao";
            int num = 30;
            //局部内部类将输出这个局部变量
            class innerClass extends Bar {

                public void show() {
                    System.out.println(num);
                }
            }
            return new innerClass();
        }

    }

    @Test
    public void testInner() {
        InnerDemo01 innerDemo01 = new InnerDemo01();
        InnerDemo01.Bar bar = innerDemo01.method();
        bar.show();//你觉得num会输出吗？？？？

        int i = Integer.parseInt("11");
        System.out.println(i);
    }

    @Test
    public void testProxy() {
        RealSubject real = new RealSubject();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        // 1.返回代理类对象 Proxy ？ 这个proxy是如何生成的
        Subject subject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(), new Class[]{Subject.class}, new ProxyHandler(real));
        // 2.调用的是ProxyHandler的invoke方法
        subject.subjectShow();
    }

    @Test
    public void testReflect() {
        try {
            long time1 = System.currentTimeMillis();
            Class<?> aClass = Class.forName("com.example.dragger.dialogapp.reflect.ReflectTest");
            Constructor<?> constructor = aClass.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            Object jone = constructor.newInstance("jone");
            Method setName = aClass.getDeclaredMethod("setName", String.class);
            setName.invoke(jone, "fiona");
            Method getName = aClass.getDeclaredMethod("getName");
            Object fiona = getName.invoke(jone);
            System.out.println("time = " + (System.currentTimeMillis() - time1) + "ms " + fiona);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThread(){
    }





}