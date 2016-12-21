package com.shui.nasor.Presenter.Contract;

import com.shui.nasor.Base.BasePresenter;
import com.shui.nasor.Base.BaseView;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuDetailEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuExtraEntity;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public interface ZhihuNewsContract {
    interface View extends BaseView
    {
        void showContent(ZhihuDetailEntity entity);
        void showExtra(ZhihuExtraEntity entity);
        void setLikeState(boolean likeState);//设置喜欢
    }
    interface Presenter extends BasePresenter<View>
    {
        void getData(int id);//获取数据
        void getExtra(int id);//获取额外数据
        void queryLike(int id);//获取是否喜欢
        void insertLike();//添加一个喜欢
        void deleteLike();//删除一个喜欢
    }
}
