package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuHotEntity;
import com.shui.nasor.Presenter.Contract.HotContract;

import java.util.List;

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


public class HotPresenter extends BaseRxPresenter<HotContract.View> implements HotContract.Presenter {
    private RetrofitHelper retrofitHelper;
    private RealmHelper realmHelper;
    @Inject
    public HotPresenter(RetrofitHelper retrofitHelper,RealmHelper realmHelper) {
        this.retrofitHelper = retrofitHelper;
        this.realmHelper=realmHelper;
    }

    @Override
    public void getData() {
        mView.showLoading();
        Subscription subscription=retrofitHelper.loadHotList()
                .compose(RxHelper.<ZhihuHotEntity>RxTransformer())
                .map(new Func1<ZhihuHotEntity, ZhihuHotEntity>() {
                    @Override
                    public ZhihuHotEntity call(ZhihuHotEntity zhihuHotEntity) {
                        List<ZhihuHotEntity.RecentBean> list=zhihuHotEntity.getRecent();
                        for (ZhihuHotEntity.RecentBean bean:list)
                        {
                            bean.setReadState(realmHelper.queryRead(bean.getNews_id()));
                        }
                        return zhihuHotEntity;

                    }
                })
                .subscribe(new Action1<ZhihuHotEntity>() {
                    @Override
                    public void call(ZhihuHotEntity zhihuHotEntity) {
                        mView.showContent(zhihuHotEntity);
                        mView.hiddenLoading();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("yoyo~~出问题了。。。");
                    }
                });
        beSubscribe(subscription);
    }

    @Override
    public void insertRead(int id) {
        realmHelper.insertRead(id);
    }
}
