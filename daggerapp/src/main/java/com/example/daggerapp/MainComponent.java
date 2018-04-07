package com.example.daggerapp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/2 22:36
 */


@Singleton
@Component(modules  = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
    void inject(ClassActivityScope classActivityScope);
}
