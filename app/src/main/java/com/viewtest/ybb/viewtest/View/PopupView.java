package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/14.
 */

public class PopupView extends View {

    private Paint mPaint;
    private int mRectWidth;
    private int mRectHeight;
    private float mSround;
    private float mRectPercent;

    public PopupView(Context context) {
        super(context);
    }

    public PopupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PopupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#2C97DE"));
        mPaint.setStyle(Paint.Style.FILL);
        mRectPercent = 0.8f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mwidth = MeasureSpec.getMode(widthMeasureSpec);
        int mheight = MeasureSpec.getMode(heightMeasureSpec);
        int swidth = MeasureSpec.getSize(widthMeasureSpec);
        int sheight = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0,height = 0;
        if (mwidth == MeasureSpec.EXACTLY){
            width = swidth;
            mRectWidth = (int)(width*mRectPercent);
        }
        if (mheight == MeasureSpec.EXACTLY){
            height = sheight;
            mRectHeight = (int)(height*mRectPercent+0.1);
        }
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(new RectF(0,0,mRectWidth,mRectHeight),10,10,mPaint);
        mSround = mRectWidth/5;
        Path mPath = new Path();
        mPath.moveTo(mRectWidth/2-mSround/2,mRectHeight);
        mPath.lineTo(mRectWidth/2,mRectHeight+mSround);
        mPath.lineTo(mRectWidth/2+mSround/2,mRectHeight);
        mPath.close();
        canvas.drawPath(mPath,mPaint);

        super.onDraw(canvas);
    }
}
