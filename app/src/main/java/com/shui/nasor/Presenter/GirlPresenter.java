package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Relax.GirlEntity;
import com.shui.nasor.Presenter.Contract.GirlContract;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 作者： max_Shui on 2016/12/16.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class GirlPresenter extends BaseRxPresenter<GirlContract.View> implements GirlContract.Presenter {
    RetrofitHelper retrofitHelper;
    private int page=1;
    private int size=15;
    @Inject
    public GirlPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
        page=1;
        Subscription subscription=retrofitHelper.loadGirl(page,size)
                .compose(RxHelper.<GirlEntity>RxTransformer())
                .subscribe(new Action1<GirlEntity>() {
                    @Override
                    public void call(GirlEntity entity) {
                        mView.showData(entity);
                    }
                });
        beSubscribe(subscription);
    }

    @Override
    public void getMore() {
        page++;
        Subscription subscription=retrofitHelper.loadGirl(page,size)
                .compose(RxHelper.<GirlEntity>RxTransformer())
                .subscribe(new Action1<GirlEntity>() {
                    @Override
                    public void call(GirlEntity entity) {
                        mView.showMore(entity);
                    }
                });
        beSubscribe(subscription);
    }
}
