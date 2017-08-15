package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/14.
 */

public class SimpleLineChart extends View {

    private Paint mPaint;
    private Map map;
    private int[] mPointX;
    private int[] mPointY;
    private int width = 0, height = 0;

    public SimpleLineChart(Context context) {
        super(context);
    }

    public SimpleLineChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleLineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(4f);
        mPaint.setTextSize(120);
        mPaint.setColor(Color.parseColor("#3F51B5"));
        mPointX = new int[]{10, 20, 30, 40, 50};
        mPointY = new int[]{10, 20, 30, 40, 50};
    }

    public void SetDate(Map map) {
        this.map = map;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        //画Y轴坐标
        int baseLineX = 0 ;
        int baseLineY = 200;

        //画基线
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 4000, baseLineY, paint);

        //写文字
        paint.setColor(Color.GREEN);
        paint.setTextSize(240); //以px为单位
        canvas.drawText("harvgc\'s blog", baseLineX, baseLineY, paint);

        //画X轴坐标


        super.onDraw(canvas);
    }
}
