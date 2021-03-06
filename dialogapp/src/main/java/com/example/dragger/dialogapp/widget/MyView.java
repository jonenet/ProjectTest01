package com.example.dragger.dialogapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by ex-zhoulai on 2018/5/7.
 */

public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
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
            //确定大小的情况 getMeasureSize 和 实际需要的大小  取一个大的值
            result = Math.max(contentSize, specSize);
        } else {
            //如果是wrap_content,wrap_content 默认是match_parent的
            Log.i(TAG,"specType =" +specType +" specSize = " +specSize);
            result = contentSize;
            if (specType == 1) {
                // width
                result += (getPaddingLeft() + getPaddingRight());
            } else {
                // height
                result += (getPaddingTop() + getPaddingBottom());
            }
            Log.i(TAG,"result = " +result);
        }
        return MeasureSpec.makeMeasureSpec(result,specMode);
    }

}
