package com.shui.nasor.Injection.Module;

import com.shui.nasor.APP.App;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Injection.AppScope;
import com.shui.nasor.Http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */

@Module
public class AppModule {
    private App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }
    @Provides
    @Singleton
    @AppScope("application")
    public App provideApp()
    {
        return mApp;
    }
    @Provides
    @Singleton
    public RetrofitHelper provideRetrofitHelper()
    {
        return new RetrofitHelper();
    }
    @Provides
    @Singleton
    public RealmHelper provideRealmHelper()
    {
        return new RealmHelper(mApp);
    }
}
