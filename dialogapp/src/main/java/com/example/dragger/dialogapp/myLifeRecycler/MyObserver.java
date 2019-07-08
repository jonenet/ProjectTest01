package com.example.dragger.dialogapp.myLifeRecycler;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.dragger.dialogapp.myLifeRecycler
 * ProjectName: ProjectTest01
 * Date: 2019/7/8 17:13
 */
public class MyObserver implements LifecycleObserver {

    private CallBack mCallBack;

    public MyObserver(CallBack callBack) {
        mCallBack = callBack;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        p("MyObserver connectOnCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        p("MyObserver connectOnResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        p("MyObserver disConnectOnDestroy");
    }

    private void p(String string) {
        mCallBack.update(string);
    }
}
