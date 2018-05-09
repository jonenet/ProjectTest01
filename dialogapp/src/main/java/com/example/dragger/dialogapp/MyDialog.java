package com.example.dragger.dialogapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rey.material.app.BottomSheetDialog;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/5/9 22:50
 */

public class MyDialog extends BottomSheetDialog {

    private View content;
    private View llContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog, null, false);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        rootView.setLayoutParams(layoutParams);
        setContentView(rootView,layoutParams);
        content = findViewById(R.id.ll_test_content);
        llContent = findViewById(R.id.ll_content);
        showContent(false);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContent(true);
            }
        });
        findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContent(false);
            }
        });
    }

    public MyDialog(Context context) {
        super(context);
    }

    public void showContent(boolean flag) {
        if (flag) {
            content.setVisibility(View.VISIBLE);
        } else {
            content.setVisibility(View.GONE);
        }
    }
}
