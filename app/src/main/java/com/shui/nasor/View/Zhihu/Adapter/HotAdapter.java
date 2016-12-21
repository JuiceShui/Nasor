package com.shui.nasor.View.Zhihu.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.Zhihu.ZhihuHotEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.Widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/11/27.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ZhihuHotEntity.RecentBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private onItemClickListener listener;
    public HotAdapter(List<ZhihuHotEntity.RecentBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater=LayoutInflater.from(mContext);
    }

    public void setData(List<ZhihuHotEntity.RecentBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    //设置已读 并刷新
    public void setReadState(int position)
    {
        mData.get(position).setReadState(true);
        notifyItemChanged(position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotHolder(mInflater.inflate(R.layout.holder_item_zhihudaily_content,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HotHolder)
        {
            final ZhihuHotEntity.RecentBean bean=mData.get(position);
            ((HotHolder) holder).hotItemTitle.setText(bean.getTitle());
            if (bean.getReadState())
            {
                ((HotHolder) holder).hotItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));
            }
            else {
                ((HotHolder) holder).hotItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
            }
            ImageLoader.load(mContext,bean.getThumbnail(),((HotHolder) holder).hotItemImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null)
                    {   ImageView iv = (ImageView) view.findViewById(R.id.iv_daily_item_image);
                        listener.onItemClick(iv,position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class HotHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_daily_item_image)
        SquareImageView hotItemImage;
        @BindView(R.id.tv_daily_item_title)
        TextView hotItemTitle;


        public HotHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface onItemClickListener
    {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        this.listener=listener;
    }
}
