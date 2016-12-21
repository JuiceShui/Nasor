package com.shui.nasor.View.WeChat.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.WeChat.WeChatEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
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


public class WeChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WeChatEntity.NewslistBean> mData;
    private Context context;
    private LayoutInflater mInflater;
    private onItemClickListener listener;
    public WeChatAdapter(Context context, List<WeChatEntity.NewslistBean> mData) {
        this.context = context;
        this.mData = mData;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WechatHolder(mInflater.inflate(R.layout.holder_item_wechat_content,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final WechatHolder wechatHolder= (WechatHolder) holder;
        final WeChatEntity.NewslistBean bean=mData.get(position);
        wechatHolder.tvWechatItemFrom.setText(bean.getDescription());
        wechatHolder.tvWechatItemTime.setText(bean.getCtime());
        wechatHolder.tvWechatItemTitle.setText(bean.getTitle());
        ImageLoader.load(context,bean.getPicUrl(),wechatHolder.ivWechatItemImage);
        wechatHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null) {
                    listener.onItemClick(wechatHolder.itemView,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class WechatHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_wechat_item_image)
        SquareImageView ivWechatItemImage;
        @BindView(R.id.tv_wechat_item_title)
        TextView tvWechatItemTitle;
        @BindView(R.id.tv_wechat_item_from)
        TextView tvWechatItemFrom;
        @BindView(R.id.tv_wechat_item_time)
        TextView tvWechatItemTime;

        public WechatHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface onItemClickListener
    {
        void onItemClick(View shareView,int position);
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        this.listener=listener;
    }
}
