package com.example.annotationdemo.processer;

import com.example.annotationdemo.anno.TestAnnotation;

/**
 * Created by ex-zhoulai on 2018/4/27.
 */

@TestAnnotation
public class AnnoUseClass {

    @TestAnnotation
    private int age;

    @TestAnnotation
    public int cal(int a, int b) {
        return a + b;
    }

    @TestAnnotation
    public void print() {
    }

    public int getAge(){
        return age;
    }


}
