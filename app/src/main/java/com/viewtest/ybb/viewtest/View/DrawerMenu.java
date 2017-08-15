package com.viewtest.ybb.viewtest.View;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/8/14.
 */

public class DrawerMenu extends ViewGroup {

    private View view_first;
    private static int num = 0;
    private List<View> viewGroup;
    private Boolean mIsChanged = false;
    int startY = 0;

    public DrawerMenu(Context context) {
        super(context);
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        viewGroup = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int count = getChildCount();
        if(count>=0){
            for (int i=0;i<count;i++) {
                measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MeasureFirstView();
        int count = getChildCount();

        for (int i=0;i<count-1;i++){
            View child = getChildAt(i+1);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            startY = startY-height;
            child.layout(0,startY,width,startY+height);
            viewGroup.add(child);
            if(num<count){
                child.setVisibility(View.GONE);
                num++;
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("2222222222","onTouchEvent");
        return super.onTouchEvent(event);
    }

    private void MeasureFirstView() {
        view_first = getChildAt(0);
        view_first.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    DisplayDrawer();
                }
                return true;
            }
        });
        startY = getMeasuredHeight()-view_first.getMeasuredHeight();
        view_first.layout(0,startY,view_first.getMeasuredWidth(),getMeasuredHeight());
    }

    private void DisplayDrawer() {
        int count = getChildCount();
        if(mIsChanged){
            for (int i=0;i<count-1;i++){
                View child = getChildAt(i+1);
                ObjectAnimator animator = ObjectAnimator.ofFloat(child,"TranslationX" ,0,-child.getMeasuredWidth());
                animator.setDuration((i+1)*300);
                animator.start();

                mIsChanged =false;
            }

        }else{
            for (int i=0;i<count-1;i++){
                View child = getChildAt(i+1);
                //child.setVisibility(View.VISIBLE);
                ObjectAnimator animator = ObjectAnimator.ofFloat(child,"TranslationX" ,-child.getMeasuredWidth(),0);
                animator.setDuration((i+1)*300);
                animator.start();
//                TranslateAnimation ta = new TranslateAnimation(-child.getMeasuredWidth(), 0, 0, 0);
//                ta.setDuration(1000 + i * 100);
//                child.startAnimation(ta);
                child.setVisibility(VISIBLE);
                mIsChanged = true;
            }
        }
    }
}
