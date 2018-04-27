package com.example.projecttest01.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ex-zhoulai on 2018/4/3.
 */

@Documented
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoGender {
}
