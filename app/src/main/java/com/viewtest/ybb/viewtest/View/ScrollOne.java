package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/8/12.
 */

public class ScrollOne extends View {

    private Paint mPaint;
    private int lastx ;
    private int lasty ;

    public ScrollOne(Context context) {
        super(context);
        init();
    }

    public ScrollOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth((float)2);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int diff = x- lastx;
                scrollBy(-diff,0);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                scrollBy(10,10);
                invalidate();
                break;
        }
        lastx = x;
        lastx = y;
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        canvas.drawRect(0,0,width,height,mPaint);
    }
}
