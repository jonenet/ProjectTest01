package com.example.projecttest01.annotation;

import android.widget.RemoteViews;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by ex-zhoulai on 2018/4/3.
 */

@Documented
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoName {
}
