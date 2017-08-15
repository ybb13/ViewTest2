package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.viewtest.ybb.viewtest.Util.DensityUtil;

/**
 * Created by Administrator on 2017/8/15.
 */

public class PanelView extends View {

    private int mWidth,mHeight;
    private Context mContext;
    private int mArcColor;
    private int maxStrokeWidth = 4;
    private Paint p ,p1;
    int mProgress = 0 ,mp2 =0 ;


    public PanelView(Context context) {
        super(context);
    }

    public PanelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public PanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        p = new Paint();
        p.setStrokeWidth(maxStrokeWidth);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);

        p1 = new Paint();
        p1.setStrokeWidth(50);
        p1.setAntiAlias(true);
        p1.setStyle(Paint.Style.STROKE);
        p1.setColor(Color.RED);
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
            mWidth = DensityUtil.dip2px(mContext,200f);
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }else {
            mHeight = DensityUtil.dip2px(mContext,200f);
        }
        Log.e("wing",mWidth+"");
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setStrokeWidth(10);
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(0 + 10, 0 + 10, mWidth - 10, mHeight - 10);
        canvas.drawArc(rectF, 180, 360 * mProgress / 100, false, p);

        if(mProgress <= 100 ){
            mProgress=mProgress+5;
            postInvalidateDelayed(10);
        }else{
            if(mp2<100){
                mp2=mp2+5;
                canvas.drawLine(10,10,(mWidth - 10)*mp2 / 100,(mHeight - 10)*mp2 / 100,p);
                canvas.drawLine(10,mHeight - 10 -(mHeight - 10)*mp2 / 100,(mWidth - 10)*mp2 / 100,10,p);
                postInvalidateDelayed(10);
            }else{
                canvas.drawLine(10,10,mWidth - 10,mHeight - 10,p);
                canvas.drawLine(10,mHeight - 10,mWidth - 10,10,p);
            }


        }


    }
}
