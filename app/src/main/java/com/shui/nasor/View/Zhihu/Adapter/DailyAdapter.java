package com.shui.nasor.View.Zhihu.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.Zhihu.ZhihuDailyEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.Widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/11/26.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ZhihuDailyEntity.TopStoriesBean> mTopBeanList;
    private List<ZhihuDailyEntity.StoriesBean> mStoriesBeanList;
    private TopPagerAdapter mAdapter;//顶部图片轮播的适配器
    private LayoutInflater mInflater;
    private onItemClickListener mListener;
    private ViewPager topViewPager;
    private String currentTitle = "今日热闻";
    public DailyAdapter(Context mContext, List<ZhihuDailyEntity.TopStoriesBean> mTopBeanList,
                        List<ZhihuDailyEntity.StoriesBean> mStoriesBeanList) {
        this.mContext = mContext;
        this.mTopBeanList = mTopBeanList;
        this.mStoriesBeanList = mStoriesBeanList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    private enum ItemType {
        TYPE_TOP,
        TYPE_CONTENT,
        TYPE_DATE;
    }

    /**
     * 设置数据
     * @param entity
     */
    public void setData(ZhihuDailyEntity entity)
    {
        if (entity!=null) {
            mTopBeanList = entity.getTop_stories();
            mStoriesBeanList=entity.getStories();
            notifyDataSetChanged();
        }
    }
    public List<ZhihuDailyEntity.StoriesBean> getData()
    {
        return mStoriesBeanList;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ItemType.TYPE_TOP.ordinal();
        } else if (position == 1) {
            return ItemType.TYPE_DATE.ordinal();
        } else {
            return ItemType.TYPE_CONTENT.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.TYPE_TOP.ordinal()) {
            mAdapter = new TopPagerAdapter(mContext, mTopBeanList);
            return new TopHolder(mInflater.inflate(R.layout.holder_item_zhihudaily_top, parent, false));
        } else if (viewType == ItemType.TYPE_DATE.ordinal()) {
            return new DateHolder(mInflater.inflate(R.layout.holder_item_zhihudaily_date, parent, false));
        } else {
            return new ContentHolder(mInflater.inflate(R.layout.holder_item_zhihudaily_content, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ZhihuDailyEntity.StoriesBean bean=mStoriesBeanList.get(position);
            if (holder instanceof ContentHolder)
            {

                ((ContentHolder) holder).tvDailyItemTitle.setText(bean.getTitle());
                if (bean.getReadState())
                {
                    ((ContentHolder)holder).tvDailyItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));
                }
                else
                {
                    ((ContentHolder)holder).tvDailyItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
                }
                ImageLoader.load(mContext,bean.getImages().get(0),((ContentHolder) holder).ivDailyItemImage);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            if (mListener!=null)
                            {
                                ImageView imageView= (ImageView) view.findViewById(R.id.iv_daily_item_image);
                                mListener.onItemClick(imageView,position);
                            }
                    }
                });
            }
            else if (holder instanceof DateHolder)
            {
                ((DateHolder) holder).tvDailyDate.setText(currentTitle);
            }
            else
            {
                ((TopHolder)holder).vpTop.setAdapter(mAdapter);
                topViewPager=((TopHolder) holder).vpTop;
            }
    }
    //设置已读状态
    public void setReadState(int position,boolean state)
    {
        mStoriesBeanList.get(position).setReadState(state);
    }
    public void changeTopPager(int position)
    {
        if (topViewPager!=null)
        {
            topViewPager.setCurrentItem(position);
        }
    }
    @Override
    public int getItemCount() {
        return mStoriesBeanList.size();
    }
    public void changeTopViewPager(int position)
    {
        if (topViewPager!=null)
        {
            topViewPager.setCurrentItem(position);
        }
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        this.mListener=listener;
    }
    /**
     * 内容的holder
     */
    public static class ContentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_daily_item_image)
        SquareImageView ivDailyItemImage;
        @BindView(R.id.tv_daily_item_title)
        TextView tvDailyItemTitle;
        public ContentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * viewpager的holder
     */
    public static class TopHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_top)
        ViewPager vpTop;
        @BindView(R.id.ll_point_container)
        LinearLayout llPointContainer;

        public TopHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 日期的holder
     */
    public static class DateHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_daily_date)
        TextView tvDailyDate;

        public DateHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface onItemClickListener
    {
        void onItemClick(View view, int position);
    }
}
