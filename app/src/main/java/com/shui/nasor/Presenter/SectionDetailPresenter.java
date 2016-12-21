package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuSectionDetailEntity;
import com.shui.nasor.Presenter.Contract.SectionDetailContract;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 作者： max_Shui on 2016/12/14.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class SectionDetailPresenter extends BaseRxPresenter<SectionDetailContract.View> implements SectionDetailContract.Presenter {
    private RealmHelper realmHelper;
    private RetrofitHelper retrofitHelper;
    @Inject
    public SectionDetailPresenter(RealmHelper realmHelper, RetrofitHelper retrofitHelper) {
        this.realmHelper = realmHelper;
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getData(int id) {
        Subscription subscription = retrofitHelper.loadSectionDetail(id)
                .compose(RxHelper.<ZhihuSectionDetailEntity>RxTransformer())
                .map(new Func1<ZhihuSectionDetailEntity, ZhihuSectionDetailEntity>() {
                    @Override
                    public ZhihuSectionDetailEntity call(ZhihuSectionDetailEntity entity) {
                        List<ZhihuSectionDetailEntity.StoriesBean> list=entity.getStories();
                        for (ZhihuSectionDetailEntity.StoriesBean bean:list)
                        {
                            bean.setReadState(realmHelper.queryRead(bean.getId()));
                        }
                        return entity;
                    }
                })
                .subscribe(new Action1<ZhihuSectionDetailEntity>() {
                    @Override
                    public void call(ZhihuSectionDetailEntity entity) {
                        mView.showData(entity);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("TOT ");
                    }
                });
        beSubscribe(subscription);
    }

    @Override
    public void insertRead(int id) {
        realmHelper.insertRead(id);
    }
}
