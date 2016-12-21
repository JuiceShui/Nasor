package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.WeChat.WeChatEntity;

/**
 * 作者： max_Shui on 2016/12/15.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface WeChatContract {
    interface View extends BaseView
    {
        void showContent(WeChatEntity entity);//展示类容
        void showMore(WeChatEntity entity);//加载更多
        void showSearch(WeChatEntity entity);//执行搜索
    }
    interface Presenter extends BasePresenter<View>
    {
        void getData();//获取数据
        void getMore();//获取更多
        void getSearch(String word);//获取搜索
    }
}
