package com.example.rx.frame;

public interface Observer<T> {
    void update(T t);
}