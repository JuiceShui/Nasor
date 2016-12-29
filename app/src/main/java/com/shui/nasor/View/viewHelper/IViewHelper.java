package com.shui.nasor.View.viewHelper;

import android.content.Context;
import android.view.View;

/**
 * 作者： max_Shui on 2016/11/6.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface IViewHelper {
    /**
     * 加载布局
     * @param LayoutResID
     * @return
     */
    View inflate(int LayoutResID);

    /**
     * 获取传人的布局
     * @return
     */
    View getView();

    /**
     * 获取当前展示的布局
     * @return
     */
    View getCurrentView();

    /**
     * 展示布局
     * @param view
     */
    void showLayout(View view);

    /**
     * 恢复布局
     */
    void restoreView();

    /**
     * 获取布局的上下文
     * @return
     */
    Context getContext();
}
