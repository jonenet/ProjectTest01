package com.example.dragger.dialogapp.widget;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.widget
 * ProjectName: ProjectTest01
 * Date: 2019/6/19 10:12
 */
public class FocusLayoutManager extends RecyclerView.LayoutManager {

    /**
     * 一次完整的聚焦滑动所需要移动的距离。似乎就是一个item的宽度
     */
    private float onceCompleteScrollLength = -1;
    private int mHorizontalOffset;
    private int mLastVisiPos;
    private int mFirstVisiPos;
    private int maxLayerCount = 3;
    private int mLayerPadding = 15;
    private float mNormalViewGap = 30;
    private int mFocusPosition;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }

        onceCompleteScrollLength = -1;
        //分离全部已有的view，放入临时缓存
        detachAndScrapAttachedViews(recycler);

        fill(recycler, state, 0);
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler,
                                    RecyclerView.State state) {
        //手指从右向左滑动，dx > 0; 手指从左向右滑动，dx < 0;

        //位移0、没有子View 当然不移动
        if (dx == 0 || getChildCount() == 0) {
            return 0;
        }

        mHorizontalOffset += dx;//累加实际滑动距离


        dx = fill(recycler, state, dx);

        return dx;
    }

    @Override
    public boolean canScrollHorizontally() {
//        return focusOrientation == FOCUS_LEFT || focusOrientation == FOCUS_RIGHT;
        return true;
    }

    private int fill(RecyclerView.Recycler recycler, RecyclerView.State state, int delta) {
        int resultDelta = delta;
        resultDelta = fillHorizontalLeft(recycler, state, delta);
        recycleChildren(recycler);
        return resultDelta;
    }

    private int fillHorizontalLeft(RecyclerView.Recycler recycler, RecyclerView.State state, int dx) {
        if (dx < 0) {
            //已达左边界
            if (mHorizontalOffset < 0) {
                mHorizontalOffset = dx = 0;
            }
        }

        if (dx > 0) {
            //滑动到只剩堆叠view，没有普通view了，说明已经到达右边界了
            if (mLastVisiPos - mFirstVisiPos <= maxLayerCount - 1) {
                //因为scrollHorizontallyBy里加了一次dx，现在减回去
                mHorizontalOffset -= dx;
                dx = 0;
            }
        }

        //分离全部的view，放入临时缓存
          detachAndScrapAttachedViews(recycler);

        //----------------2、初始化布局数据-----------------
        // 第一个应该从0开始
        float startX = getPaddingLeft() - mLayerPadding;

        View tempView = null;
        int tempPosition = -1;

        if (onceCompleteScrollLength == -1) {
            //因为mFirstVisiPos在下面可能会被改变，所以用tempPosition暂存一下。
            tempPosition = mFirstVisiPos;
            tempView = recycler.getViewForPosition(tempPosition);
            measureChildWithMargins(tempView, 0, 0);
            // 一个view的宽度
            onceCompleteScrollLength = getDecoratedMeasurementHorizontal(tempView) + mNormalViewGap;
        }

        //当前"一次完整的聚焦滑动"所在的进度百分比.百分比增加方向为向着堆叠移动的方向（即如果为FOCUS_LEFT，从右向左移动fraction将从0%到100%）
        float fraction = (Math.abs(mHorizontalOffset) % onceCompleteScrollLength) / (onceCompleteScrollLength * 1.0f);

        //堆叠区域view偏移量。在一次完整的聚焦滑动期间，其总偏移量是一个layerPadding的距离
        float layerViewOffset = mLayerPadding * fraction;
        //普通区域view偏移量。在一次完整的聚焦滑动期间，其总位移量是一个onceCompleteScrollLength
        float normalViewOffset = onceCompleteScrollLength * fraction;
        boolean isLayerViewOffsetSetted = false;
        boolean isNormalViewOffsetSetted = false;

        //修正第一个可见的view：mFirstVisiPos。已经滑动了多少个完整的onceCompleteScrollLength就代表滑动了多少个item
        mFirstVisiPos = (int) Math.floor(Math.abs(mHorizontalOffset) / onceCompleteScrollLength); //向下取整
        //临时将mLastVisiPos赋值为getItemCount() - 1，放心，下面遍历时会判断view是否已溢出屏幕，并及时修正该值并结束布局
        mLastVisiPos = getItemCount() - 1;

        int newFocusedPosition = mFirstVisiPos + maxLayerCount - 1;
        if (newFocusedPosition != mFocusPosition) {
//            if (onFocusChangeListener != null) {
//                onFocusChangeListener.onFocusChanged(newFocusedPosition, mFocusPosition);
//            }
            mFocusPosition = newFocusedPosition;
        }

        for (int i = mFirstVisiPos; i <= mLastVisiPos; i++) {
            //属于堆叠区域
            if (i - mFirstVisiPos < maxLayerCount) {
                View item;

                if (i == tempPosition && tempView != null) {
                    //如果初始化数据时已经取了一个临时view，可别浪费了！
                    item = tempView;
                } else {
                    item = recycler.getViewForPosition(i);
                }
                addView(item);
                measureChildWithMargins(item, 0, 0);

                startX += mLayerPadding;
                if (!isLayerViewOffsetSetted) {
                    startX -= layerViewOffset;
                    isLayerViewOffsetSetted = true;
                }

//                if (trasitionListeners != null && !trasitionListeners.isEmpty()) {
//                    for (TrasitionListener trasitionListener : trasitionListeners) {
//                        trasitionListener.handleLayerView(this, item, i - mFirstVisiPos,
//                                maxLayerCount, i, fraction, dx);
//                    }
//                }

                int l, t, r, b;
                l = (int) startX;
                t = getPaddingTop();
                r = (int) (startX + getDecoratedMeasurementHorizontal(item));
                b = getPaddingTop() + getDecoratedMeasurementVertical(item);
                layoutDecoratedWithMargins(item, l, t, r, b);


            } else {//属于普通区域

                View item = recycler.getViewForPosition(i);
                addView(item);
                measureChildWithMargins(item, 0, 0);

                startX += onceCompleteScrollLength;
                if (!isNormalViewOffsetSetted) {
                    startX += layerViewOffset;
                    startX -= normalViewOffset;
                    isNormalViewOffsetSetted = true;
                }

//                if (trasitionListeners != null && !trasitionListeners.isEmpty()) {
//                    for (TrasitionListener trasitionListener : trasitionListeners) {
//                        if (i - mFirstVisiPos == maxLayerCount) {
//                            trasitionListener.handleFocusingView(this, item, i, fraction, dx);
//                        } else {
//                            trasitionListener.handleNormalView(this, item, i, fraction, dx);
//                        }
//                    }
//                }

                int l, t, r, b;
                l = (int) startX;
                t = getPaddingTop();
                r = (int) (startX + getDecoratedMeasurementHorizontal(item));
                b = getPaddingTop() + getDecoratedMeasurementVertical(item);
                layoutDecoratedWithMargins(item, l, t, r, b);

                //判断下一个view的布局位置是不是已经超出屏幕了，若超出，修正mLastVisiPos并跳出遍历
                if (startX + onceCompleteScrollLength > getWidth() - getPaddingRight()) {
                    mLastVisiPos = i;
                    break;
                }
            }

        }
        return dx;
    }


    private void recycleChildren(RecyclerView.Recycler recycler) {
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        for (int i = 0; i < scrapList.size(); i++) {
            RecyclerView.ViewHolder holder = scrapList.get(i);
            removeAndRecycleView(holder.itemView, recycler);
        }
    }

    private int getDecoratedMeasurementVertical(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                view.getLayoutParams();
        return getDecoratedMeasuredHeight(view) + params.topMargin
                + params.bottomMargin;
    }

    private float getDecoratedMeasurementHorizontal(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                view.getLayoutParams();
        return getDecoratedMeasuredWidth(view) + params.leftMargin
                + params.rightMargin;
    }

}
