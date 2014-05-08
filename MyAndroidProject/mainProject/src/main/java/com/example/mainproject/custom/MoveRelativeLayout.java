package com.example.mainproject.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by luh on 2014/5/7.
 */
public class MoveRelativeLayout extends RelativeLayout {
    private static final int SNAP_VELOCITY = 600;
    Scroller scroller;
    /**
     * 速度追踪对象
     */
    private VelocityTracker velocityTracker;
    /**
     * 认为是用户滑动的最小距离
     */
    private int mTouchSlop;
    /**
     * 是否响应滑动，默认为不响应
     */
    private boolean isSlide = false;
    /**
     * 手指按下X的坐标
     */
    private int downY;
    /**
     * 手指按下Y的坐标
     */
    private int downX;

    public MoveRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(getContext());
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        setClickable(true);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * Pass the touch screen motion event down to the target view, or this view if it is the target.
     *
     * @param event
     * @return True if the event was handled by the view, false otherwise.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                // 假如scroller滚动还没有结束，我们直接返回
                if (!scroller.isFinished()) {
                    return super.dispatchTouchEvent(event);
                }
                addVelocityTracker(event);
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                addVelocityTracker(event);
                Log.e("tag", "Math.abs(getScrollVelocity()" + Math.abs(getScrollVelocity()));
                if (Math.abs(getScrollVelocity()) > SNAP_VELOCITY
                        || (Math.abs(event.getX() - downX) > mTouchSlop && Math
                        .abs(event.getY() - downY) < mTouchSlop)) {
                    isSlide = true;
                }
                break;
            }
            case MotionEvent.ACTION_UP:
                Log.e("tag", "dispatchTouchEvent action up");
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isSlide) {
            return super.onTouchEvent(ev);
        }
        final int action = ev.getAction();
        int x = (int) ev.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = downX - x;
                double toX = (-1 * getScrollX()) + x - downX;
                if (toX <= -1 * dpToPx(100)) {
                    toX = -1 * dpToPx(100);
                    deltaX = (int) (-1 * (toX - (-1 * getScrollX())));
                } else if (toX > 0) {
                    toX = 0;
                    deltaX = (int) (-1 * (toX - (-1 * getScrollX())));
                }
                downX = x;
                // 手指拖动itemView滚动, deltaX大于0向左滚动，小于0向右滚
                scrollBy(deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                //open menu
                //getScrollX必须*-1 后使用
                int velocityX = getScrollVelocity();
                if (velocityX > SNAP_VELOCITY) {
                    scrollRight();
                } else if (velocityX < -SNAP_VELOCITY) {
                    scrollLeft();
                } else if ((-1 * getScrollX()) < (-1 * dpToPx(50))) {
                    scrollLeft();
                } else {
                    scrollRight();
                }
                recycleVelocityTracker();
                isSlide = false;
                break;
        }


        //否则直接交给ListView来处理onTouchEvent事件
        return super.onTouchEvent(ev);
    }

    private void scrollLeft() {
        int moveValue = -1 * ((-1 * dpToPx(100)) - (-1 * getScrollX()));
        scroller.startScroll(getScrollX(), getScrollY(), moveValue, getScrollY(), Math.abs(moveValue) * 2);
        //必须加入invalidate否则不会有滚动效果
        invalidate();
    }

    private void scrollRight() {
        //close menu
        int moveValue = -1 * (0 - (-1 * getScrollX()));
        scroller.startScroll(getScrollX(), getScrollY(), moveValue, getScrollY(), Math.abs(moveValue) * 2);
        //必须加入invalidate否则不会有滚动效果
        invalidate();
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    /**
     * 添加用户的速度跟踪器
     *
     * @param event
     */
    private void addVelocityTracker(MotionEvent event) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }

        velocityTracker.addMovement(event);
    }

    /**
     * 移除用户速度跟踪器
     */
    private void recycleVelocityTracker() {
        if (velocityTracker != null) {
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }

    /**
     * 获取X方向的滑动速度,大于0向右滑动，反之向左
     *
     * @return
     */
    private int getScrollVelocity() {
        //The units you would like the velocity in. A value of 1 provides pixels per millisecond, 1000 provides pixels per second, etc.
        velocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) velocityTracker.getXVelocity();
        return velocity;
    }

}
