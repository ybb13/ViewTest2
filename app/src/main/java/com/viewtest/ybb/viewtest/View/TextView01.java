package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/12.
 */

public class TextView01 extends android.support.v7.widget.AppCompatTextView {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TextView01(Context context) {
        super(context);
        init();
    }

    public TextView01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextView01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth((float)1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0,height/2,width,height/2,mPaint);
    }
}
