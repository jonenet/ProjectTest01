package com.example.projecttest01.dagger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/2 22:37
 */

@Singleton
public class TestDaggerBean {

    String gender;
    String name;

    @Inject
    public TestDaggerBean(@UserName String name, @Gender String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

//    public void setGender(String gender) {
//        this.gender = gender;
//    }
}
