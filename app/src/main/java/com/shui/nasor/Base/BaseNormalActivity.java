package com.shui.nasor.Base;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shui.nasor.APP.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 非MVP模式的activity基类
 */

public abstract class BaseNormalActivity extends SupportActivity{
    protected Unbinder mBinder;
    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext=this;
        setContentView(getLayout());
        mBinder= ButterKnife.bind(this);
        App.getInstance().addActivity(this);
        initEventAndData();
    }
    protected void setToolbar(Toolbar bar,String title)
    {
        bar.setTitle(title);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
        App.getInstance().removeActivity(this);
    }

    /**
     * 处理事件数据
     */
    protected abstract void initEventAndData();

    /**
     * 获取布局
     * @return
     */
    protected abstract int getLayout();
}
