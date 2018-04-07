package com.example.projecttest01.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/2 22:36
 */

@Module
public class MainModule {



    private String name;
    private String gender;
    
//    @Provides
//    public TestDaggerBean provideTestBean() {
//        return new TestDaggerBean(name,gender);
//    }
    
    public MainModule(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    @UserName
    @Provides
    public String provideName() {
        return name;
    }

    @Gender
    @Provides
    public String provideGender() {
        return gender;
    }

}
