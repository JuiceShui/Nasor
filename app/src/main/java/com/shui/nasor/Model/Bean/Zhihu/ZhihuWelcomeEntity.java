package com.shui.nasor.Model.Bean.Zhihu;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 作者： max_Shui on 2016/12/11.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class ZhihuWelcomeEntity implements Serializable{
    private static final long serialVersionUID = 6065177763126366034L;

    /**
     * text : Elena Prokofyeva
     * img : https://pic4.zhimg.com/v2-f2cf38f62276ffe6c86f7d0a31a38e9f_xxdpi.jpg
     */

    private String text;
    private String img;

    public static ZhihuWelcomeEntity objectFromData(String str) {

        return new Gson().fromJson(str, ZhihuWelcomeEntity.class);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
