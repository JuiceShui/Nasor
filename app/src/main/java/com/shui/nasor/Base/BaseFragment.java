package com.shui.nasor.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shui.nasor.APP.App;
import com.shui.nasor.Injection.Component.DaggerFragmentComponent;
import com.shui.nasor.Injection.Component.FragmentComponent;
import com.shui.nasor.Injection.Module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */
//实现MVP的fragment基类

public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView {
    @Inject
    protected T mPresenter;
    protected Activity mActivity;
    protected Context mContext;
    protected View mView;
    private Unbinder mBinder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity=activity;
        this.mContext=activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(getLayout(),null);
        InjectView();
        return mView;
    }

    protected abstract void InjectView();

    protected FragmentComponent getFragmentComponent()
    {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }
    protected FragmentModule getFragmentModule()
    {
        return new  FragmentModule(this);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mBinder= ButterKnife.bind(this,view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initEventAndData();
    }

    /**
     * 初始化事件和数据
     */
    protected abstract void initEventAndData();

    /**
     * 获取布局
     * @return
     */

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
        {
            mPresenter.detachView();
        }
    }
    @Override
    public void isNightMode(boolean isNight) {

    }

    
}
