package com.example.projecttest01.dagger;

import com.example.projecttest01.MainActivity;

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
}
