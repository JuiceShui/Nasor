package com.shui.nasor.Injection.Component;

import android.app.Activity;

import com.shui.nasor.Injection.FragmentScope;
import com.shui.nasor.Injection.Module.FragmentModule;
import com.shui.nasor.View.Like.Fragment.LikeFragment;
import com.shui.nasor.View.News.Fragment.NewsFragment;
import com.shui.nasor.View.Relax.Fragment.GirlFragment;
import com.shui.nasor.View.Relax.Fragment.IMGJokerFragment;
import com.shui.nasor.View.Relax.Fragment.RelaxMainFragment;
import com.shui.nasor.View.Relax.Fragment.TxTJokerFragment;
import com.shui.nasor.View.WeChat.Fragment.WeChatFragment;
import com.shui.nasor.View.Zhihu.Fragment.CommentFragment;
import com.shui.nasor.View.Zhihu.Fragment.CommmetFragment;
import com.shui.nasor.View.Zhihu.Fragment.DailyFragment;
import com.shui.nasor.View.Zhihu.Fragment.HotFragment;
import com.shui.nasor.View.Zhihu.Fragment.SectionFragment;
import com.shui.nasor.View.Zhihu.Fragment.ThemeFragment;
import com.shui.nasor.View.Zhihu.Fragment.ZhiHuMainFragment;

import dagger.Component;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */
@FragmentScope
@Component(modules = FragmentModule.class,dependencies = AppComponent.class)
public interface FragmentComponent {
    Activity getActivity();
    void Inject(ZhiHuMainFragment zhiHuMainFragment);
    void Inject(DailyFragment dailyFragment);
    void Inject(ThemeFragment themeFragment);
    void Inject(SectionFragment sectionFragment);
    void Inject(HotFragment hotFragment);
    void Inject(WeChatFragment weChatFragment);
    void Inject(RelaxMainFragment relaxMainFragment);
    void Inject(LikeFragment likeFragment);
    void Inject(CommentFragment commentFragment);
    void Inject(CommmetFragment commentFragment);
    void Inject(TxTJokerFragment txTJokerFragment);
    void Inject(IMGJokerFragment imgJokerFragment);
    void Inject(GirlFragment girlFragment);
    void Inject(NewsFragment newsFragment);
}
