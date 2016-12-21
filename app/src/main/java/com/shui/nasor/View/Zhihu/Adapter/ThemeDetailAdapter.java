package com.shui.nasor.View.Zhihu.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.Zhihu.ZhihuThemeDetailEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.Widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/11/30.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * 知乎主题详情的的适配器
 */


public class ThemeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ZhihuThemeDetailEntity.StoriesBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private onItemClickListener listener;
    public ThemeDetailAdapter(List<ZhihuThemeDetailEntity.StoriesBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemeViewHolder(mInflater.inflate(R.layout.holder_item_zhihudaily_content,parent,false));
    }
    public void setData(List<ZhihuThemeDetailEntity.StoriesBean> mData)
    {
        this.mData=mData;
        notifyDataSetChanged();
    }
    public void setReadState(int position)
    {
        mData.get(position).setReadState(true);
        notifyItemChanged(position);//通知改变
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, final int position) {
            final ThemeViewHolder holder= (ThemeViewHolder) holder1;
            ZhihuThemeDetailEntity.StoriesBean bean=mData.get(position);
             holder.tvItemTitle.setText(bean.getTitle());
        if (bean.getReadState())
        {
            holder.tvItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));
        }
        else {
            holder.tvItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
        }
        if (bean.getImages() != null&&bean.getImages().size()>0) {
            ImageLoader.load(mContext, bean.getImages().get(0), holder.ivItemImage);
        }
        else {
            holder.ivItemImage.setImageResource(R.drawable.no_pic);
        }
             holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (listener!=null)
                    {
                        listener.onItemClick(position,holder.ivItemImage);
                    }
               }
          });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ThemeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_daily_item_image)
        SquareImageView ivItemImage;
        @BindView(R.id.tv_daily_item_title)
        TextView tvItemTitle;


        public ThemeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface onItemClickListener
    {
        void onItemClick(int position, View shareView);
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        this.listener=listener;
    }
}
