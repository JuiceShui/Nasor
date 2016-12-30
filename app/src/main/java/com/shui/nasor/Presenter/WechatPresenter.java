package com.shui.nasor.Presenter;

import android.text.TextUtils;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.WeChat.WeChatEntity;
import com.shui.nasor.Presenter.Contract.WeChatContract;

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


public class WechatPresenter extends BaseRxPresenter<WeChatContract.View> implements WeChatContract.Presenter {
    RetrofitHelper retrofitHelper;
    private int page=1;
    private String word="";
    @Inject
    public WechatPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
        mView.showLoading();
        this.word="";
        this.page=1;
        Subscription subscription=retrofitHelper.loadWeChat(20,page)
                .compose(RxHelper.<WeChatEntity>RxTransformer())
                .subscribe(new Action1<WeChatEntity>() {
                    @Override
                    public void call(WeChatEntity entity) {
                        mView.showContent(entity);
                        mView.hiddenLoading();
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
        if (TextUtils.isEmpty(word)||word=="") {
            Subscription subscription = retrofitHelper.loadWeChat(20, page)
                    .compose(RxHelper.<WeChatEntity>RxTransformer())
                    .subscribe(new Action1<WeChatEntity>() {
                        @Override
                        public void call(WeChatEntity entity) {
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
        else {
            Subscription subscription = retrofitHelper.loadWeChatSearch(20, page,word)
                    .compose(RxHelper.<WeChatEntity>RxTransformer())
                    .subscribe(new Action1<WeChatEntity>() {
                        @Override
                        public void call(WeChatEntity entity) {
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

    @Override
    public void getSearch(String word) {
        mView.showLoading();
        this.page=1;
        this.word=word;
        Subscription subscription=retrofitHelper.loadWeChatSearch(20,page,word)
                .compose(RxHelper.<WeChatEntity>RxTransformer())
                .subscribe(new Action1<WeChatEntity>() {
                    @Override
                    public void call(WeChatEntity entity) {
                        mView.showSearch(entity);
                        mView.hiddenLoading();
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
