package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.viewtest.ybb.viewtest.R;
import com.viewtest.ybb.viewtest.Util.DensityUtil;


/**
 * Created by Administrator on 2017/8/14.
 */

public class WaveView extends View {

    private static final int MODE_CIRCLE = -1;
    private static final int MODE_TRIANGLE = -2;
    private Paint mPaint;
    private Context mContext;
    private int width,height;
    private float mRectWidth,mRectHeight;
    private float mScale;
    private int count;
    private int mode;
    private int color;

    public WaveView(Context context) {
        super(context);
        mContext = context;
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context,attrs);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context,attrs);
    }

    private void init(Context mContext,AttributeSet mAttrs) {
        TypedArray a = mContext.obtainStyledAttributes(mAttrs,R.styleable.WaveView);
        count = a.getInt(R.styleable.WaveView_count,10);
        mode = a.getInt(R.styleable.WaveView_mode,-2);
        color = a.getColor(R.styleable.WaveView_color, Color.parseColor("#2C97DE"));
        a.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#2C97DE"));
        mScale = 0.8f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST){
            width = DensityUtil.dip2px(mContext,300);

        }else if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }

        if(heightMode == MeasureSpec.AT_MOST){
            height = DensityUtil.dip2px(mContext,200);
        }else if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }

        mRectHeight = height*mScale;
        mRectWidth = width*mScale;

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        float LRpadding = (width-mRectWidth)/2;
        float TBpadding = (height-mRectHeight)/2;

        canvas.drawRect(LRpadding,TBpadding,width-LRpadding,height-TBpadding,mPaint);

        float mheight = mRectHeight/count;
        float mwidht = mheight/2;

        if(mode == MODE_TRIANGLE){
            Path mPathL = new Path();
            mPathL.moveTo(LRpadding,TBpadding);
            for (int i=0;i<count;i++){
                //绘制左边的波浪
                mPathL.lineTo(LRpadding-mwidht,TBpadding+mheight*i+mheight/2);
                mPathL.lineTo(LRpadding,TBpadding+mheight*i+mheight);
            }
            mPathL.close();
            canvas.drawPath(mPathL,mPaint);

            Path mPathR = new Path();
            mPathR.moveTo(mRectWidth+LRpadding,TBpadding);
            for (int j=0;j<count;j++){
                //绘制右边的波浪
                mPathR.lineTo(mRectWidth+LRpadding+mwidht,TBpadding+mheight*j+mheight/2);
                mPathR.lineTo(mRectWidth+LRpadding,TBpadding+mheight*j+mheight);
            }
            mPathR.close();
            canvas.drawPath(mPathR,mPaint);
        }else if(mode == MODE_CIRCLE){
            //绘制波浪
            for(int i=0;i<count;i++){
                canvas.drawCircle(LRpadding,TBpadding+mheight*i+mheight/2,mheight/2,mPaint);
                canvas.drawCircle(mRectWidth+LRpadding,TBpadding+mheight*i+mheight/2,mheight/2,mPaint);
            }
        }


        super.onDraw(canvas);
    }
}
