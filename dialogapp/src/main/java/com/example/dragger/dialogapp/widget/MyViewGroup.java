package com.example.dragger.dialogapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.widget
 * ProjectName: ProjectTest01
 * Date: 2019/10/24 11:43
 */
public class MyViewGroup extends ViewGroup {

    private static final String TAG = MyViewGroup.class.getSimpleName();

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int contentAllWidth = 0;//= mCountdown.getAllContentWidth();
        int contentAllHeight = 0;//= mCountdown.getAllContentHeight();

        int viewWidth = measureSize(1, contentAllWidth, widthMeasureSpec);
        int viewHeight = measureSize(2, contentAllHeight, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = childAt.getLayoutParams();

            Log.i(TAG, "width = " + childAt.getMeasuredWidth());
            Log.i(TAG, "height = " + childAt.getMeasuredHeight());

            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.width,
                    layoutParams.width == LayoutParams.WRAP_CONTENT ? MeasureSpec.AT_MOST : MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.height,
                    layoutParams.height == LayoutParams.WRAP_CONTENT ? MeasureSpec.AT_MOST : MeasureSpec.EXACTLY);

            childAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (null != childAt) {
                    int measuredHeight = childAt.getMeasuredHeight();
                    int measuredWidth = childAt.getMeasuredWidth();
                    int left = (r - measuredWidth) / 2;
                    int top = (b - measuredHeight) / 2;
                    childAt.layout(left, top, left + measuredWidth, top + measuredHeight);
                }
            }
        }
    }


    /**
     * measure view Size
     *
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
            Log.i(TAG, "specType =" + specType + " specSize = " + specSize);
            result = contentSize;
            if (specType == 1) {
                // width
                result += (getPaddingLeft() + getPaddingRight());
            } else {
                // height
                result += (getPaddingTop() + getPaddingBottom());
            }
            Log.i(TAG, "result = " + result);
        }
        return MeasureSpec.makeMeasureSpec(result, specMode);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
