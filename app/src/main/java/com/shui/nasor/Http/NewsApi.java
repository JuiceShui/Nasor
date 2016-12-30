package com.shui.nasor.Http;


import com.shui.nasor.Model.Bean.News.NewsEntity;
import com.shui.nasor.Model.Bean.Relax.GirlEntity;
import com.shui.nasor.Model.Bean.Relax.IMGJokerEntity;
import com.shui.nasor.Model.Bean.Relax.TXTJokerEntity;
import com.shui.nasor.Model.Bean.Weather.WeatherEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface NewsApi {
    String HOST_LINK="http://route.showapi.com/";
    String APPSCRIT="56e7dd5e0acc496a8bfa73834d675e4c";
    String APPID="26821";

    /**
     * 获取新闻列表
     * @param appid 应用的id
     * @param appScript 应用的身份
     * @param title 新闻类型
     * @param page 返回的页数
     * @return
     */
    @GET("109-35")
    Observable<NewsEntity> getNews(@Query("showapi_appid") String appid, @Query("showapi_sign") String appScript,
                                   @Query("title") String title, @Query("page") int page);

    /**
     * 获取最新新闻
     * @param appid
     * @param appScript
     * @param page
     * @return
     */
    @GET("109-35")
    Observable<NewsEntity> getLastedNews(@Query("showapi_appid") String appid, @Query("showapi_sign") String appScript,
                                    @Query("page") int page);

    /**
     * 获取笑话
     * @param appid
     * @param appScript
     * @param page
     * @return
     */
    @GET("341-1")
    Observable<TXTJokerEntity> getJoker(@Query("showapi_appid") String appid, @Query("showapi_sign") String appScript,
                                        @Query("page") int page);

    /**
     * 获取图片笑话
     * @param appid
     * @param appScript
     * @param page
     * @param max
     * @return
     */
    @GET("341-2")
    Observable<IMGJokerEntity> getIMGJoker(@Query("showapi_appid") String appid, @Query("showapi_sign") String appScript,
                                        @Query("page") int page,@Query("maxResult") int max);

    /**
     * 妹纸
     * @param appid
     * @param appScript
     * @param max
     * @param page
     * @return
     */
    @GET("197-1")
    Observable<GirlEntity> getGirl(@Query("showapi_appid") String appid, @Query("showapi_sign") String appScript,
                    @Query("num") int max, @Query("page") int page);

    /**
     * 天气预报api
     * @param appid
     * @param appScript
     * @param area
     * @return
     */
    //http://route.showapi.com/9-3
    @GET("9-2")
    Observable<WeatherEntity> getWeather(@Query("showapi_appid") String appid, @Query("showapi_sign") String appScript,
                                         @Query("area") String area);

}
