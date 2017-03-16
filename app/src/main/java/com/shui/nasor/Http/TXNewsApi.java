package com.shui.nasor.Http;

/**
 * 作者： JuiceShui on 2017/2/21.
 * We improve ourselves by victories over ourselves.
 * There must be contests, and we must win.
 */


import com.shui.nasor.Model.Bean.TXData.TXNews;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 天行api接口
 */
public interface TXNewsApi {
    String HostLink="https://api.tianapi.com/";
    String APIKEY="f8d6bf3d8a2e5daa7fd6eaf1cfbe5439";
    int SigleCount=15;//每次数据的返回数

    /**
     * |国内新闻
     * @param key
     * @param num
     * @return
     */
    @GET("guonei/")
    Observable<TXNews> TXHomeNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("guonei/")
    Observable<TXNews> TXHomeNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

    /**
     * 国外新闻
     * @param key
     * @param num
     * @return
     */
    @GET("world/")
    Observable<TXNews> TXWorldNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("world/")
    Observable<TXNews> TXWorldNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
    /**
     * 娱乐新闻
     * @param key
     * @param num
     * @return
     */
    @GET("huabian/")
    Observable<TXNews> TXEntertainmentNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("huabian/")
    Observable<TXNews> TXEntertainmentNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

    /**
     * 体育新闻
     * @param key
     * @param num
     * @return
     */
    @GET("tiyu/")
    Observable<TXNews> TXSportNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("tiyu/")
    Observable<TXNews> TXSportNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

    /**
     * NBA新闻
     * @param key
     * @param num
     * @return
     */
    @GET("nba/")
    Observable<TXNews> TXNBANews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("nba/")
    Observable<TXNews> TXNBANewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

    /**
     * 足球新闻
     * @param key
     * @param num
     * @return
     */
    @GET("football/")
    Observable<TXNews> TXFootballNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("football/")
    Observable<TXNews> TXFootballNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

    /**
     * 健康
     * @param key
     * @param num
     * @return
     */
    @GET("health/")
    Observable<TXNews> TXHealthNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("health/")
    Observable<TXNews> TXHealthNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

    /**
     * 美女图片
     * @param key
     * @param num
     * @return
     */
    @GET("meinv/")
    Observable<TXNews> TXGirl(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("meinv/")
    Observable<TXNews> TXGirlWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

    /**
     * it新闻
     * @param key
     * @param num
     * @return
     */
    @GET("it/")
    Observable<TXNews> TXITNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("it/")
    Observable<TXNews> TXITNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
    /**
     * 奇闻异事
     * @param key
     * @param num
     * @return
     */
    @GET("qiwen/")
    Observable<TXNews> TXStrangeNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("qiwen/")
    Observable<TXNews> TXStrangeNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
    /**
     * 旅游新闻
     * @param key
     * @param num
     * @return
     */
    @GET("travel/")
    Observable<TXNews> TXTravelNews(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    @GET("travel/")
    Observable<TXNews> TXTravelNewsWord(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
}
