package com.shui.nasor.APP;

import android.os.Environment;

import java.io.File;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */

//常数
public class Constants {
    //--------------------TYPE-------------------

    public static final int TYPE_ZHIHU = 100;

    public static final int TYPE_NEWS = 101;

    public static final int TYPE_WECHAT = 102;

    public static final int TYPE_RELAX = 103;

    public static final int TYPE_PHOTO = 104;

    public static final int TYPE_LIKE = 105;

    public static final int TYPE_SETTING = 106;

    public static final int TYPE_ABOUT = 107;

    //-------------------PATH-------------------------//
    public static final String PATH_DATA=App.getInstance().getCacheDir().getAbsolutePath()+ File.separator+"data";
    public static final String PATH_CACHE=PATH_DATA+"/netCache";
    public static final String PATH_EXTERNAL_CARD= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Shui"+File.separator+"dexter";
    //--------------------MODE-----------------------//
    public static final String SP_NIGHT_MODE="NIGHT_MODE";

    //-------------------sp--------------
    public static final String SP_CURRENT_ITEM = "current_item";
    public static  final String SP_POSITION="position";
    public  static  final String SP_USER_INFO="user_info";
    public static final String SP_USER_ENTITY="user_entity";
    //--------------------shareView-----------------
    public static final String SHARE_VIEW = "shareView";

    //-----------------activity constants----activity request code and result code
    public static final int ACTIVITY_REQUEST = 11;
    public static final int ACTIVITY_RESULT=21;
}
