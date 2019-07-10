package com.example.dragger.dialogapp;

import android.app.Application;
import android.content.Context;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.dragger.dialogapp
 * ProjectName: ProjectTest01
 * Date: 2019/7/9 17:49
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
