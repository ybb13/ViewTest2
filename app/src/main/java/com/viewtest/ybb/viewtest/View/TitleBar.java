package com.viewtest.ybb.viewtest.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viewtest.ybb.viewtest.R;


/**
 * Created by Administrator on 2017/8/12.
 */

public class TitleBar extends RelativeLayout {

    private ImageView limageView;
    private ImageView rimageView;
    private TextView ctextview;
    private RelativeLayout mrelativeLayout;

    private int mColor= Color.BLUE;
    private int mTextColor= Color.WHITE;
    private String mtitle ;

    public TitleBar(Context context) {
        super(context);
        //init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs ,R.styleable.TitleBar);
        mColor = a.getColor(R.styleable.TitleBar_title_bg,Color.BLUE);
        mTextColor = a.getColor(R.styleable.TitleBar_title_text_color,Color.BLACK);
        mtitle = a.getString(R.styleable.TitleBar_title_bar_text);
        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.view_customtitle,this,true);
        limageView = (ImageView)findViewById(R.id.iv_titlebar_left);
        ctextview = (TextView)findViewById(R.id.tv_titlebar_title);
        rimageView = (ImageView)findViewById(R.id.iv_titlebar_right);
        mrelativeLayout = (RelativeLayout)findViewById(R.id.layout_titlebar_rootlayout);

        mrelativeLayout.setBackgroundColor(mColor);
        ctextview.setTextColor(mTextColor);
        setTile(mtitle);
    }

    public void setTile(String title){
        if(!TextUtils.isEmpty(title))
        ctextview.setText(title);
    }

    public void setLimageView(OnClickListener onClickListener){
        limageView.setOnClickListener(onClickListener);
    }

    public void setRimageViewt(OnClickListener onClickListener){
        rimageView.setOnClickListener(onClickListener);
    }

}
