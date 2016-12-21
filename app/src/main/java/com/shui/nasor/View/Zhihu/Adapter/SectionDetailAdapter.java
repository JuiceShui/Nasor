package com.shui.nasor.View.Zhihu.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.Zhihu.ZhihuSectionDetailEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.Widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/12/14.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */
//知乎专栏详细页的适配器

public class SectionDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ZhihuSectionDetailEntity.StoriesBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private onItemClickListener listener;
    public SectionDetailAdapter(List<ZhihuSectionDetailEntity.StoriesBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<ZhihuSectionDetailEntity.StoriesBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SectionHolder(mInflater.inflate(R.layout.holder_item_zhihudaily_content, parent, false));
    }
    public void setReadState(int position,boolean readState)
    {
        mData.get(position).setReadState(readState);
        notifyItemChanged(position);
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        SectionHolder sectionHolder= (SectionHolder) holder;
        ZhihuSectionDetailEntity.StoriesBean bean=mData.get(position);
        sectionHolder.tvDailyItemTitle.setText(bean.getTitle());
        if (bean.getReadState())
        {
            sectionHolder.tvDailyItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));
        }
        else
        {
            sectionHolder.tvDailyItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
        }
        ImageLoader.load(mContext,bean.getImages().get(0),((SectionHolder) holder).ivDailyItemImage);
        final String title=bean.getTitle();
        sectionHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null)
                {
                    ImageView imageView= (ImageView) view.findViewById(R.id.iv_daily_item_image);
                    listener.onItemClick(position,title,imageView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class SectionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_daily_item_image)
        SquareImageView ivDailyItemImage;
        @BindView(R.id.tv_daily_item_title)
        TextView tvDailyItemTitle;

        public SectionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface onItemClickListener
    {
        void onItemClick(int position,String title,ImageView shareView);
    }
    public void setItemClickListener(onItemClickListener listener)
    {
        this.listener=listener;
    }
}
