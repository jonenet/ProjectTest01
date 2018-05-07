package com.example.dragger.dialogapp.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ex-zhoulai on 2018/5/7.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int contentAllWidth = 0;//= mCountdown.getAllContentWidth();
        int contentAllHeight = 0;//= mCountdown.getAllContentHeight();
        int viewWidth = measureSize(1, contentAllWidth, widthMeasureSpec);
        int viewHeight = measureSize(2, contentAllHeight, heightMeasureSpec);
        setMeasuredDimension(viewWidth, viewHeight);

//        mCountdown.onMeasure(this, viewWidth, viewHeight, contentAllWidth, contentAllHeight);
    }


    /**
     * measure view Size
     * @param specType    1 width 2 height
     * @param contentSize all content view size
     * @param measureSpec spec
     * @return measureSize
     */
    private int measureSize(int specType, int contentSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            //getMeasureSize 和 实际需要的大小  取一个大的值
            result = Math.max(contentSize, specSize);
        } else {
            result = contentSize;

            if (specType == 1) {
                // width
                result += (getPaddingLeft() + getPaddingRight());
            } else {
                // height
                result += (getPaddingTop() + getPaddingBottom());
            }
        }

        return result;
    }

}
