package com.example.dragger.dialogapp.proxy;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.proxy
 * ProjectName: ProjectTest01
 * Date: 2019/10/17 14:03
 */
public class RealSubject implements Subject {
    @Override
    public void subjectShow() {
        System.out.println("RealSubject subjectShow");
    }
}
