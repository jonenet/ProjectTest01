package com.example.daggerapp;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/2 22:36
 */

@Module
public class MainModule {

    @StringName("name")
    @Provides
    public String providerName(){
        return "name";
    }

    @StringName("type")
    @Provides
    public String providerType(){
        return "type";
    }

//    @Provides
//    public DaggerBean provideTestBean() {
//        return new DaggerBean(name);
//    }


//    String name;
//
//    public MainModule(String name) {
//        this.name = name;
//    }



//    private String name;
//    private String gender;

//    @Provides
//    public DaggerBean provideTestBean() {
//        return new DaggerBean(name,gender);
//    }



//    public MainModule(String name, String gender) {
//        this.name = name;
//        this.gender = gender;
//    }
//
//    @StringName
//    @Provides
//    public String provideName() {
//        return name;
//    }
//
//    @Type
//    @Provides
//    public String provideGender() {
//        return gender;
//    }

}
