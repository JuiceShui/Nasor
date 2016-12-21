package com.shui.nasor.Injection.Component;

import android.app.Activity;

import com.shui.nasor.Injection.ActivityScope;
import com.shui.nasor.Injection.Module.ActivityModule;
import com.shui.nasor.View.Home.HomeActivity;
import com.shui.nasor.View.Home.WeatherActivity;
import com.shui.nasor.View.Home.WelcomeActivity;
import com.shui.nasor.View.Zhihu.Activity.SectionDetailActivity;
import com.shui.nasor.View.Zhihu.Activity.ThemeDetailActivity;
import com.shui.nasor.View.Zhihu.Activity.ZhihuDetailActivity;

import dagger.Component;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */
@ActivityScope
@Component(modules = ActivityModule.class,dependencies = AppComponent.class)
public interface ActivityComponent {
    Activity getActivity();
    void Inject (HomeActivity homeActivity);
    void Inject (WelcomeActivity welcomeActivity);
    void Inject (ZhihuDetailActivity zhihuDetailActivity);
    void Inject (ThemeDetailActivity themeDetailActivity);
    void Inject(SectionDetailActivity sectionDetailActivity);
    void Inject(WeatherActivity weatherActivity);
}
