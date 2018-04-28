package com.example.dragger.dialogapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.baselib.impl.AutowiredServiceImpl;
import com.example.testannotation.anno.TestAnnoAware;

/**
 * Created by ex-zhoulai on 2018/4/28.
 */

public class NextActivity extends Activity {

    @TestAnnoAware
    public String text;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutowiredServiceImpl.create().inject(this);
        ((TextView) findViewById(R.id.tv_text)).setText(text);
    }
}
