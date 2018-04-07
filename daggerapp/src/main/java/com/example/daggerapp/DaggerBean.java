package com.example.daggerapp;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/2 22:37
 */

@Singleton
public class DaggerBean {
    private String name;
    private String type;


    @Inject
    public DaggerBean(@StringName("name")String name,@StringName("type")String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
