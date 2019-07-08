package com.example.dragger.dialogapp.widget;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;

import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.widget
 * ProjectName: ProjectTest01
 * Date: 2019/6/26 11:40
 */
public class CircularRevealHelper extends ConstraintHelper {


    public CircularRevealHelper(Context context) {
        super(context);
    }

    public CircularRevealHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularRevealHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void updatePostLayout(ConstraintLayout container) {
        super.updatePostLayout(container);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View[] views = getViews(container);
            for (View view : views) {
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2,
                        view.getHeight() / 2, 0f,
                        (float) Math.hypot((view.getHeight() * 1d / 2), (view.getWidth() * 1d / 2)));
                circularReveal.setDuration(3000);
                circularReveal.start();
            }
        }
    }
}
