package com.shui.nasor.APP;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.shui.nasor.Injection.Component.AppComponent;
import com.shui.nasor.Injection.Component.DaggerAppComponent;
import com.shui.nasor.Injection.Module.AppModule;

import java.util.HashSet;
import java.util.Set;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
        instance = this;
        //初始化屏幕宽高
        getScreenSize();
        //初始化tbs x5 webview

        //初始化bmob
        BmobConfig config =new BmobConfig.Builder(this)
                ////设置appkey
                .setApplicationId("e260f6ba0552beb9de37c252ce32fb7f")
                ////请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                ////文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024*1024)
                ////文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);

        //初始化shareSDK
        SMSSDK.initSDK(this, "1a39fef440794", "764c21865adb620490ba489edb17bf11");
        ShareSDK.initSDK(this,"1a1f6f16411c2");
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public  void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }
    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

}
