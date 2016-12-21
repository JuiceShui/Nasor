package com.shui.nasor.View.Relax.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.Relax.TXTJokerEntity;
import com.shui.nasor.R;

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


public class TXTJokerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<TXTJokerEntity.ShowapiResBodyBean.ContentlistBean> mData;
    private LayoutInflater mInflater;

    public TXTJokerAdapter(Context mContext, List<TXTJokerEntity.ShowapiResBodyBean.ContentlistBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JokerTXTHolder(mInflater.inflate(R.layout.holder_item_joker_txt,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        JokerTXTHolder jHolder= (JokerTXTHolder) holder;
        TXTJokerEntity.ShowapiResBodyBean.ContentlistBean bean=mData.get(position);
        jHolder.jokerTxtTitle.setText(bean.getTitle());
        jHolder.jokerTxtContent.setText(Html.fromHtml(bean.getText()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class JokerTXTHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jokerTxtTitle)
        TextView jokerTxtTitle;
        @BindView(R.id.jokerTxtContent)
        TextView jokerTxtContent;


        public JokerTXTHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

