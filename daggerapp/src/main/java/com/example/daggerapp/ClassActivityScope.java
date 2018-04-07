package com.example.daggerapp;

import javax.inject.Inject;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/7 23:14
 */

public class ClassActivityScope {

    @Inject
    DaggerBean daggerBean;

    public ClassActivityScope(MainComponent build) {
        build.inject(this);
    }

    public DaggerBean getDaggerBean() {
        return daggerBean;
    }
}
