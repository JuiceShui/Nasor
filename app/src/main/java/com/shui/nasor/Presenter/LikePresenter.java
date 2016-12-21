package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Presenter.Contract.LikeContract;

import javax.inject.Inject;

/**
 * 作者： max_Shui on 2016/12/15.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class LikePresenter extends BaseRxPresenter<LikeContract.View> implements LikeContract.Presenter {
    RealmHelper realmHelper;
    @Inject
    public LikePresenter(RealmHelper realmHelper) {
        this.realmHelper = realmHelper;
    }

    @Override
    public void getData() {
        mView.showData(realmHelper.getLikeData());
    }

    @Override
    public void deleteData(String id) {
        realmHelper.deleteLike(id);
    }

    @Override
    public void changeTime(String id, long time, boolean isUp) {
        realmHelper.changeTime(id,time,isUp);
    }
}
