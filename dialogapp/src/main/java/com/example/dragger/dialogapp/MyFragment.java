package com.example.dragger.dialogapp;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/5/9 23:29
 */

public class MyFragment extends BottomSheetFragment {

    private View content;
    private View llContent;
    private View rootView;
    private View showBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my, container, false);
        content = rootView.findViewById(R.id.ll_test_content);
        llContent = rootView.findViewById(R.id.ll_content);
        showContent(false);
        showBtn = rootView.findViewById(R.id.btn_show);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContent(true);
//                showAnimation();
            }
        });
        rootView.findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContent(false);
//                closeAnimation();
            }
        });
        return rootView;
    }

    private void showContent(boolean flag) {
        if (flag) {
            content.setVisibility(View.VISIBLE);
        } else {
            content.setVisibility(View.GONE);
        }
    }

    public void showAnimation() {
        int measuredHeight = content.getMeasuredHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(llContent, "translationY", 0, -measuredHeight);
        animator.setDuration(300);
        animator.start();
    }

    public void closeAnimation() {
        int measuredHeight = content.getMeasuredHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(llContent, "translationY", -measuredHeight, 0);
        animator.setDuration(300);
        animator.start();
    }


}