package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuWelcomeEntity;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Presenter.Contract.WelcomeContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 作者： max_Shui on 2016/12/11.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class WelcomePresenter extends BaseRxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {
    private static final String SIZE= "1080*1776";
    private static final int TIME_DOWN=2200;
    private RetrofitHelper retrofitHelper;
    @Inject
    public WelcomePresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper=retrofitHelper;
    }
    @Override
    public void getWelcomeData() {
        Subscription subscription=retrofitHelper.loadWelcome(SIZE)
               .compose(RxHelper.<ZhihuWelcomeEntity>RxTransformer())
                .subscribe(new Action1<ZhihuWelcomeEntity>() {
                    @Override
                    public void call(ZhihuWelcomeEntity zhihuWelcomeEntity) {
                        mView.showContent(zhihuWelcomeEntity);
                        startTime();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载错误");
                        mView.jumpToMain();
                    }
                });
        beSubscribe(subscription);
    }
    public void startTime()
    {
        Subscription timeSubscription=Observable.timer(TIME_DOWN, TimeUnit.MILLISECONDS)
                .compose(RxHelper.<Long>RxTransformer())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.jumpToMain();
                    }
                });
        beSubscribe(timeSubscription);
    }
}
