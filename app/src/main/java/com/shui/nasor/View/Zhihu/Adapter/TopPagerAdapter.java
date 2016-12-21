package com.shui.nasor.View.Zhihu.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuDailyEntity;
import com.shui.nasor.R;

import java.util.List;

/**
 * 作者： max_Shui on 2016/11/26.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * 知乎日报的头部viewpager的适配器！
 */


public class TopPagerAdapter extends PagerAdapter {
    private List<ZhihuDailyEntity.TopStoriesBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    public TopPagerAdapter(Context context,List<ZhihuDailyEntity.TopStoriesBean> mData) {
        this.mData = mData;
        this.mContext=context;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        View view=mInflater.inflate(R.layout.item_top_pager,container,false);
        final ImageView imageView= (ImageView) view.findViewById(R.id.iv_top_image);
        TextView textView= (TextView) view.findViewById(R.id.tv_top_title);
        ZhihuDailyEntity.TopStoriesBean bean=mData.get(position);
        Glide.with(mContext)
                .load(bean.getImage())
                .crossFade()
                .placeholder(R.drawable.loading)
                .into(imageView);
        textView.setText(bean.getTitle());
        final int id=bean.getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /** Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                imageView.setTransitionName("shareView");
                intent.putExtra("id",id);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,imageView,"shareView");
                mContext.startActivity(intent,options.toBundle());
                **/
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
