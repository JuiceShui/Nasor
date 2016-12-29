package com.shui.nasor.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 作者： max_Shui on 2016/12/29.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class ScrollViewY extends ScrollView{
    private  float downX,downY,upX,upY;
    private boolean stateVertical,isScrolledToTop,isScrolledToBottom;
    private boolean stateDown,stateUp;//向下，向上
    public ScrollViewY(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewY(Context context) {
        super(context);
    }

    public ScrollViewY(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_DOWN)
        {
            downX=ev.getX();
            downY=ev.getY();
            System.out.println("downX"+downX+"|||  downY"+downY);
        }
        if (ev.getAction()==MotionEvent.ACTION_MOVE)
        {
            upX=ev.getX();
            upY=ev.getY();
            System.out.println("upX"+upX+"|||  upY"+upY);
            if (Math.abs(upX-downX)>Math.abs(upY-downY))
            {
                stateVertical=false;//左右
            }
            if (downY-upY>0)//向下
            {
                stateVertical=true;
                stateDown=true;
            }
            if (downY-upY<0)
            {
                //向上
                stateVertical=true;
                stateUp=true;
            }
        }
        System.out.println("state--"+stateVertical);
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (stateVertical&&!isScrolledToTop)
        {
            return true;
        }
        if (stateVertical&&isScrolledToTop&&stateDown)
        {
            requestDisallowInterceptTouchEvent(false);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (android.os.Build.VERSION.SDK_INT < 9) {  // API 9及之后走onOverScrolled方法监听
            if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
                isScrolledToTop = true;
                isScrolledToBottom = false;
            } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
                // 小心踩坑2: 这里不能是 >=
                // 小心踩坑3（可能忽视的细节2）：这里最容易忽视的就是ScrollView上下的padding　
                isScrolledToBottom = true;
                isScrolledToTop = false;
            } else {
                isScrolledToTop = false;
                isScrolledToBottom = false;
            }
            System.out.println("isTop"+isScrolledToTop);
        }}
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            isScrolledToTop = clampedY;
            isScrolledToBottom = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = clampedY;
        }
        System.out.println("isTop"+isScrolledToTop);
    }

}
