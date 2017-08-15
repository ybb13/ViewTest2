package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/8/12.
 */

public class HorizontalView extends ViewGroup {

    int lastInterceptX;
    int lastInterceptY;
    int lastX;
    int lastY;

    private Scroller scroller;
    private int currentIndex;
    private int childWidth;

    public HorizontalView(Context context) {
        super(context);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                //如果动画还没有执行完成,则打断
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                //用户想水平滑动的，所以拦截
                //水平方向距离长  MOVE中返回true一次,后续的MOVE和UP都不会收到此请求
                if (Math.abs(deltaX) - Math.abs(deltaY) > 0) {
                    intercept = true;
                    Log.i("wangshu", "intercept = true");
                } else {
                    intercept = false;
                    Log.i("wangshu", "intercept = false");
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        System.out.println("x="+x);
        System.out.println("y="+y);
        System.out.println("rawx="+event.getRawX());
        System.out.println("rway="+event.getRawY());
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX; //跟随手指滑动
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex * childWidth;
                if (Math.abs(distance) > childWidth / 2) {
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                }
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ?
                        getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childWidth, 0);
                break;
        }
        lastX = x;
        lastY = y;

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    //弹性滑动到指定位置
    public void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(),
                destY - getScrollY(), 1000);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int hmode = MeasureSpec.getMode(heightMeasureSpec);
        int wsize = MeasureSpec.getSize(widthMeasureSpec);
        int hsize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (wmode == MeasureSpec.AT_MOST && hmode == MeasureSpec.AT_MOST) {
            int width = getChildAt(0).getMeasuredWidth();
            int height = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(width * getChildCount(), height);
        } else if (wmode == MeasureSpec.AT_MOST) {
            int width = getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(width * getChildCount(), hsize);
        } else if (hmode == MeasureSpec.AT_MOST) {
            int height = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(wsize * getChildCount(), height);
        }

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int counts = getChildCount();
        int left = 0;
        View child;
        for (int m = 0; m < counts; m++) {
            child = getChildAt(m);
            int width = child.getMeasuredWidth();
            childWidth = width;
            int height = child.getMeasuredHeight();
            child.layout(left, 0, left + width, height);
            left += width;
        }
    }
}
