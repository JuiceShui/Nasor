package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuDetailEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuExtraEntity;
import com.shui.nasor.Model.RealmBean.LikeBean;
import com.shui.nasor.Model.RealmBean.LikeType;
import com.shui.nasor.Presenter.Contract.ZhihuNewsContract;

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


public class ZhuhiNewsPresenter extends BaseRxPresenter<ZhihuNewsContract.View> implements ZhihuNewsContract.Presenter {
    private RetrofitHelper retrofitHelper;
    private RealmHelper realmHelper;
    private ZhihuDetailEntity mData;
    @Inject
    public ZhuhiNewsPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.retrofitHelper = retrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void getData(int id) {
        mView.showLoading();
        Subscription subscription=
        retrofitHelper.loadNewsDetail(id)
                .compose(RxHelper.<ZhihuDetailEntity>RxTransformer())
                .subscribe(new Action1<ZhihuDetailEntity>() {
                    @Override
                    public void call(ZhihuDetailEntity entity) {
                        mData=entity;
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
    public void getExtra(int id) {
        Subscription extraSubscription=retrofitHelper.loadExtra(id)
                .compose(RxHelper.<ZhihuExtraEntity>RxTransformer())
                .subscribe(new Action1<ZhihuExtraEntity>() {
                    @Override
                    public void call(ZhihuExtraEntity zhihuExtraEntity) {
                        mView.showExtra(zhihuExtraEntity);
                    }
                });
        beSubscribe(extraSubscription);
    }

    @Override
    public void queryLike(int id) {
        mView.setLikeState(realmHelper.queryLike(String.valueOf(id)));
    }

    @Override
    public void insertLike() {
        if (mData!=null)
        {
            LikeBean bean=new LikeBean();
            bean.setId(String.valueOf(mData.getId()));
            bean.setType(LikeType.LIKE_ZHIHU);
            bean.setTitle(mData.getTitle());
            bean.setPic(mData.getImage());
            bean.setUrl(mData.getShare_url());
            bean.setTime(System.currentTimeMillis());
            realmHelper.insertLike(bean);
        }
        else {
            mView.showError("添加收藏失败，请重试");
        }
    }

    @Override
    public void deleteLike() {
        if (mData!=null)
        {
            realmHelper.deleteLike(String .valueOf(mData.getId()));
        }
        else {
            mView.showError("取消收藏失败，请重试");
        }
    }
}
