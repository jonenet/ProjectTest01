package com.example.projecttest01.dagger;

import com.example.projecttest01.annotation.AnnoGender;
import com.example.projecttest01.annotation.AnnoName;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ex-zhoulai on 2018/4/3.
 */

@Singleton
public class TestDaggerBean {

    private String name;
    private String gender;

    @Inject
    public TestDaggerBean(@AnnoName String name, @AnnoGender String gender) {
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

    public void setGender(String gender) {
        this.gender = gender;
    }
}
