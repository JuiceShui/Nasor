package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.Weather.WeatherEntity;

/**
 * 作者： max_Shui on 2016/12/19.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface WeatherContract {
    interface View extends BaseView
    {
        void ShowCity(String city);
        void ShowWeather(WeatherEntity entity);
        void PositionAfter();
    }
    interface Presenter extends BasePresenter<View>
    {
        void initPosition();
        void getCity();
        void getWeather(String dis);
    }
}
