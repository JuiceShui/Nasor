package com.shui.nasor.View.Relax.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.shui.nasor.APP.App;
import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Model.RealmBean.LikeBean;
import com.shui.nasor.Model.RealmBean.LikeType;
import com.shui.nasor.R;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 作者： max_Shui on 2016/12/16.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class ImgJokerActivity extends BaseNormalActivity {
    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.photoImage)
    ImageView photoImage;
    private RealmHelper realmHelper;
    PhotoViewAttacher mAttacher;
    private MenuItem menuItem;
    private boolean isLiked;
    private String id;
    private String url;
    private String title;
    private Intent intent;
    Bitmap bitmap;
    @Override
    protected void initEventAndData() {
        intent=getIntent();
        id=intent.getStringExtra("id");
        url=intent.getStringExtra("url");
        title=intent.getStringExtra("title");
        realmHelper = App.getAppComponent().realmHelper();
        setToolbar(normalToolbar,title);
        if (url!=null)
        {
            Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    bitmap=resource;
                    photoImage.setImageBitmap(bitmap);
                    mAttacher=new PhotoViewAttacher(photoImage);
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_menu,menu);
        menuItem=menu.findItem(R.id.action_like);
        setLikeState(realmHelper.queryLike(id));
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_photo;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_like:
                if (isLiked)
                {
                    isLiked=false;
                    realmHelper.deleteLike(id);
                    menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
                }
                else {
                    isLiked=true;
                    LikeBean bean=new LikeBean();
                    bean.setPic(url);
                    bean.setId(id);
                    bean.setTime(System.currentTimeMillis());
                    bean.setTitle("");
                    bean.setType(LikeType.LIKE_RELAX);
                    bean.setUrl("");
                    realmHelper.insertLike(bean);
                    menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
                }
                break;
            case R.id.action_save:
                break;
            case R.id.action_share:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setLikeState(boolean state) {
        if(state) {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
            isLiked = true;
        } else {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
            isLiked = false;
        }
    }

    @Override
    public void onBackPressedSupport() {
        finishAfterTransition();
    }
}
