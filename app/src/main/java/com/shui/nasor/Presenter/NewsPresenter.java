package com.shui.nasor.Presenter;

import android.text.TextUtils;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.News.NewsEntity;
import com.shui.nasor.Presenter.Contract.NewsContract;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 作者： max_Shui on 2016/12/29.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class NewsPresenter extends BaseRxPresenter<NewsContract.View> implements NewsContract.Presenter {
    RetrofitHelper retrofitHelper;
    private int page=1;
    private int topCount=0;
    private int currentPage=0;
    private int intervalTime=4;
    private Subscription timeSubscription;
    @Inject
    public NewsPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }
    @Override
    public void getData(String title, final boolean isFirst) {
        if (isFirst)
        {
        mView.showLoading();
        }
        if (title==""|| TextUtils.isEmpty(title))
        {
            Subscription subscription=retrofitHelper.loadLastedNews(page)
                    .compose(RxHelper.<NewsEntity>RxTransformer())
                    .map(new Func1<NewsEntity, NewsEntity>() {
                        @Override
                        public NewsEntity call(NewsEntity entity) {
                            List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> bean=
                                    entity.getShowapi_res_body().getPagebean().getContentlist();
                            for (int i=0;i<10;i++)
                            {
                                if (bean.get(i).isHavePic()&&bean.get(i).getImageurls().size()>1)
                                {
                                    topCount++;
                                }
                            }
                            return entity;
                        }
                    })
                    .subscribe(new Action1<NewsEntity>() {
                        @Override
                        public void call(NewsEntity entity) {
                            mView.showData(entity);
                            if (isFirst) {
                                mView.hiddenLoading();
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            if (isFirst)
                            {
                                mView.hiddenLoading();
                            }
                            mView.showError("加载出错啦~~~~");

                        }
                    });
            beSubscribe(subscription);
        }
        else {
            Subscription subscription = retrofitHelper.loadNews(title, page)
                    .compose(RxHelper.<NewsEntity>RxTransformer())
                    .map(new Func1<NewsEntity, NewsEntity>() {
                        @Override
                        public NewsEntity call(NewsEntity entity) {
                            List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> bean =
                                    entity.getShowapi_res_body().getPagebean().getContentlist();
                            for (int i = 0; i < 10; i++) {
                                if (bean.get(i).isHavePic() && bean.get(i).getImageurls().size() > 1) {
                                    topCount++;
                                }
                            }
                            return entity;
                        }
                    })
                    .subscribe(new Action1<NewsEntity>() {
                        @Override
                        public void call(NewsEntity entity) {
                            mView.showData(entity);
                            if (isFirst) {
                                mView.hiddenLoading();
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            if (isFirst)
                            {
                                mView.hiddenLoading();
                            }
                            mView.showError("加载出错啦~~~~");
                        }
                    });
            beSubscribe(subscription);
        }
    }

    @Override
    public void getMore(String title) {
        page++;
        if (title==""||TextUtils.isEmpty(title))
        {
            Subscription subscription=retrofitHelper.loadLastedNews(page)
                    .compose(RxHelper.<NewsEntity>RxTransformer())
                    .subscribe(new Action1<NewsEntity>() {
                        @Override
                        public void call(NewsEntity entity) {
                            mView.showMore(entity);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mView.showError("怎么回事。。加载不出来了、、、");
                        }
                    });
            beSubscribe(subscription);
        }
        else
        {
            Subscription subscription=retrofitHelper.loadNews(title,page)
                    .compose(RxHelper.<NewsEntity>RxTransformer())
                    .subscribe(new Action1<NewsEntity>() {
                        @Override
                        public void call(NewsEntity entity) {
                            mView.showMore(entity);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mView.showError("怎么回事。。加载不出来了、、、");
                        }
                    });
            beSubscribe(subscription);
        }
    }

    @Override
    public void startChange() {
        timeSubscription= Observable.interval(intervalTime, TimeUnit.SECONDS)
                .compose(RxHelper.<Long>RxTransformer())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                            if (currentPage==topCount)
                            {
                                currentPage=0;
                            }
                        mView.ChangeTop(currentPage++);
                    }
                });
        beSubscribe(timeSubscription);
    }

    @Override
    public void stopChange() {
    if (timeSubscription!=null)
    {
        timeSubscription.unsubscribe();
    }
    }
}
