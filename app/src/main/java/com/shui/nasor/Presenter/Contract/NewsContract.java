package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.News.NewsEntity;

/**
 * 作者： max_Shui on 2016/12/15.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface NewsContract  {
    interface View extends BaseView
    {
        void showData(NewsEntity entity);
        void showMore(NewsEntity entity);
        void ChangeTop(int position);//切换头条
    }
    interface Presenter extends BasePresenter<View>
    {
        void getData(String title);
        void getMore(String title);
        void startChange();//开始切换
        void stopChange();//停止切换
    }
}
