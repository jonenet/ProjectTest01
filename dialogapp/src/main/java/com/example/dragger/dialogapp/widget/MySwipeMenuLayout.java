package com.example.dragger.dialogapp.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by ex-zhoulai on 2018/5/7.
 */

public class MySwipeMenuLayout extends ViewGroup {

    private static final String TAG = "MySwipeMenuLayout";
    private int mScaleTouchSlop;
    private int mMaxVelocity;
    private int mRightMenuWidths;
    private int mHeight;
    private View mContentView;
    private int mLimit;
    private VelocityTracker mVelocityTracker;
    private int mPointerId;
    private ValueAnimator mCloseAnim;
    private ValueAnimator mExpandAnim;

    public MySwipeMenuLayout(Context context) {
        this(context, null);
    }

    public MySwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MySwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        setClickable(true);//令自己可点击，从而获取触摸事件

    }

    //一、处理控件大小问题            MeasureSpec.EXACTLY        MeasureSpec.AT_MOST      如果是
    //二、处理子控件位置排布
    //三、处理滑动事件              scrollTo 滑动到指定的位置    scrollBy 滑动多少的而距离（一般在ACTION_MOVE中使用）
    //四、事件处理                  getParent().requestDisallowInterceptTouchEvent(true);
    //
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mRightMenuWidths = 0;//侧边栏的宽度 由于ViewHolder的复用机制，每次这里要手动恢复初始值
        mHeight = height;
        int contentWidth = 0;// 适配GridLayoutManager，将以第一个子Item(即ContentItem)的宽度为控件宽度

        int childCount = getChildCount();
        //MatchParent 和 具体大小都是都是EXACTLY
//        boolean measureSpecMathcParent = MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY;

        //暂时不知道怎么用,必须测量子控件的高度
//        boolean isNeedMeasureChildHeight = false;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.setClickable(true);

            if (childView.getVisibility() != View.GONE) {
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                mHeight = Math.max(childView.getMeasuredHeight(), mHeight);
                //父控件是wrap_content 子控件又是match_parent
//                if (measureSpecMathcParent && lp.height == LayoutParams.MATCH_PARENT) {
//                    isNeedMeasureChildHeight = true;
//                }

                if (i > 0) {
                    mRightMenuWidths += childView.getMeasuredWidth();
                } else {
                    //第一个子控件为内容区域
                    mContentView = childView;
                    contentWidth = childView.getMeasuredWidth();
                }
            }
        }

        //重新测量父控件 这里只要是为了
        setMeasuredDimension(contentWidth + getPaddingLeft() + getPaddingRight(), mHeight + getPaddingTop() + getPaddingBottom());

        //滑动位置的临界值，如果是40%就打开
        mLimit = mRightMenuWidths * 4 / 10;//滑动判断的临界值

//        if (isNeedMeasureChildHeight) {
//            forceUniformHeight(childCount, widthMeasureSpec);
//        }
    }

    /**
     * 重新计算高度，暂时没有发现这个方法用处
     */
    private void forceUniformHeight(int childCount, int widthMeasureSpec) {
        int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY);
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                if (layoutParams.height == LayoutParams.MATCH_PARENT) {
                    int oldWidth = layoutParams.width;
                    layoutParams.width = childView.getMeasuredWidth();
//                  measureChildWithMargins跟measureChild的区别就是父控件支不支持margin属性
                    measureChildWithMargins(childView, widthMeasureSpec, 0, uniformMeasureSpec, 0);
                    layoutParams.width = oldWidth;
                }
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 处理控件排版问题
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = getPaddingLeft();
        int top = getPaddingTop();

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                if (i == 0) {
                    childView.layout(left, top, left + childView.getMeasuredWidth(), top + childView.getMeasuredHeight());
                    left += childView.getMeasuredWidth();
                } else {
                    //暂时默认往左边滑动
                    childView.layout(left, top, left + childView.getMeasuredWidth(), top + childView.getMeasuredHeight());
                    left += childView.getMeasuredWidth();
                }
            }
        }
    }

    //上一次的xy
    private PointF mLastP = new PointF();
    private PointF mFirstP = new PointF();
    private static MySwipeMenuLayout mViewCache;


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //是监听速度的
        acquireVelocityTracker(ev);
        VelocityTracker velocityTracker = mVelocityTracker;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //相对于父控件的位置和相对于自己的位置
                float downX = ev.getRawX();
                float downY = ev.getRawY();
                mLastP.set(downX, downY);
                mFirstP.set(downX, downY);

                if (null != mViewCache) {
                    if (mViewCache != this) {
                        mViewCache.smoothClose();
                    }
                    //请求父控件不要拦截事件，这样父控件就不会处理事件，代表父控件不会有滑动效果
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                mPointerId = ev.getPointerId(0);
//                float x = ev.getX();
//                float y = ev.getY();
//                Log.e(TAG, "dispatchTouchEvent: getRawX = " + downX + " ,getRawY = " + downY);
//                Log.e(TAG, "dispatchTouchEvent: getX = " + x + " ,getY = " + y);
                break;
            case MotionEvent.ACTION_MOVE:
                float gap = mLastP.x - ev.getRawX();
                //认为是左右的滑动事件
                if (Math.abs(gap) > 10 || Math.abs(getScrollX()) > 10) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                scrollBy((int) (gap), 0);//滑动使用 scrollBy

                //////////////////暂时没有左右区分//////////////////
                //越界修正
                if (getScrollX() < 0) {
                    scrollTo(0, 0);
                }
                if (getScrollX() > mRightMenuWidths) {
                    scrollTo(mRightMenuWidths, 0);
                }

                mLastP.set(ev.getRawX(), ev.getRawY());

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                velocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
                final float velocityX = velocityTracker.getXVelocity(mPointerId);
                if (Math.abs(velocityX) > 1000) {//滑动速度超过阈值
                    if (velocityX < -1000) {
//                    if (isLeftSwipe) {//左滑
                        //平滑展开Menu
                        smoothExpand();

//                    } else {
//                        平滑关闭Menu
//                        smoothClose();
//                    }
                    } else {
//                    if (isLeftSwipe) {//左滑
                        // 平滑关闭Menu
                        smoothClose();
//                    } else {
                        //平滑展开Menu
//                        smoothExpand();

//                    }
                    }
                } else {
                    if (Math.abs(getScrollX()) > mLimit) {//否则就判断滑动距离
                        //平滑展开Menu
                        smoothExpand();
                    } else {
                        // 平滑关闭Menu
                        smoothClose();
                    }
                }

                //释放
                releaseVelocityTracker();

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //add by zhangxutong 2016 11 04 begin :
            // fix 长按事件和侧滑的冲突。
            case MotionEvent.ACTION_MOVE:
                //屏蔽滑动时的事件，拦截滑动事件，传递点击事件
                if (Math.abs(ev.getRawX() - mFirstP.x) > mScaleTouchSlop) {
                    return true;
                }
                break;
            //add by zhangxutong 2016 11 04 end
            case MotionEvent.ACTION_UP:
                //add by zhangxutong 2016 11 03 begin:
                // 判断手指起始落点，如果距离属于滑动了，就屏蔽一切点击事件。 说白了就是不让事件传递下去
                //add by zhangxutong 2016 11 03 end
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void releaseVelocityTracker() {
        if (null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * 关闭
     */
    private void smoothClose() {
        mViewCache = null;

        //2016 11 13 add 侧滑菜单展开，屏蔽content长按
        if (null != mContentView) {
            mContentView.setLongClickable(true);
        }

        cancelAnim();
        mCloseAnim = ValueAnimator.ofInt(getScrollX(), 0);
        mCloseAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scrollTo((Integer) animation.getAnimatedValue(), 0);
            }
        });
        mCloseAnim.setInterpolator(new AccelerateInterpolator());
        mCloseAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                isExpand = false;

            }
        });
        mCloseAnim.setDuration(300).start();
    }

    /**
     * 每次执行动画之前都应该先取消之前的动画
     */
    private void cancelAnim() {
        if (mCloseAnim != null && mCloseAnim.isRunning()) {
            mCloseAnim.cancel();
        }
        if (mExpandAnim != null && mExpandAnim.isRunning()) {
            mExpandAnim.cancel();
        }
    }

    /**
     * 打开
     */
    public void smoothExpand() {
        //Log.d(TAG, "smoothExpand() called" + this);
        /*mScroller.startScroll(getScrollX(), 0, mRightMenuWidths - getScrollX(), 0);
        invalidate();*/
        //展开就加入ViewCache：
        mViewCache = MySwipeMenuLayout.this;

        //Log.d(TAG, "smoothExpand() called" + this);
        /*mScroller.startScroll(getScrollX(), 0, mRightMenuWidths - getScrollX(), 0);
        invalidate();*/
        //展开就加入ViewCache：

        //2016 11 13 add 侧滑菜单展开，屏蔽content长按
        if (null != mContentView) {
            mContentView.setLongClickable(false);
        }

        cancelAnim();
//        mExpandAnim = ValueAnimator.ofInt(getScrollX(), isLeftSwipe ? mRightMenuWidths : -mRightMenuWidths);
        mExpandAnim = ValueAnimator.ofInt(getScrollX(), mRightMenuWidths);
        mExpandAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scrollTo((Integer) animation.getAnimatedValue(), 0);
            }
        });
        mExpandAnim.setInterpolator(new OvershootInterpolator());
        mExpandAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                isExpand = true;
            }
        });
        mExpandAnim.setDuration(300).start();
    }

    /**
     * @param event 向VelocityTracker添加MotionEvent
     * @see VelocityTracker#obtain()
     * @see VelocityTracker#addMovement(MotionEvent)
     */
    private void acquireVelocityTracker(final MotionEvent event) {
        if (null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    //每次ViewDetach的时候，判断一下 ViewCache是不是自己，如果是自己，关闭侧滑菜单，且ViewCache设置为null，
    // 理由：1 防止内存泄漏(ViewCache是一个静态变量)
    // 2 侧滑删除后自己后，这个View被Recycler回收，复用，下一个进入屏幕的View的状态应该是普通状态，而不是展开状态。
    @Override
    protected void onDetachedFromWindow() {
        if (this == mViewCache) {
            mViewCache.smoothClose();
            mViewCache = null;
        }
        super.onDetachedFromWindow();
    }

}
