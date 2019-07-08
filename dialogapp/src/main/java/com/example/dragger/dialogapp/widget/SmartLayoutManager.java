package com.example.dragger.dialogapp.widget;

import android.graphics.PointF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.widget
 * ProjectName: ProjectTest01
 * Date: 2019/6/18 11:52
 */
public class SmartLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {

    private int mDecoratedChildWidth;
    public int mDecoratedChildHeight;
    private int mScrollOffset;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 实现ItemView的创建/布局/回收/复用
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //recycler.getViewForPosition 从recycle缓存中拿到指定position的缓存itemview，如果没有则调用onCreateViewHolder来进行创建
        final View view = recycler.getViewForPosition(0);
//        addView(view);
        measureChildWithMargins(view, 0, 0);
        // 计算view的实际大小
        mDecoratedChildWidth = getDecoratedMeasuredWidth(view);
        mDecoratedChildHeight = getDecoratedMeasuredHeight(view);
        // removeAndRecycleView 将指定view移除并放入recycle缓存中
//        removeAndRecycleView(view, recycler);

        // detachAndScrapAttachedViews 分离全部的view，放入临时缓存
        detachAndScrapAttachedViews(recycler);
        fill(recycler, state);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            // removeAndRecycleViewAt 将指定view移除并放入recycle缓存中
            removeAndRecycleViewAt(i, recycler);
        }
        // // FIXME: 2019/6/18 不懂 应该是获取到中心位置的 item
        float centerOffset = 1f * mScrollOffset / mDecoratedChildHeight;
        int centerPosition = Math.round(centerOffset);

        int left = (getWidth() - getPaddingLeft() - getPaddingRight() - mDecoratedChildWidth) / 2;
        int top = (getHeight() - getPaddingBottom() - getPaddingTop() - mDecoratedChildHeight) / 2;

        //显示7个item,上下各三个
        for (int i = centerPosition - 3; i <= centerPosition + 3; i++) {
            if (i < 0) {
                continue;
            }
            if (i > getItemCount() - 1) {
                break;
            }
            final View view = recycler.getViewForPosition(i);

            // 添加子view
            addView(view);
            measureChildWithMargins(view, 0, 0);

            float offset = i - centerOffset;
            double itemPxFromCenter = getCardOffsetByPositionDiff(offset);//纯数学算法，处理为中间速度快，两端速度慢
            final float scale = (float) (2 * (2 * -StrictMath.atan(Math.abs(offset) + 1.0) / Math.PI + 1));
            final float translateYGeneral = mDecoratedChildHeight * (1 - scale) / 2f;
            float translateY = Math.signum(offset) * translateYGeneral;
            layoutDecoratedWithMargins(view, left, (int) (top + itemPxFromCenter + translateY), left + mDecoratedChildWidth, (int) (top + itemPxFromCenter + mDecoratedChildHeight + translateY));


//            view.setScaleX(scale);
//            view.setScaleY(scale);
//            ViewCompat.setZ(view, 1 / (1 + Math.abs(offset)));
        }
    }

    /**
     * 实现容器的纵向滚动
     */
    @Override
    public boolean canScrollVertically() {
        return true;
    }

    /**
     * 实现容器的纵向滚动
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int newOffset = mScrollOffset + dy;
        newOffset = Math.max(0, Math.min(newOffset, (getItemCount() - 1) * mDecoratedChildHeight));
        dy = newOffset - mScrollOffset;
        mScrollOffset += dy;
        offsetChildrenVertical(-dy);
        fill(recycler, state);
        return dy;
    }

    protected double getCardOffsetByPositionDiff(final float itemPositionDiff) {
        final double smoothPosition = convertItemPositionDiffToSmoothPositionDiff(itemPositionDiff);
        final int dimenDiff;
        dimenDiff = (getHeight() - getPaddingBottom() - getPaddingTop() - mDecoratedChildHeight) / 2;
        return Math.signum(itemPositionDiff) * dimenDiff * smoothPosition;
    }

    protected double convertItemPositionDiffToSmoothPositionDiff(final float itemPositionDiff) {
        final float absIemPositionDiff = Math.abs(itemPositionDiff);
        if (absIemPositionDiff > StrictMath.pow(1.0f / 3, 1.0f / 3)) {
            return StrictMath.pow(absIemPositionDiff / 3, 1 / 2.0f);
        } else {
            return StrictMath.pow(absIemPositionDiff, 2.0f);
        }
    }

    public int centerPosition() {
        return Math.round(1f * mScrollOffset / mDecoratedChildHeight);
    }

    public View getCenterChildView() {
        int centerPosition = centerPosition();
        int childCount = getChildCount();
        if (centerPosition < 0 && childCount > 0) {
            return getChildAt(0);
        }
        int latestIndex = getItemCount() - 1;
        if (childCount > 0 && centerPosition > latestIndex) {
            return getChildAt(latestIndex);
        }
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int position = getPosition(childView);
            if (centerPosition == position) {
                return childView;
            }
        }
        return null;
    }

    public int getOffsetForCurrentView(@NonNull final View view) {
        final int targetPosition = getPosition(view);
        final float directionDistance = getScrollDirection(targetPosition);
        return Math.round(directionDistance * mDecoratedChildHeight);
    }

    private float getScrollDirection(final int targetPosition) {
        final float currentScrollPosition = 1f * mScrollOffset / mDecoratedChildHeight;
        return currentScrollPosition - targetPosition;
    }

    /**
     * 实现ScrollVectorProvider接口，确定容器的滚动方向，配合LinearSmoothScroller和SnapHelper使用
     */
    @Override
    public PointF computeScrollVectorForPosition(int targetPosition) {
        if (getChildCount() == 0) {
            return null;
        }
        return new PointF(0, targetPosition - centerPosition());
    }
}
