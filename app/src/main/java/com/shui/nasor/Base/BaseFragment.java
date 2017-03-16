package com.shui.nasor.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shui.nasor.APP.App;
import com.shui.nasor.Injection.Component.DaggerFragmentComponent;
import com.shui.nasor.Injection.Component.FragmentComponent;
import com.shui.nasor.Injection.Module.FragmentModule;
import com.shui.nasor.View.viewHelper.ViewHelperController;

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
    private ViewHelperController mViewHelperController;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
        this.mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayout(), null);
        InjectView();
        return mView;
    }
    protected abstract View getLoadingTargetView();

    protected abstract void InjectView();

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mBinder = ButterKnife.bind(this, view);
        mViewHelperController = new ViewHelperController(getLoadingTargetView());
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
     *
     * @return
     */

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    /**
     * show toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        if (null != msg && !TextUtils.isEmpty(msg)) {
            Toast.makeText(((Activity) mContext), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void toggleShowLoading(boolean toggle, String msg) {
        if (null == mViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle) {
            mViewHelperController.showLoading(msg);
        } else {
            mViewHelperController.restore();
        }
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mViewHelperController.restore();
        }
    }

    /**
     * toggle show error
     *
     * @param toggle
     */
    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mViewHelperController.showError(msg, onClickListener);
        } else {
            mViewHelperController.restore();
        }
    }

    /**
     * toggle show network error
     *
     * @param toggle
     */
    protected void toggleNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mViewHelperController.showNetworkError(onClickListener);
        } else {
            mViewHelperController.restore();
        }
    }

    @Override
    public void showLoading() {
        toggleShowLoading(true,null);
    }

    @Override
    public void hiddenLoading() {
        toggleShowLoading(false,null);
    }

    @Override
    public void showError(String msg) {
        toggleShowError(true,msg,null);
    }

    @Override
    public void showExceotion(String msg) {
        toggleShowEmpty(true,msg,null);
    }

    @Override
    public void showNetError(String msg) {
        showNetError(msg);
    }
}
    

