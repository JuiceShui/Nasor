package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuSectionListEntity;
import com.shui.nasor.Presenter.Contract.SectionContract;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class SectionPresenter extends BaseRxPresenter<SectionContract.View> implements SectionContract.Presenter {
    private RetrofitHelper retrofitHelper;
    @Inject
    public SectionPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
        mView.showLoading();
        Subscription subscription=retrofitHelper.loadSectionList()
                .compose(RxHelper.<ZhihuSectionListEntity>RxTransformer())
                .subscribe(new Action1<ZhihuSectionListEntity>() {
                    @Override
                    public void call(ZhihuSectionListEntity entity) {
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
}
