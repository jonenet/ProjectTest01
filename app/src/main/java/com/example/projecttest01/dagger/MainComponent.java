package com.example.projecttest01.dagger;

import com.example.projecttest01.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ex-zhoulai on 2018/4/3.
 */

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);
}
