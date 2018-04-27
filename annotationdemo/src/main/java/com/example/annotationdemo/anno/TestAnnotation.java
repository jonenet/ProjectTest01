package com.example.annotationdemo.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ex-zhoulai on 2018/4/27.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    int id() default -1;

    String msg() default "";

}
