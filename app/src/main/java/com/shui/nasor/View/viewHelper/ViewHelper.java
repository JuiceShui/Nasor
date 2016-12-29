package com.shui.nasor.View.viewHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者： max_Shui on 2016/11/6.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class ViewHelper implements IViewHelper {
    private View view;//传人的布局
    private View currentView;//当前的布局
    private int viewIndex;//布局的位置
    private ViewGroup parentView;//父容器
    private ViewGroup.LayoutParams params;//父容器的布局参数
    public ViewHelper(View view) {
        super();
        this.view=view;
    }
    private void init()
    {   //获取布局参数
        params=view.getLayoutParams();
        if (view.getParent()!=null)
        {
            //获取父容器
            parentView= (ViewGroup) view.getParent();
        }
        else
        {
            //如果没有父容器
            parentView= (ViewGroup) view.getRootView().findViewById(android.R.id.content);
        }
        int count=parentView.getChildCount();
        //获取当前布局的位置并赋值
        for (int index=0;index<count;index++)
        {
            if (view==parentView.getChildAt(index))
            {
                viewIndex=index;
                break;
            }
        }
        currentView=view;
    }

    /**
     * 加载布局
     * @param LayoutResID
     * @return
     */
    @Override
    public View inflate(int LayoutResID) {
        return LayoutInflater.from(getContext()).inflate(LayoutResID,null);
    }

    /**
     * 获取布局
     * @return
     */
    @Override
    public View getView() {
        return view;
    }

    /**
     * 获取当前布局
     * @return
     */
    @Override
    public View getCurrentView() {
        return currentView;
    }

    /**
     * 展示布局
     * @param view
     */
    @Override
    public void showLayout(View view) {
        if (parentView==null)
        {
            init();
        }
        this.currentView=view;
        //如果已经是那个view就不再进行替换
        if (currentView!=parentView.getChildAt(viewIndex))
        {
            ViewGroup parent= (ViewGroup) view.getParent();
            if (parent!=null)
            {
                parent.removeView(view);
            }
            parentView.removeViewAt(viewIndex);
            parentView.addView(view,viewIndex,params);
        }
    }

    /**
     * 恢复布局
     */
    @Override
    public void restoreView() {
        showLayout(view);
    }

    /**
     * 获取布局上下文
     * @return
     */
    @Override
    public Context getContext() {
        return view.getContext();
    }
}
