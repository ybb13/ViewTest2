package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.viewtest.ybb.viewtest.R;

/**
 * Created by Administrator on 2017/8/12.
 */

public class RectView extends View {

    private Paint mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor ;

    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        mColor = mTypedArray.getColor(R.styleable.RectView_rectview_color,Color.RED);
        mTypedArray.recycle();
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        mColor = mTypedArray.getColor(R.styleable.RectView_rectview_color,Color.RED);
        mTypedArray.recycle();
        init();
    }

    public void init(){
        mpaint.setColor(mColor);
        mpaint.setStrokeWidth((float)1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int lpading = getPaddingLeft();
        int rpadding = getPaddingRight();
        int tpadding = getPaddingTop();
        int bpadding = getPaddingBottom();

        int pwidht = width-lpading-rpadding;
        int pheight = height -tpadding-bpadding;

        canvas.drawRect(lpading,tpadding,pwidht+lpading,pheight+pheight,mpaint);
    }
}
