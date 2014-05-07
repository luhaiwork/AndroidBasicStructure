package com.example.mainproject.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.example.mainproject.R;

/**
 * Created by Administrator on 2014/5/7.
 */
public class MoveRelativeLayout extends RelativeLayout {
    Scroller scroller;
    /**
     * 处理我们拖动ListView item的逻辑
     */
    int downX = 0;
    RelativeLayout rl_test = null;

    public MoveRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(getContext());
        setClickable(true);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            Log.e("tag", "scroll scroller.getCurrX() :" + scroller.getCurrX());
            rl_test.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        int x = (int) ev.getX();
        rl_test = (RelativeLayout) findViewById(R.id.rl_test);
        // 假如scroller滚动还没有结束，我们直接返回
//        if (!scroller.isFinished()) {
//           return false;
//        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = downX - x;
                double toX = (-1 * rl_test.getScrollX()) + x - downX;
                if (toX <= -1 * dpToPx(100)) {
                    toX = -1 * dpToPx(100);
                    deltaX = (int) (-1 * (toX - (-1 * rl_test.getScrollX())));
                } else if (toX > 0) {
                    toX = 0;
                    deltaX = (int) (-1 * (toX - (-1 * rl_test.getScrollX())));
                }
                downX = x;
                // 手指拖动itemView滚动, deltaX大于0向左滚动，小于0向右滚
                rl_test.scrollBy(deltaX, 0);
//                return true;  //拖动的时候ListView不滚动
                break;
            case MotionEvent.ACTION_UP:
                //open menu
//                if ((-1 * rl_test.getScaleX()) < (-1 * dpToPx(50))) {
//                    scroller.startScroll(rl_test.getScrollX(),rl_test.getScrollY(),((-1 * dpToPx(100))),rl_test.getScrollY(), 0);
//                } else {
//                    //close menu
//                    scroller.startScroll(rl_test.getScrollX(),rl_test.getScrollY(),0,rl_test.getScrollY(), 0);
//                }
                scroller.startScroll(rl_test.getScrollX(), rl_test.getScrollY(), -100, 0, 1000);
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
