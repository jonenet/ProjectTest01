package com.example.dragger.dialogapp.reflect;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.reflect
 * ProjectName: ProjectTest01
 * Date: 2019/10/17 15:00
 */
public class ReflectTest {
    private String name;

    private ReflectTest(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
