package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuCommentEntity;
import com.shui.nasor.Presenter.Contract.CommentContract;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 作者： max_Shui on 2016/12/14.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class CommentPresenter extends BaseRxPresenter<CommentContract.View> implements CommentContract.Presenter {
    private RetrofitHelper retrofitHelper;
    @Inject
    public CommentPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getData(int id,int kind) {
        mView.showLoading();
        if (kind==0)
        {
            Subscription subscription=retrofitHelper.loadLongComment(id)
                    .compose(RxHelper.<ZhihuCommentEntity>RxTransformer())
                    .subscribe(new Action1<ZhihuCommentEntity>() {
                        @Override
                        public void call(ZhihuCommentEntity entity) {
                            mView.showData(entity);
                            mView.hiddenLoading();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mView.showError("出错了TOT");
                        }
                    });
            beSubscribe(subscription);
        }
        else
        {
            Subscription subscription=retrofitHelper.loadShortComment(id)
                    .compose(RxHelper.<ZhihuCommentEntity>RxTransformer())
                    .subscribe(new Action1<ZhihuCommentEntity>() {
                        @Override
                        public void call(ZhihuCommentEntity entity) {
                            mView.showData(entity);
                            mView.hiddenLoading();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mView.showError("TOT  出错了");
                        }
                    });
            beSubscribe(subscription);
        }
    }
}
