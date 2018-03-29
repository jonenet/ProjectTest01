package com.example.projecttest01.callback;

/**
 * Created by ex-zhoulai on 2018/3/29.
 */

public interface Fun<T, R> {
    R call(T t);
}
