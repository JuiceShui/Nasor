package com.shui.nasor.Injection.Module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.shui.nasor.Injection.FragmentScope;

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
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    @FragmentScope
    public Activity provideFragment()
    {
        return fragment.getActivity();
    }
}
