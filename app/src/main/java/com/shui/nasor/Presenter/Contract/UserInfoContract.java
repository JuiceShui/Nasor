package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.MyData.BombUserEntity;
import com.shui.nasor.Model.Bean.MyData.RealmUserEntity;

/**
 * 作者： max_Shui on 2016/12/27.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * 用户信息
 */


public interface UserInfoContract {
    interface View extends BaseView
    {
        /**
         * 展示网络信息
         * @param entity
         */
        void showNetData(BombUserEntity entity);

        /**
         * 展示本地信息
         * @param entity
         */
        void showLocalData(RealmUserEntity entity);
    }
    interface Presenter extends BasePresenter<View>
    {
        /**
         * 获取网络信息
         */
        void getNetData();

        /**
         * 获取本地信息
         */
        void getLocalData();
    }
}
