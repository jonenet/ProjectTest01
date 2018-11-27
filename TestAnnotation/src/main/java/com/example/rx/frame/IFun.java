package com.example.rx.frame;

public interface IFun<T, R> {
    R call(T t);
}