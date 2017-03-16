package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuThemeDetailEntity;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface ThemeDetailContract  {
    interface View extends BaseView
    {
        void showContent(ZhihuThemeDetailEntity entity);
    }
    interface Presenter extends BasePresenter<View>
    {
        void getData(int id,boolean isFirst);
        void insertRead(int id);
    }
}
