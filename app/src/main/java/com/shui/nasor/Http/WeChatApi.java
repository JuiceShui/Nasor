package com.shui.nasor.Http;


import com.shui.nasor.Model.Bean.WeChat.WeChatEntity;

import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    String HOST_LINK="http://apis.baidu.com/txapi/weixin/";

    /**
     * 获取微信列表
     * @param num 返回信息的最大值
     * @param page 返回的页数
     * @param word 查询关键词
     * @return
     */
    @Headers("apikey:807cc4515ba35ede5a5e2979542e9a86")
    @GET("wxhot")
    Observable<WeChatEntity> getWeChatSearch(@Query("num") int num, @Query("page") int page, @Query("word") String word);


    @Headers("apikey:807cc4515ba35ede5a5e2979542e9a86")
    @GET("wxhot")
    Observable<WeChatEntity> getWeChatInfo(@Query("num") int num, @Query("page") int page);
}
