package com.shui.nasor.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.shui.nasor.APP.App;
import com.shui.nasor.APP.Constants;

/**
 * 作者： max_Shui on 2016/12/11.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class SharedPreferenceUtils {
    private static final String SP_NAME="mysp";
    private static final int DEFAULT_ITEM= Constants.TYPE_ZHIHU;//默认
    public static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
    public static int getCurrentItem() {
        return getAppSp().getInt(Constants.SP_CURRENT_ITEM, DEFAULT_ITEM);
    }

    public static void setCurrentItem(int item) {
        getAppSp().edit().putInt(Constants.SP_CURRENT_ITEM, item).apply();
    }
    public static void setPosition(String position)
    {
        getAppSp().edit().putString(Constants.SP_POSITION,position).apply();
    }
    public static String getPosition()
    {
        return getAppSp().getString(Constants.SP_POSITION,"");
    }
    public static void setUser(String position)
    {
        getAppSp().edit().putString(Constants.SP_USER_INFO,position).apply();
    }
    public static String getUser()
    {
        return getAppSp().getString(Constants.SP_USER_INFO,"");
    }
}
