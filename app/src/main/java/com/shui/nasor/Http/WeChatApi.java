package com.shui.nasor.Http;


import com.shui.nasor.Model.Bean.WeChat.WXEntity;

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


public interface WeChatApi {
    String HOST_LINK="https://api.tianapi.com/";
    String APIKEY="f8d6bf3d8a2e5daa7fd6eaf1cfbe5439";
    int SigleCount=20;//每次数据的返回数

    /**
     * 获取
     * @param key
     * @param num
     * @param page
     * @return
     */
    @GET("/wxnew/")
    Observable<WXEntity> getWXInfo(@Query("key") String key,@Query("num") int num,@Query("page") int page);

    /**
     * 查询
     * @param key
     * @param num
     * @param page
     * @param word
     * @return
     */
    @GET("/wxnew/")
    Observable<WXEntity> getWXInfoSearch(@Query("key") String key,@Query("num") int num,@Query("page") int page,@Query("word") String word);
}
