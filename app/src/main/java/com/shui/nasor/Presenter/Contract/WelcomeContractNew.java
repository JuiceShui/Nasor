package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;

/**
 * 作者： JuiceShui on 2017/3/14.
 * We improve ourselves by victories over ourselves.
 * There must be contests, and we must win.
 */
public interface WelcomeContractNew {
    interface View extends BaseView
    {
        void jumpToMain();
        void showPic(String url,String title);
    }
    interface Presenter extends BasePresenter<View>
    {
        void getData();
    }
}
