package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.viewtest.ybb.viewtest.Util.DensityUtil;

/**
 * Created by Administrator on 2017/8/15.
 */

public class CardiographView extends View {

    private int mWidth,mHeight;
    Context mContext ;
    private Paint mCardPaint;

    public CardiographView(Context context) {
        super(context);
    }

    public CardiographView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext =context;
        init();
    }

    public CardiographView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext =context;
        init();
    }

    private void init() {
        mCardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCardPaint.setStrokeWidth(3f);
        mCardPaint.setColor(Color.parseColor("#7CCD7C"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }else {
            mWidth = DensityUtil.dip2px(mContext,800f);
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }else {
            mHeight = DensityUtil.dip2px(mContext,800f);
        }
        Log.e("wing",mWidth+"");
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        drawGrid(canvas);
    }

    private void drawGrid(Canvas canvas) {

        int mGridWidth = mWidth/15;
        int mGridHeight = mHeight/25;

        for(int i =0;i<16;i++){
            canvas.drawLine(mGridWidth*i,0,mGridWidth*i,mHeight,mCardPaint);
        }
        for(int i = 0;i<26 ;i++){
            canvas.drawLine(0,mGridHeight*i,mWidth,mGridHeight*i,mCardPaint);
        }

        int mSGridWidth = mGridWidth/5;
        int mSGridHeight = mGridHeight/5;

        for(int i =0;mWidth>mSGridWidth*i;i++){
            canvas.drawLine(mSGridWidth*i,0,mSGridWidth*i,mHeight,mCardPaint);
        }
        for(int i = 0;mHeight>mSGridHeight*i ;i++){
            canvas.drawLine(0,mSGridHeight*i,mWidth,mSGridHeight*i,mCardPaint);
        }

    }


}
