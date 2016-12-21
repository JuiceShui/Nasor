package com.shui.nasor.View.Like.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shui.nasor.APP.App;
import com.shui.nasor.APP.Constants;
import com.shui.nasor.Model.RealmBean.LikeBean;
import com.shui.nasor.Model.RealmBean.LikeType;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.View.Relax.Activity.PhotoActivity;
import com.shui.nasor.View.WeChat.Activity.WeChatActivity;
import com.shui.nasor.View.Zhihu.Activity.ZhihuDetailActivity;
import com.shui.nasor.Widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/12/15.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class LikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<LikeBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public LikeAdapter(List<LikeBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        LikeBean bean = mData.get(position);
        return bean.getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LikeType.LIKE_WECHAT || viewType == LikeType.LIKE_ZHIHU) {
            return new NormalHolder(mInflater.inflate(R.layout.holder_item_like_normal, parent, false));
        } else if (viewType == LikeType.LIKE_NEWS) {
            return new NewsHolder(mInflater.inflate(R.layout.holder_item_like_news, parent, false));
        } else {
            return new PictureHolder(mInflater.inflate(R.layout.holder_item_like_image, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final LikeBean bean = mData.get(position);
        if (holder instanceof NormalHolder) {
            ((NormalHolder) holder).likeTVTitle.setText(bean.getTitle());
            ImageLoader.load(mContext, bean.getPic(), ((NormalHolder) holder).likeImageView);
            switch (bean.getType()) {
                case LikeType.LIKE_WECHAT:
                    ((NormalHolder) holder).likeTVFrom.setText(App.getInstance().getString(R.string.from_wechat));
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            goToWeChat(bean.getId(),bean.getTitle(),bean.getUrl(),holder.itemView);
                        }
                    });
                    break;
                case LikeType.LIKE_ZHIHU:
                    ((NormalHolder) holder).likeTVFrom.setText(App.getInstance().getString(R.string.from_zhihu));
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            goToZhihu(Integer.valueOf(bean.getId()), bean.getTitle(), ((NormalHolder) holder).likeImageView);
                        }
                    });
                    break;
            }
        }
        else if (holder instanceof PictureHolder)
        {
            ((PictureHolder) holder).likePicTVFrom.setText(App.getInstance().getString(R.string.from_girl));
            ImageLoader.load(mContext,bean.getPic(),((PictureHolder) holder).likePicImageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView imageView= (ImageView) view.findViewById(R.id.likePicImageView);
                    goToMeizhi(imageView,bean.getId(),bean.getPic());
                }
            });
        }
        if (holder instanceof NewsHolder)
        {
            ((NewsHolder) holder).likeNewsTitle.setText(bean.getTitle());
            ((NewsHolder) holder).likeNewsTVFrom.setText(App.getInstance().getString(R.string.from_news));
            ((NewsHolder) holder).likeNewsTvTime.setText(String.valueOf(bean.getTime()));
            ImageLoader.load(mContext,bean.getPic(),((NewsHolder) holder).likeNewsImageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToNews();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 知乎 和 微信的样式
     */
    public class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.likeImageView)
        SquareImageView likeImageView;
        @BindView(R.id.likeTVTitle)
        TextView likeTVTitle;
        @BindView(R.id.likeTVFrom)
        TextView likeTVFrom;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 新闻的样式
     */
    public class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.likeNewsTitle)
        TextView likeNewsTitle;
        @BindView(R.id.likeNewsImageView)
        SquareImageView likeNewsImageView;
        @BindView(R.id.list_item_news_list_top_layout)
        LinearLayout listItemNewsListTopLayout;
        @BindView(R.id.likeNewsTVFrom)
        TextView likeNewsTVFrom;
        @BindView(R.id.likeNewsTvTime)
        TextView likeNewsTvTime;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 图片的样式
     */
    public class PictureHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.likePicImageView)
        ImageView likePicImageView;
        @BindView(R.id.likePicTVFrom)
        TextView likePicTVFrom;

        public PictureHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    //转到知乎
    private void goToZhihu(int id, String title, View shareView) {
        Intent intent = new Intent(mContext, ZhihuDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, shareView, Constants.SHARE_VIEW);
        mContext.startActivity(intent, options.toBundle());
    }

    //转到微信
    private void goToWeChat(String id,String title,String link,View shareView) {
        Intent intent = new Intent(mContext, WeChatActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("link",link);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, shareView, Constants.SHARE_VIEW);
        mContext.startActivity(intent, options.toBundle());
    }
    //转到妹纸
    private void goToMeizhi(ImageView imageView,String id,String picUrl)
    {
        Intent intent=new Intent(mContext, PhotoActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("url",picUrl);
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,imageView,Constants.SHARE_VIEW);
        mContext.startActivity(intent,options.toBundle());
    }
    //转到新闻
    private void goToNews()
    {

    }
}
