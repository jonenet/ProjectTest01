package com.example.projecttest01.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/2 23:48
 */
@Documented
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {
}
