package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.Relax.IMGJokerEntity;

/**
 * 作者： max_Shui on 2016/12/16.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface IMGJokerContract {
    interface View extends BaseView
    {
        void showData(IMGJokerEntity entity);//
        void showNextPage(IMGJokerEntity entity);//展示下一页
        void showPrePage(IMGJokerEntity entity);
        void showNoPrePage();
    }
    interface Presenter extends BasePresenter<View>
    {
        void getData();
        void getNextData();
        void getPreData();
    }
}
