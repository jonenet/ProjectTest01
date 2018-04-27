package com.example.projecttest01.dagger;

import com.example.projecttest01.annotation.AnnoGender;
import com.example.projecttest01.annotation.AnnoName;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ex-zhoulai on 2018/4/3.
 */
@Module
public class MainModule {

    private String name;
    private String gender;

    public MainModule(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    @AnnoName
    @Provides
    public String providerName() {
        return name;
    }

    @AnnoGender
    @Provides
    public String providerGender() {
        return gender;
    }

    @Provides
    public AnimalBean providerAnimal(){
        return new AnimalBean("50cm");
    }

}
