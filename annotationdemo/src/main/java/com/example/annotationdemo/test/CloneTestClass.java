package com.example.annotationdemo.test;

/**
 * Created by ex-zhoulai on 2018/5/2.
 */

public class CloneTestClass implements Cloneable {

    private CloneTestClass() {
    }

    private static final CloneTestClass INSTANCE = new CloneTestClass();

    public static CloneTestClass getInstance() {
        return INSTANCE;
    }

    public CloneTestClass clone() {
        CloneTestClass cloneTestClass = null;
        try {
            cloneTestClass = (CloneTestClass) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneTestClass;
    }
}
