package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.viewtest.ybb.viewtest.Util.DensityUtil;

/**
 * Created by Administrator on 2017/8/15.
 */

public class CircleProgressBar extends View {

    private Paint mPaint;
    private int width, height;
    private Context mContext;
    private int reaslut;
    private int centerx,centery;
    private float MaxRadio ,MidRadio;
    private float radius;
    private float ringWidth;
    private int mProgress =0;
    private Paint fontP;


    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        fontP = new Paint(Paint.ANTI_ALIAS_FLAG);
       // fontP.setStrokeWidth(0);
        fontP.setColor(Color.BLUE);
        fontP.setStyle(Paint.Style.STROKE);
        fontP.setTextSize(60);
        fontP.setTypeface(Typeface.DEFAULT);

        mContext = context;
        reaslut = DensityUtil.dip2px(mContext,200);
        MaxRadio = 3;
        ringWidth = 20;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取宽高的mode和size
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量宽度
        if (widthMode == MeasureSpec.AT_MOST) {
            width = reaslut;
        } else {
            width = widthSize;
        }

        //测量高度
        if (heightMode == MeasureSpec.AT_MOST) {
            height = reaslut;
        } else {
            height = heightSize;
        }

        //设置测量的宽高值
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawArc(canvas);
        drawText(canvas);
    }

    private void drawCircle(Canvas canvas) {

        centerx = width/2;
        centery = height/2;
        radius = (int) (centerx - ringWidth / 2);
        mPaint.setStrokeWidth(ringWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerx,centery,radius,mPaint);

    }


    private void drawArc(Canvas canvas) {
        Paint p = new Paint();
        p.setStrokeWidth(ringWidth);
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);
        //mPaint.setStrokeCap(Paint.Cap.ROUND);
        if(mProgress<100){
            canvas.drawArc(new RectF(centerx-radius,centery-radius,centerx+radius,centery+radius),-90,360*mProgress/100,false,p);
            mProgress+=5;
            postInvalidateDelayed(200);
        }else{
            canvas.drawArc(new RectF(centerx-radius,centery-radius,centerx+radius,centery+radius),-90,360,false,p);
        }

    }

    private void drawText(Canvas canvas) {


        Paint.FontMetrics fm = fontP.getFontMetrics();
        float fontHeight = fm.ascent+fm.descent;
        float fontWidth = fontP.measureText(mProgress+""+"%");
        canvas.drawText(mProgress+"%",centerx-fontWidth/2,centery-fontHeight/2,fontP);


    }

}
