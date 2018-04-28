package com.example.testannotation.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ex-zhoulai on 2018/4/28.
 */


@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnoAware {

    String value() default "";

}
