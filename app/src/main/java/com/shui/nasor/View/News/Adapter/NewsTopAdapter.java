package com.shui.nasor.View.News.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.News.NewsEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.View.News.Activity.NewsDetailActivity;

import java.util.List;

/**
 * 作者： max_Shui on 2016/12/29.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * 新闻顶部 viewpager适配器
 */


public class NewsTopAdapter extends PagerAdapter {
    private List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    public NewsTopAdapter(List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=mInflater.inflate(R.layout.item_top_pager,container,false);
        final ImageView imageView= (ImageView) view.findViewById(R.id.iv_top_image);
        TextView textView= (TextView) view.findViewById(R.id.tv_top_title);
        NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean bean=mData.get(position);
        NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ImageurlsBean imageurlsBean=bean.getImageurls().get(0);
        ImageLoader.load(mContext,imageurlsBean.getUrl(),imageView);
        textView.setText(bean.getTitle());
        //final int id=bean.getId();
        final String url=bean.getLink();
        final String title=bean.getTitle();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, NewsDetailActivity.class);
                imageView.setTransitionName("shareView");
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,imageView,"shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
