package com.shui.nasor.DB;

import android.content.Context;

import com.shui.nasor.Model.Bean.MyData.RealmUserEntity;
import com.shui.nasor.Model.RealmBean.LikeBean;
import com.shui.nasor.Model.RealmBean.ReadBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class RealmHelper {
    private static final String REALM_NAME="myDB.realm";
    private Realm mRealm;
    public RealmHelper(Context context) {
        mRealm=Realm.getInstance(new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .name(REALM_NAME)
                .build());
    }

    /**
     * 查询是否已经读取
     * @param id
     * @return
     */
    public boolean queryRead(int id)
    {
        RealmResults<ReadBean> list=mRealm.where(ReadBean.class).findAll();
        for (ReadBean bean:list)
        {
         if (id==bean.getId())
         {
             return true;
         }
        }
        return false;
    }

    /**
     * 增加一个阅读状态
     * @param id
     */
    public void insertRead(int id)
    {
        ReadBean bean=new ReadBean();
        bean.setId(id);
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }
    /**
     * 增加一个收藏
     * @param bean
     */
    public void insertLike(LikeBean bean)
    {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }

    /**
     * 查询是否是已经收藏
     * @param id
     * @return
     */
    public boolean queryLike(String id)
    {
        RealmResults<LikeBean> results=mRealm.where(LikeBean.class).findAll();
        for (LikeBean bean:results)
        {
            if (bean.getId()==id||bean.getId().equals(id))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除一个收藏记录
     * @param id
     */
    public void deleteLike(String id)
    {
        LikeBean bean=mRealm.where(LikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        bean.deleteFromRealm();
        mRealm.commitTransaction();
    }

    /**
     * 获取收藏列表
     * @return
     */
    public List<LikeBean> getLikeData()
    {
        RealmResults<LikeBean> list=mRealm.where(LikeBean.class).findAllSorted("time");
        return mRealm.copyFromRealm(list);
    }

    /**
     *因为获取的数据 是按时间大小来排的 所以时间越近  在集合中的位置越靠后 所以当isUp时  即是代表时间越近 也就是++
     * @param id  数据id
     * @param time  更新的时间
     * @param isUp  是否是向上更新
     */
    public void changeTime(String id,long time,boolean isUp)
    {
        LikeBean bean=mRealm.where(LikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if (isUp)
        {
            bean.setTime(++time);//在目标item 即是改变过后的位置的时间上+1
        }
        else
        {
            bean.setTime(--time);
        }
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }

    /**
     * 将用户数据注入本地数据库
     * @param userInfo
     */
    public void InsertUserInfo(RealmUserEntity userInfo)
    {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(userInfo);
        mRealm.commitTransaction();
    }

    /**
     * 根据objectID获取用户信息
     * @param id
     * @return
     */
    public RealmUserEntity queryUserInfo(String id)
    {
        RealmResults<RealmUserEntity> results=mRealm.where(RealmUserEntity.class).findAll();
        for (RealmUserEntity bean:results)
        {
            if (bean.getObjectId()==id||bean.getObjectId().equals(id))
            {
                return bean;
            }
        }
        return null;
    }
}
