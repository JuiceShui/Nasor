package com.shui.nasor.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * 作者： max_Shui on 2016/12/29.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class CustomScrollView extends ScrollView {
private float preX;
private float preY;
private float touchSlop;
private boolean isViewPagerDragged;
public CustomScrollView(Context context) {
        this(context, null);
        }
public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
        }
/**
 * 在onInterceptTouchEvent()方法里，
 * 如果水平移动距离大于竖直移动距离，ScrollView不拦截这个事件
 * @param ev
 * @return
 */
@Override
public boolean onInterceptTouchEvent(MotionEvent ev) {
        float currentX=ev.getX();
        float currentY=ev.getY();
        switch (ev.getAction()){
        case MotionEvent.ACTION_DOWN:
        preX=currentX;
        preY=currentY;
        isViewPagerDragged=false;
        break;
        case MotionEvent.ACTION_MOVE:
        if(isViewPagerDragged){
        return false;
        }
        float dx=Math.abs(preX-currentX);
        float dy=Math.abs(preY-currentY);
        if(dx>dy && dx>touchSlop){
        isViewPagerDragged=true;
        return false;
        }
        break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
        isViewPagerDragged=false;
        break;
        }
        return super.onInterceptTouchEvent(ev);
        }
}
