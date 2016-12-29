package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Model.Bean.MyData.BombUserEntity;
import com.shui.nasor.Model.Bean.MyData.RealmUserEntity;
import com.shui.nasor.Presenter.Contract.UserInfoContract;
import com.shui.nasor.Utils.SharedPreferenceUtils;

import javax.inject.Inject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * 作者： max_Shui on 2016/12/27.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class UserInfoPresenter extends BaseRxPresenter<UserInfoContract.View> implements UserInfoContract.Presenter{
    RealmHelper realmHelper;
    String objectID;
    @Inject
    public UserInfoPresenter(RealmHelper realmHelper) {
        this.realmHelper = realmHelper;
        objectID= SharedPreferenceUtils.getUser();
    }

    @Override
    public void getNetData() {
        BmobQuery<BombUserEntity> bmobQuery=new BmobQuery<>();
        bmobQuery.getObject(objectID, new QueryListener<BombUserEntity>() {
            @Override
            public void done(BombUserEntity entity, BmobException e) {
                if (e==null)
                {
                    mView.showNetData(entity);
                }
                else
                {
                    mView.showError(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getLocalData() {
        RealmUserEntity entity=realmHelper.queryUserInfo(objectID);
        mView.showLocalData(entity);
    }
}
