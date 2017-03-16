package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.Relax.GirlEntity;

/**
 * 作者： max_Shui on 2016/12/16.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface GirlContract {
    interface View extends BaseView
    {
        void showData(GirlEntity entity);
        void showMore(GirlEntity entity);
    }
    interface Presenter extends BasePresenter<View>
    {
        void getData(boolean isFirst);
        void getMore();
    }
}
