package com.example.dragger.dialogapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by ex-zhoulai on 2018/4/28.
 */

public class NextActivity extends AppCompatActivity {
    private ImageView ivScan;

//    @TestAnnoAware
//    public String text;
//
//    @TestAnnoAware
//    public String name;

    @SuppressLint("ObjectAnimatorBinding")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
//        AutowiredServiceImpl.create().inject(this);
//        ((TextView) findViewById(R.id.tv_text)).setText(text);
        ivScan = findViewById(R.id.iv_scan);
        findViewById(R.id.step_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one(ivScan);
            }
        });
        findViewById(R.id.step_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two(ivScan);
            }
        });

        findViewById(R.id.step_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void two(ImageView ivScan) {
        ObjectAnimator[] objectAnimators = new ObjectAnimator[]{
                ObjectAnimator.ofFloat(ivScan, "translationX", 0, -450),
                ObjectAnimator.ofFloat(ivScan, "translationY", 0, 550),
                ObjectAnimator.ofFloat(ivScan, "scaleX", 1f, 0.4f),
                ObjectAnimator.ofFloat(ivScan, "scaleY", 1f, 0.4f)
        };

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000).playTogether(objectAnimators);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }

    private void one(ImageView ivScan) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivScan, "rotation", 0f, 360f);
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }
}
