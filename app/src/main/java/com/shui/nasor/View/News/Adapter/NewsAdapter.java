package com.shui.nasor.View.News.Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.News.NewsEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/12/29.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * 新闻信息的适配器
 */


public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mData=new ArrayList<>();
    private List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mTopData=new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private NewsTopAdapter mTopAdapter;//顶部的adapter
    private ViewPager mTopViewPager;//顶部的viewpager
    private onItemClickListener listener;

    public NewsAdapter(List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }
    /**
     * 设置数据
     * @param entity
     */
    public void setData(List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> entity)
    {
        if (entity!=null) {
            for (int i=0;i<10;i++)
            {
                if (mData.get(i).isHavePic()&&mData.get(i).getImageurls().size()>1)
                {
                    mTopData.add(mData.get(i));
                }
            }
            mData=entity;
            notifyDataSetChanged();
        }
    }
    private enum ItemType {
        TYPE_TOP,
        TYPE_CONTENT,
        TYPE_IMAGE;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ItemType.TYPE_TOP.ordinal();
        }
        if (mData.get(position).getImageurls().size() > 4) {
            return ItemType.TYPE_IMAGE.ordinal();
        } else {
            return ItemType.TYPE_CONTENT.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==ItemType.TYPE_TOP.ordinal())
        {
            mTopAdapter=new NewsTopAdapter(mTopData,mContext);
            mTopAdapter.notifyDataSetChanged();
            return new TopViewHolder(mInflater.inflate(R.layout.holder_item_zhihudaily_top,parent,false));
        }
        else if (viewType==ItemType.TYPE_CONTENT.ordinal())
        {
            return new ContentViewHolder(mInflater.inflate(R.layout.holder_item_news_list,parent,false));
        }
        else
        {
            return new ImageViewHolder(mInflater.inflate(R.layout.holder_item_news_pics,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean bean=mData.get(position);
            final String url=bean.getLink();
            final String title=bean.getTitle();
        if (holder instanceof ContentViewHolder)
        {
            ((ContentViewHolder) holder).listItemNewsListSource.setText(bean.getSource());
            ((ContentViewHolder) holder).listItemNewsListDate.setText(bean.getPubDate());
            ((ContentViewHolder) holder).listItemNewsListTitle.setText(bean.getTitle());
            if (bean.getImageurls().size()>0)
            {
                ImageLoader.load(mContext, bean.getImageurls().get(0).getUrl(),((ContentViewHolder) holder).listItemNewsListImage);
            }
            else
            {
                ((ContentViewHolder) holder).listItemNewsListImage.setImageResource(R.drawable.no_pic);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null)
                    {
                        listener.onItemClick(url,title,holder.itemView);
                    }
                }
            });
        }
        else if (holder instanceof ImageViewHolder)
        {
            ImageLoader.load(mContext, bean.getImageurls().get(0).getUrl(),((ImageViewHolder) holder).ivNewsPicOne);
            ImageLoader.load(mContext, bean.getImageurls().get(1).getUrl(),((ImageViewHolder) holder).ivNewsPicTwo);
            ImageLoader.load(mContext, bean.getImageurls().get(2).getUrl(),((ImageViewHolder) holder).ivNewsPicThree);
            ((ImageViewHolder) holder).tvNewsPicTitle.setText(bean.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null)
                    {
                     listener.onItemClick(url,title,holder.itemView);
                    }
                }
            });
        }
        else if (holder instanceof TopViewHolder)
        {
            ((TopViewHolder) holder).vpTop.setAdapter(mTopAdapter);
            mTopViewPager=((TopViewHolder) holder).vpTop;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 改变图片
     * @param position
     */
    public void changeTopPager(int position)
    {
        if (mTopViewPager!=null)
        {
            mTopViewPager.setCurrentItem(position);
        }
    }
    /**
     * 顶部布局
     */
    public class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_top)
        ViewPager vpTop;
        @BindView(R.id.ll_point_container)
        LinearLayout llPointContainer;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 普通item
     */
    public class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_item_news_list_title)
        TextView listItemNewsListTitle;
        @BindView(R.id.list_item_news_list_image)
        ImageView listItemNewsListImage;
        @BindView(R.id.list_item_news_list_source)
        TextView listItemNewsListSource;
        @BindView(R.id.list_item_news_list_date)
        TextView listItemNewsListDate;

        ContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 多图的item
     */
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_news_pic_title)
        TextView tvNewsPicTitle;
        @BindView(R.id.iv_news_pic_one)
        ImageView ivNewsPicOne;
        @BindView(R.id.iv_news_pic_two)
        ImageView ivNewsPicTwo;
        @BindView(R.id.iv_news_pic_three)
        ImageView ivNewsPicThree;
        @BindView(R.id.ll_news_image_container)
        LinearLayout llNewsImageContainer;
        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        this.listener=listener;
    }
    public interface onItemClickListener
    {
        void onItemClick(String url,String title,View shareView);
    }
}
