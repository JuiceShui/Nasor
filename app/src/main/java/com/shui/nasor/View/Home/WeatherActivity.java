package com.shui.nasor.View.Home;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.Base.BaseActivity;
import com.shui.nasor.Model.Bean.Weather.WeatherEntity;
import com.shui.nasor.Presenter.Contract.WeatherContract;
import com.shui.nasor.Presenter.WeatherPresenter;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/17.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class  WeatherActivity extends BaseActivity<WeatherPresenter> implements WeatherContract.View {

    @BindView(R.id.weatherIVHeader)
    ImageView weatherIVHeader;
    @BindView(R.id.zhihuTVCopyRight)
    TextView zhihuTVCopyRight;
    @BindView(R.id.weatherToolbar)
    Toolbar weatherToolbar;
    @BindView(R.id.weatherCollapsingToolbarLayouts)
    CollapsingToolbarLayout weatherCollapsingToolbarLayouts;
    @BindView(R.id.weatherAppBarLayout)
    AppBarLayout weatherAppBarLayout;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_more_info)
    TextView tvMoreInfo;
    @BindView(R.id.weatherCardToday)
    CardView weatherCardToday;
    @BindView(R.id.iv_icon_f1)
    ImageView ivIconF1;
    @BindView(R.id.tv_date_f1)
    TextView tvDateF1;
    @BindView(R.id.tv_temp_f1)
    TextView tvTempF1;
    @BindView(R.id.tv_detail_f1)
    TextView tvDetailF1;
    @BindView(R.id.iv_icon_f2)
    ImageView ivIconF2;
    @BindView(R.id.tv_date_f2)
    TextView tvDateF2;
    @BindView(R.id.tv_temp_f2)
    TextView tvTempF2;
    @BindView(R.id.tv_detail_f2)
    TextView tvDetailF2;
    @BindView(R.id.iv_icon_f3)
    ImageView ivIconF3;
    @BindView(R.id.tv_date_f3)
    TextView tvDateF3;
    @BindView(R.id.tv_temp_f3)
    TextView tvTempF3;
    @BindView(R.id.tv_detail_f3)
    TextView tvDetailF3;
    @BindView(R.id.weatherNestedScrollView)
    NestedScrollView weatherNestedScrollView;
    @BindView(R.id.tv_weather_now)
    TextView tvWeatherNow;

    @Override
    protected void initInject() {
        getActivityComponent().Inject(this);
    }

    @Override
    protected void initEventData() {
        setToolBar(weatherToolbar, "");
        mPresenter.initPosition();//先初始化定位
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_weather1;
    }

    @Override
    public void ShowCity(String city) {
        weatherToolbar.setTitle(city);
        mPresenter.getWeather(city);
    }

    @Override
    public void ShowWeather(WeatherEntity entity) {
        WeatherEntity.ShowapiResBodyBean.NowBean nowBean=entity.getShowapi_res_body().getNow();
        ImageLoader.load(this,nowBean.getWeather_pic(), weatherIVHeader);
        //现在
        ImageLoader.load(this, nowBean.getWeather_pic(), ivIcon);
        tvTemp.setText(nowBean.getTemperature() + "℃");
        tvWeatherNow.setText(nowBean.getWeather());
        tvMoreInfo.setText(nowBean.getWind_direction()+nowBean.getWind_power()+"   "+"空气"+nowBean.getAqiDetail().getQuality());
        //今天
        WeatherEntity.ShowapiResBodyBean.F1Bean f1Bean=entity.getShowapi_res_body().getF1();
        tvDateF1.setText("今天");
        tvTempF1.setText(f1Bean.getNight_air_temperature()+"~"+f1Bean.getDay_air_temperature()+"℃");
        StringBuilder sb1=new StringBuilder();
        sb1.append(f1Bean.getDay_weather()+"")
                .append("风向:"+f1Bean.getDay_wind_direction()+"")
                .append("风速:"+f1Bean.getDay_wind_power()+"")
                .append("紫外线强度:"+f1Bean.getZiwaixian()+"");
        tvDetailF1.setText(sb1);
        ImageLoader.load(this,f1Bean.getDay_weather_pic(),ivIconF1);
        //明天
        WeatherEntity.ShowapiResBodyBean.F2Bean f2Bean=entity.getShowapi_res_body().getF2();
        tvDateF2.setText("明天");
        tvTempF2.setText(f2Bean.getNight_air_temperature()+"~"+f2Bean.getDay_air_temperature()+"℃");
        StringBuilder sb2=new StringBuilder();
        sb2.append(f2Bean.getDay_weather()+"")
                .append("风向:"+f2Bean.getDay_wind_direction()+"")
                .append("风速:"+f2Bean.getDay_wind_power()+"")
                .append("紫外线强度:"+f2Bean.getZiwaixian()+"");
        tvDetailF2.setText(sb2);
        ImageLoader.load(this,f2Bean.getDay_weather_pic(),ivIconF2);
        //后天
        WeatherEntity.ShowapiResBodyBean.F3Bean f3Bean=entity.getShowapi_res_body().getF3();
        tvDateF3.setText("后天");
        tvTempF3.setText(f3Bean.getNight_air_temperature()+"~"+f3Bean.getDay_air_temperature()+"℃");
        StringBuilder sb3=new StringBuilder();
        sb3.append(f3Bean.getDay_weather()+"")
                .append("风向:"+f3Bean.getDay_wind_direction()+"")
                .append("风速:"+f3Bean.getDay_wind_power()+"")
                .append("紫外线强度:"+f3Bean.getZiwaixian()+"");
        tvDetailF3.setText(sb3);
        ImageLoader.load(this,f3Bean.getDay_weather_pic(),ivIconF3);
    }

    @Override
    public void PositionAfter() {
        mPresenter.getCity();
    }


}
