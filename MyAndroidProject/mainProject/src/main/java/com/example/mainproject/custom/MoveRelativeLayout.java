package com.example.mainproject.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by luh on 2014/5/7.
 */
public class MoveRelativeLayout extends RelativeLayout {
    Scroller scroller;
    /**
     * 处理我们拖动ListView item的逻辑
     */
    int downX = 0;

    public MoveRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(getContext());
        setClickable(true);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        int x = (int) ev.getX();
        // 假如scroller滚动还没有结束，我们直接返回
        if (scroller.computeScrollOffset()) {
            return false;
        }
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
                int moveValue = 0;
                if ((-1 * getScrollX()) < (-1 * dpToPx(50))) {
                    moveValue = -1 * ((-1 * dpToPx(100)) - (-1 * getScrollX()));
                    scroller.startScroll(getScrollX(), getScrollY(), moveValue, getScrollY(), Math.abs(moveValue) * 2);
                } else {
                    //close menu
                    moveValue = -1 * (0 - (-1 * getScrollX()));
                    scroller.startScroll(getScrollX(), getScrollY(), moveValue, getScrollY(), Math.abs(moveValue) * 2);
                }
                //必须加入invalidate否则不会有滚动效果
                invalidate();
                break;
        }


        //否则直接交给ListView来处理onTouchEvent事件
        return super.onTouchEvent(ev);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
