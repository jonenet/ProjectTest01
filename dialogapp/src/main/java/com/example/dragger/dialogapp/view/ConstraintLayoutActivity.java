package com.example.dragger.dialogapp.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dragger.dialogapp.R;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.constraintLayoutTest
 * ProjectName: ProjectTest01
 * Date: 2019/4/16 16:15
 */
public class ConstraintLayoutActivity extends Activity {

    private View mAdd;
    private View mLike;
    private View mTop;
    private View mWrite;
    private View likeGroup;
    private View writeGroup;
    private View topGroup;
    // 圆的半径
    private int radius;
    // FloatingActionButton宽度和高度，宽高一样
    private int width;
    private AnimatorSet mAnimatorSet;


    @Override
    protected void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrain_layout);

        initView();
    }

    private void initView() {
        mAdd = findViewById(R.id.fab_add);
        mLike = findViewById(R.id.fab_like);
        mTop = findViewById(R.id.fab_top);
        mWrite = findViewById(R.id.fab_write);
        likeGroup = findViewById(R.id.gp_like);
        writeGroup = findViewById(R.id.gp_write);
        topGroup = findViewById(R.id.gp_top);
        // 将三个弹出的FloatingActionButton隐藏
        setViewVisible(false);
        initListener();
    }

    private void setViewVisible(boolean isShow) {
        likeGroup.setVisibility(isShow ? View.VISIBLE : View.GONE);
        writeGroup.setVisibility(isShow ? View.VISIBLE : View.GONE);
        topGroup.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void initListener() {
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 播放动画的时候不可以点击
                if (mAnimatorSet != null && mAnimatorSet.isRunning()) { return; }

                // 判断播放显示还是隐藏动画
                if (likeGroup.getVisibility() != View.VISIBLE) {
                    mAnimatorSet = new AnimatorSet();
                    ValueAnimator likeAnimator = getValueAnimator(mLike, false, likeGroup, 0);
                    ValueAnimator writeAnimator = getValueAnimator(mWrite, false, writeGroup, 45);
                    ValueAnimator topAnimator = getValueAnimator(mTop, false, topGroup, 90);
                    mAnimatorSet.playSequentially(likeAnimator, writeAnimator, topAnimator);
                    mAnimatorSet.start();
                } else {
                    mAnimatorSet = new AnimatorSet();
                    ValueAnimator likeAnimator = getValueAnimator(mLike, true, likeGroup, 0);
                    ValueAnimator writeAnimator = getValueAnimator(mWrite, true, writeGroup, 45);
                    ValueAnimator topAnimator = getValueAnimator(mTop, true, topGroup, 90);
                    mAnimatorSet.playSequentially(topAnimator, writeAnimator, likeAnimator);
                    mAnimatorSet.start();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 动态获取FloatingActionButton的宽
        mAdd.post(new Runnable() {



            @Override
            public void run() {
                width = mAdd.getMeasuredWidth();
            }
        });
        // 在xml文件里设置的半径
        radius = dp2px(this, 80);
    }

    private int dp2px(ConstraintLayoutActivity constraintLayoutActivity, int i) {
        float density = constraintLayoutActivity.getResources().getDisplayMetrics().density;
        return (int) (i * density + 0.5f);
    }



    private ValueAnimator getValueAnimator(final View button, final boolean reverse, final View group, final int angle) {
        ValueAnimator animator;
        if (reverse)
            animator = ValueAnimator.ofFloat(1, 0);
        else
            animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) button.getLayoutParams();
//                params.circleRadius = (int) (radius * v);
                params.circleAngle = 270f + angle * v;
                params.width = (int) (width * v);
                params.height = (int) (width * v);
                button.setLayoutParams(params);
            }
        });
        animator.addListener(new SimpleAnimation() {
            @Override
            public void onAnimationStart(Animator animation) {
                group.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(group == likeGroup && reverse){
                    setViewVisible(false);
                }
            }
        });
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        return animator;
    }

    abstract class SimpleAnimation implements Animator.AnimatorListener{
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }
}
