package com.shui.nasor.Injection.Component;

import com.shui.nasor.APP.App;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Injection.AppScope;
import com.shui.nasor.Injection.Module.AppModule;
import com.shui.nasor.Http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    @AppScope("application")
    App getApp();//提供app
    RetrofitHelper retrofitHelper();//提供RetrofitHelper
    RealmHelper realmHelper();//提供RealmHelper
}
