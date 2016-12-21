package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuDailyEntity;
import com.shui.nasor.Presenter.Contract.DailyContract;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class DailyPresenter extends BaseRxPresenter<DailyContract.View> implements DailyContract.Presenter {
    private RealmHelper realmHelper;
    private RetrofitHelper retrofitHelper;
    private int mCount=0;//top的个数
    private int currentItem=0;//当前的个数
    private Subscription timeSubscription;
    private int spliterTime=5;//间隔时间
    @Inject
    public DailyPresenter(RealmHelper realmHelper, RetrofitHelper retrofitHelper) {
        this.realmHelper = realmHelper;
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
        Subscription subscription=retrofitHelper.loadDaily()
                .compose(RxHelper.<ZhihuDailyEntity>RxTransformer())
                .map(new Func1<ZhihuDailyEntity, ZhihuDailyEntity>() {

                    @Override
                    public ZhihuDailyEntity call(ZhihuDailyEntity entity) {
                        List<ZhihuDailyEntity.StoriesBean> list=entity.getStories();
                        for (ZhihuDailyEntity.StoriesBean bean:list)
                        {
                            bean.setReadState(realmHelper.queryRead(bean.getId()));
                        }
                       return entity;
                    }
                })
                .subscribe(new Action1<ZhihuDailyEntity>() {
                               @Override
                               public void call(ZhihuDailyEntity entity) {
                                   mCount = entity.getTop_stories().size();
                                   mView.ShowContent(entity);
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mView.showError("数据加载错误T0T");
                            }
                        });
        beSubscribe(subscription);
    }

    @Override
    public void startChange() {
        timeSubscription= rx.Observable.interval(spliterTime, TimeUnit.SECONDS)
                .compose(RxHelper.<Long>RxTransformer())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (currentItem==mCount)//最大值 归零
                        {
                            currentItem=0;
                        }
                        mView.TopChange(currentItem++);//加一
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

    @Override
    public void insertReadToDB(int id) {
        realmHelper.insertRead(id);
    }
}
