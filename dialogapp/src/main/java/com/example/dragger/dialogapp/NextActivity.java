package com.example.dragger.dialogapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.baselib.impl.AutowiredServiceImpl;
import com.flipboard.bottomsheet.BottomSheetLayout;

/**
 * Created by ex-zhoulai on 2018/4/28.
 */

public class NextActivity extends AppCompatActivity {

//    @TestAnnoAware
//    public String text;
//
//    @TestAnnoAware
//    public String name;
    protected BottomSheetLayout bottomSheetLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        AutowiredServiceImpl.create().inject(this);

        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        findViewById(R.id.bottomsheet_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyFragment().show(getSupportFragmentManager(), R.id.bottomsheet);
            }
        });
//        ((TextView) findViewById(R.id.tv_text)).setText(text);
    }
}
