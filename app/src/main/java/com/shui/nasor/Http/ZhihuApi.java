package com.shui.nasor.Http;


import com.shui.nasor.Model.Bean.Zhihu.ZhihuCommentEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuDailyEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuDetailEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuExtraEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuHotEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuSectionDetailEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuSectionListEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuThemeDetailEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuThemeListEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuWelcomeEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface ZhihuApi {
    String HOST_LINK="http://news-at.zhihu.com/api/4/";//知乎api

    /**
     * 获取封面
     * @return
     */
    @GET("start-image/{size}")
    Observable<ZhihuWelcomeEntity> getWelcome(@Path("size") String size);
    /**
     * 知乎最新新闻
     * @return
     */
    @GET("news/latest")
    Observable<ZhihuDailyEntity> getDailyList();

    /**
     * 获取对应id的新闻
     * @param id
     * @return
     */
    @GET("news/{id}")
    Observable<ZhihuDetailEntity> getNewsDetail(@Path("id") int id);

    /**
     * 获取对应id新闻的额外信息
     * @param id
     * @return
     */
    @GET("story-extra/{id}")
    Observable<ZhihuExtraEntity> getNewsExtra(@Path("id") int id);

    /**
     * 获取对应id新闻的长评论
     * @param id
     * @return
     */
    @GET("story/{id}/long-comments")
    Observable<ZhihuCommentEntity> getLongComment(@Path("id") int id);
    /**
     * 获取对应id新闻的短评论
     * @param id
     * @return
     */
    @GET("story/{id}/short-comments")
    Observable<ZhihuCommentEntity> getShortComment(@Path("id") int id);
    /**
     * 获取主题
     * @return
     */
    @GET("themes")
    Observable<ZhihuThemeListEntity> getThemeList();

    /**
     * 获取对应id的主题详情
     * @param id 主题id
     * @return
     */
    @GET("theme/{id}")
    Observable<ZhihuThemeDetailEntity> getThemeDetail(@Path("id") int id);

    /**
     * 获取专栏
     * @return
     */
    @GET("sections")
    Observable<ZhihuSectionListEntity> getSectionList();

    /**
     * 获取对应id的专栏详情
     * @param id 专栏id
     * @return
     */
    @GET("section/{id}")
    Observable<ZhihuSectionDetailEntity> getSectionDetail(@Path("id") int id);

    /**
     * 获取热门消息
     * @return
     */
    @GET("news/hot")
    Observable<ZhihuHotEntity> getHotList();
}
