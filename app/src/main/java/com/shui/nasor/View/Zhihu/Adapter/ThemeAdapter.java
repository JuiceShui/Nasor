package com.shui.nasor.View.Zhihu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.Zhihu.ZhihuThemeListEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/11/27.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * themeFragment的适配器
 */


public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ZhihuThemeListEntity.OthersBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private onItemClickListener mListener;

    public ThemeAdapter(List<ZhihuThemeListEntity.OthersBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemeViewHolder(mInflater.inflate(R.layout.holder_item_zhihutheme_content,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ZhihuThemeListEntity.OthersBean bean=mData.get(position);
        if (holder instanceof ThemeViewHolder)
        {
            ((ThemeViewHolder) holder).holderThemeTitle.setText(bean.getName());
            ImageLoader.load(mContext,bean.getThumbnail(),((ThemeViewHolder) holder).holderThemeImageView);
            final int id=bean.getId();
            final String name=bean.getName();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener!=null)
                    {
                        mListener.onItemClick(id,name);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 设置数据
     * @param data
     */
    public void setData(List<ZhihuThemeListEntity.OthersBean> data)
    {
        if (data!=null) {
            this.mData = data;
            notifyDataSetChanged();
        }
    }
    public static class ThemeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.themeImageView)
        ImageView holderThemeImageView;
        @BindView(R.id.themeTitle)
        TextView holderThemeTitle;
        public ThemeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    //设置listener
    public void setOnItemClickListener(onItemClickListener listener)
    {
        this.mListener=listener;
    }
    //item点击接口
    public interface onItemClickListener
    {
        void onItemClick(int id, String name);
    }
}
