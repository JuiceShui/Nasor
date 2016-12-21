package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Relax.TXTJokerEntity;
import com.shui.nasor.Presenter.Contract.TXTJokerContract;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 作者： max_Shui on 2016/12/15.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class TXTJokerPresenter extends BaseRxPresenter<TXTJokerContract.View> implements TXTJokerContract.Presenter {
    RetrofitHelper retrofitHelper;
    private int page=1;
    @Inject
    public TXTJokerPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
        page=1;
        Subscription subscription=retrofitHelper.loadTXTJoker(page)
                .compose(RxHelper.<TXTJokerEntity>RxTransformer())
                .subscribe(new Action1<TXTJokerEntity>() {
                    @Override
                    public void call(TXTJokerEntity entity) {
                        mView.showData(entity);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("");
                    }
                });
        beSubscribe(subscription);
    }

    @Override
    public void getMore() {
        page++;
        Subscription subscription=retrofitHelper.loadTXTJoker(page)
                .compose(RxHelper.<TXTJokerEntity>RxTransformer())
                .subscribe(new Action1<TXTJokerEntity>() {
                    @Override
                    public void call(TXTJokerEntity entity) {
                        mView.showMore(entity);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("");
                    }
                });
        beSubscribe(subscription);
    }
}
