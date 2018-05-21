package com.example.dragger.dialogapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.baselib.impl.AutowiredServiceImpl;

/**
 * Created by ex-zhoulai on 2018/4/28.
 */

public class NextActivity extends AppCompatActivity {

//    @TestAnnoAware
//    public String text;
//
//    @TestAnnoAware
//    public String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        AutowiredServiceImpl.create().inject(this);
//        ((TextView) findViewById(R.id.tv_text)).setText(text);
    }
}
