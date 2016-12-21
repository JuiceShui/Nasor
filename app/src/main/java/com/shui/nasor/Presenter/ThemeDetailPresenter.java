package com.shui.nasor.Presenter;

import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuThemeDetailEntity;
import com.shui.nasor.Presenter.Contract.ThemeDetailContract;

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


public class ThemeDetailPresenter extends BaseRxPresenter<ThemeDetailContract.View> implements ThemeDetailContract.Presenter {
    private RetrofitHelper retrofitHelper;
    private RealmHelper realmHelper;
    @Inject
    public ThemeDetailPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.retrofitHelper = retrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void getData(int id) {
        Subscription subscription=retrofitHelper.loadThemeDetail(id)
                .compose(RxHelper.<ZhihuThemeDetailEntity>RxTransformer())
                .map(new Func1<ZhihuThemeDetailEntity, ZhihuThemeDetailEntity>() {
                    @Override
                    public ZhihuThemeDetailEntity call(ZhihuThemeDetailEntity entity) {
                        List<ZhihuThemeDetailEntity.StoriesBean> list=entity.getStories();
                        for (ZhihuThemeDetailEntity.StoriesBean bean:list)
                        {
                            bean.setReadState(realmHelper.queryRead(bean.getId()));
                        }
                        return entity;
                    }
                })
                .subscribe(new Action1<ZhihuThemeDetailEntity>() {
                    @Override
                    public void call(ZhihuThemeDetailEntity entity) {
                        mView.showContent(entity);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("");
                    }
                });
    }

    @Override
    public void insertRead(int id) {
        realmHelper.insertRead(id);
    }
}
