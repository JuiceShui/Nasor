package com.shui.nasor.Http;


import com.shui.nasor.APP.AppUtils;
import com.shui.nasor.APP.Constants;
import com.shui.nasor.BuildConfig;
import com.shui.nasor.Model.Bean.News.NewsEntity;
import com.shui.nasor.Model.Bean.Relax.GirlEntity;
import com.shui.nasor.Model.Bean.Relax.IMGJokerEntity;
import com.shui.nasor.Model.Bean.Relax.TXTJokerEntity;
import com.shui.nasor.Model.Bean.WeChat.WeChatEntity;
import com.shui.nasor.Model.Bean.Weather.WeatherEntity;
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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */

//网络管理类
public class RetrofitHelper {
    private static OkHttpClient okHttpClient=null;
    private static ZhihuApi zhihuApi=null;
    private static NewsApi newsApi;
    private static WeChatApi weChatApi;
    private void init()
    {
        initOkHttp();
        zhihuApi=getZhihuApi();
        newsApi=getNewsApi();
        weChatApi=getWeChatApi();
    }

    public RetrofitHelper() {
        init();
    }

    /**
     * 初始化OkHttp
     */
    private void initOkHttp() {
        /**
         * 参考http://www.jianshu.com/p/93153b34310e
         */
       OkHttpClient.Builder builder=new OkHttpClient.Builder();
        if (BuildConfig.DEBUG)
        {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile=new File(Constants.PATH_CACHE);//缓存文件位置
        Cache cache=new Cache(cacheFile,1024*1024*64);//缓存文件
        Interceptor cacheInterceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                if (!AppUtils.isNetworkConnected())
                {
                    //没网络时强制使用缓存
                    request=request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response=chain.proceed(request);
                if (AppUtils.isNetworkConnected())
                {
                    // 有网络时, 不缓存, 最大保存时长为0 有网络时 设置缓存超时时间0个小时
                    int maxAge=0;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("shui")//// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                }
                else {
                    // 无网络时，设置超时为30天
                    int maxTime=60*60*24*30;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxTime)
                            .removeHeader("shui")
                            .build();
                }
                return response;
            }
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        //设置超时
        builder.connectTimeout(12, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient=builder.build();
    }

    /**
     * 微信
     * @return
     */
    private static WeChatApi getWeChatApi() {
        Retrofit wechatRetrofit=new Retrofit.Builder()
                .baseUrl(WeChatApi.HOST_LINK)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return wechatRetrofit.create(WeChatApi.class);
    }

    /**
     * 新闻
     * @return
     */
    private static NewsApi getNewsApi() {
        Retrofit newsRetrofit=new Retrofit.Builder()
                .baseUrl(NewsApi.HOST_LINK)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return newsRetrofit.create(NewsApi.class);
    }

    /**
     * 知乎
     * @return
     */
    private static ZhihuApi getZhihuApi() {
        Retrofit zhihuRetrofit=new Retrofit.Builder()
                .baseUrl(ZhihuApi.HOST_LINK)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return zhihuRetrofit.create(ZhihuApi.class);
    }
    //***********************************知乎api
    public Observable<ZhihuWelcomeEntity> loadWelcome(String size)
    {
        return zhihuApi.getWelcome(size);
    }
    //
    public Observable<ZhihuDailyEntity> loadDaily()
    {
       return  zhihuApi.getDailyList();
    }
    //
    public Observable<ZhihuThemeListEntity> loadThemeList()
    {
        return zhihuApi.getThemeList();
    }
    //
    public Observable<ZhihuSectionListEntity> loadSectionList()
    {
        return zhihuApi.getSectionList();
    }
    public Observable<ZhihuHotEntity> loadHotList()
    {
        return zhihuApi.getHotList();
    }
    public Observable<ZhihuThemeDetailEntity> loadThemeDetail(int id)
    {
        return zhihuApi.getThemeDetail(id);
    }
    public Observable<ZhihuSectionDetailEntity> loadSectionDetail(int id)
    {
        return zhihuApi.getSectionDetail(id);
    }
    public Observable<ZhihuExtraEntity> loadExtra(int id)
    {
        return zhihuApi.getNewsExtra(id);
    }
    public Observable<ZhihuCommentEntity> loadLongComment(int id)
    {
        return zhihuApi.getLongComment(id);
    }
    public Observable<ZhihuCommentEntity> loadShortComment(int id)
    {
        return zhihuApi.getShortComment(id);
    }
    public Observable<ZhihuDetailEntity> loadNewsDetail(int id)
    {
        return zhihuApi.getNewsDetail(id);
    }
    //*******************************************************


    //**********************微信api***********************
    public Observable<WeChatEntity> loadWeChatSearch(int num, int page, String word)
    {
        return weChatApi.getWeChatSearch(num,page,word);
    }
    public Observable<WeChatEntity> loadWeChat(int num,int page)
    {
        return weChatApi.getWeChatInfo(num,page);
    }
    //*************************************************************


    //**************************易源 新闻 笑话api***************************
    public Observable<NewsEntity> loadLastedNews(int page)
    {
        return newsApi.getLastedNews(NewsApi.APPID,NewsApi.APPSCRIT,page);
    }
    public Observable<NewsEntity> loadNews(String title, int page)
    {
        return newsApi.getNews(NewsApi.APPID,NewsApi.APPSCRIT,title,page);
    }

    public Observable<TXTJokerEntity> loadTXTJoker(int page)
    {
        return newsApi.getJoker(NewsApi.APPID,NewsApi.APPSCRIT,page);
    }

    public Observable<IMGJokerEntity> loadIMGJoker(int page,int max)
    {
        return newsApi.getIMGJoker(NewsApi.APPID,NewsApi.APPSCRIT,page,max);
    }
    public Observable<GirlEntity> loadGirl(int page,int num)
    {
        return newsApi.getGirl(NewsApi.APPID,NewsApi.APPSCRIT,num,page);
    }
    public Observable<WeatherEntity> loadWeather(String area)
    {
        return newsApi.getWeather(NewsApi.APPID,NewsApi.APPSCRIT,area);
    }
    //***********************************************************
}
