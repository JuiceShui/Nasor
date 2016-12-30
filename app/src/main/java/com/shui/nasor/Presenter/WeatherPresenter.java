package com.shui.nasor.Presenter;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.shui.nasor.APP.App;
import com.shui.nasor.Base.BaseRxPresenter;
import com.shui.nasor.Http.RetrofitHelper;
import com.shui.nasor.Http.RxHelper;
import com.shui.nasor.Model.Bean.Weather.WeatherEntity;
import com.shui.nasor.Presenter.Contract.WeatherContract;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 作者： max_Shui on 2016/12/19.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class WeatherPresenter extends BaseRxPresenter<WeatherContract.View> implements WeatherContract.Presenter {
    RetrofitHelper retrofitHelper;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new MAMapLocationListener();
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    String District;
    @Inject
    public WeatherPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void initPosition() {
        mLocationClient = new AMapLocationClient(App.getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        // 等待提示
    }

    @Override
    public void getCity() {
        mView.ShowCity(District);
    }
    @Override
    public void getWeather(String district) {
        mView.showLoading();
           Subscription subscription= retrofitHelper.loadWeather(district)
                    .compose(RxHelper.<WeatherEntity>RxTransformer())
                    .subscribe(new Action1<WeatherEntity>() {
                        @Override
                        public void call(WeatherEntity weatherEntity) {
                           mView.ShowWeather(weatherEntity);
                            mView.hiddenLoading();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                          mView.showError(throwable.toString());
                        }
                    });
        beSubscribe(subscription);
    }
    public class MAMapLocationListener implements AMapLocationListener {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {

                if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    //double latitude = amapLocation.getLatitude();//获取纬度
                   /// double longitude = amapLocation.getLongitude();//获取经度
                    //tvXy.setText("经纬度：" + latitude + " " + longitude);
                   // String city = amapLocation.getCity();//城市信息
                    District=amapLocation.getDistrict();
                    mView.PositionAfter();
                    //setDistrict(amapLocation.getDistrict());
                   // tvCity.setText("定位的城市：" + city + "\n");
                   // tvCityid.setText("地区"+dis);

                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }

        }
        }
    }
}
