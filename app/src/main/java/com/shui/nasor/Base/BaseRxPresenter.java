package com.shui.nasor.Base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */
//基于rx请求的presenter基类 控制Subscription得生命周期

public class BaseRxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected CompositeSubscription compositeSubscription;
    protected T mView;
    @Override
    public void attachView(T view) {
        this.mView=view;
    }

    /**
     * 取消订阅
     */
    protected void unSubscribe()
    {
        if (compositeSubscription!=null)
        {
            compositeSubscription.unsubscribe();
        }
    }

    /**
     * 添加订阅
     * @param subscription 订阅
     */
    protected void beSubscribe(Subscription subscription)
    {
        if (compositeSubscription==null)
        {
            compositeSubscription=new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }
    @Override
    public void detachView() {
        this.mView=null;
        unSubscribe();
    }
}
