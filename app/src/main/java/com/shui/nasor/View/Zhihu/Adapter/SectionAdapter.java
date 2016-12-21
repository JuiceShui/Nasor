package com.shui.nasor.View.Zhihu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuSectionListEntity;
import com.shui.nasor.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/11/27.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * 知乎专栏的适配器
 */


public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ZhihuSectionListEntity.DataBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private onItemClickListener mListener;
    public SectionAdapter(List<ZhihuSectionListEntity.DataBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ColumnHolder(mInflater.inflate(R.layout.holder_item_section_content,parent,false));
    }
    public void setData(List<ZhihuSectionListEntity.DataBean> data)
    {
        this.mData=data;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        this.mListener=listener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ColumnHolder)
        {
            ZhihuSectionListEntity.DataBean bean=mData.get(position);
            ((ColumnHolder) holder).holderColumnTVKind.setText(bean.getName());
            ((ColumnHolder) holder).holderColumnTVDes.setText(bean.getDescription());
            Glide.with(mContext)
                    .load(bean.getThumbnail())
                    .crossFade()
                    .placeholder(R.drawable.loading)
                    .into(((ColumnHolder) holder).holderColumnImageView);
            final int id=bean.getId();
            final String title=bean.getName();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener!=null)
                    {
                        mListener.onItemClick(id,title);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ColumnHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.holderColumnImageView)
        ImageView holderColumnImageView;
        @BindView(R.id.holderColumnTVKind)
        TextView holderColumnTVKind;
        @BindView(R.id.holderColumnTVDes)
        TextView holderColumnTVDes;

        public ColumnHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface onItemClickListener
    {
        void onItemClick(int id, String title);
    }
}
