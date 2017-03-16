package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.Relax.TXTJokerEntity;

/**
 * 作者： max_Shui on 2016/12/15.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface TXTJokerContract {
    interface View extends BaseView
    {
        void showData(TXTJokerEntity entity);
        void showMore(TXTJokerEntity entity);
    }
    interface Presenter extends BasePresenter<View>
    {
        void getData(boolean isFirst);
        void getMore();
    }
}
