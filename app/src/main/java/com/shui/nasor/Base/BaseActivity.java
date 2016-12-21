package com.shui.nasor.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shui.nasor.APP.App;
import com.shui.nasor.Injection.Component.ActivityComponent;
import com.shui.nasor.Injection.Component.DaggerActivityComponent;
import com.shui.nasor.Injection.Module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */
//基于数据获取的activity基类

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView{
    @Inject
    protected T mPresenter;
    protected Unbinder mBinder;
    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mBinder= ButterKnife.bind(this);
        mContext=this;
        initInject();
        if (mPresenter!=null)
        {
            mPresenter.attachView(this);
        }
        App.getInstance().addActivity(this);
        initEventData();
    }

    protected abstract void initInject();
    //注入依赖
    protected ActivityComponent getActivityComponent(){
       return DaggerActivityComponent.builder()
               .activityModule(getActivityModule())
               .appComponent(App.getAppComponent())
               .build();
    }
    protected ActivityModule getActivityModule()
    {
        return new ActivityModule(this);
    }
    protected void setToolBar(Toolbar bar,String title)
    {
        bar.setTitle(title);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayShowHomeEnabled(true); //使左上角图标是否显示，如果设成false，
        // 则没有程序图标，仅仅就个标题，否则，显示应用程序图标，对应id为Android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });

    }
    protected abstract void initEventData();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
        {
            mPresenter.detachView();
        }
        mBinder.unbind();
        App.getInstance().removeActivity(this);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void isNightMode(boolean isNight) {
    if (isNight)
     {
        AppCompatDelegate.setDefaultNightMode
                (AppCompatDelegate.MODE_NIGHT_YES);
     }
    else
     {
        AppCompatDelegate.setDefaultNightMode
                (AppCompatDelegate.MODE_NIGHT_NO);
     }
        recreate();
    }
}
