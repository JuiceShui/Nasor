package com.shui.nasor.Presenter;

import com.shui.nasor.APP.AppUtils;
import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.TXData.TXNews;
import com.shui.nasor.Presenter.Contract.WelcomeContractNew;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 作者： JuiceShui on 2017/3/14.
 * We improve ourselves by victories over ourselves.
 * There must be contests, and we must win.
 */
public class WelcomePresenterNew extends BaseRxPresenter<WelcomeContractNew.View> implements WelcomeContractNew.Presenter {
    private int COUNT_DOWN_TIME=2000;
    private RetrofitHelper retrofitHelper;
    @Inject
    public WelcomePresenterNew(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
        Subscription subscription=retrofitHelper.getGirl(1)
                .compose(RxHelper.<TXNews>RxTransformer())
                .map(new Func1<TXNews, TXNews.NewslistBean>() {
                    @Override
                    public TXNews.NewslistBean call(TXNews txNews) {
                        return txNews.getNewslist().get(AppUtils.getRandomInt(14));
                    }
                })
                .subscribe(new Action1<TXNews.NewslistBean>() {
                    @Override
                    public void call(TXNews.NewslistBean newslistBean) {
                        mView.showPic(newslistBean.getPicUrl(),newslistBean.getTitle());
                        startCountDown();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.jumpToMain();
                    }
                });
        beSubscribe(subscription);
    }
    private void startCountDown()
    {
        Subscription subscription= Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxHelper.<Long>RxTransformer())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.jumpToMain();
                    }
                });
        beSubscribe(subscription);
    }
}
