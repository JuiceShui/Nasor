package com.shui.nasor.View.Zhihu.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shui.nasor.Model.Bean.Zhihu.ZhihuCommentEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.DateUtil;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.Widget.CircleImageView;

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


public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ZhihuCommentEntity.CommentsBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public CommentAdapter(List<ZhihuCommentEntity.CommentsBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }
    public void setData(List<ZhihuCommentEntity.CommentsBean> data)
    {
        this.mData=data;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(mInflater.inflate(R.layout.holder_item_zhihu_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentHolder commentHolder= (CommentHolder) holder;
        ZhihuCommentEntity.CommentsBean bean=mData.get(position);
        commentHolder.tvCommentName.setText(bean.getAuthor());
        commentHolder.tvCommentContent.setText(bean.getContent());
        commentHolder.tvCommentTime.setText(DateUtil.formatTime2String(bean.getTime()));
        commentHolder.tvCommentLike.setText(String.valueOf(bean.getLikes()));
        ImageLoader.load(mContext,bean.getAvatar(),commentHolder.civCommentFace);
        if (bean.getReply_to()!=null&&bean.getReply_to().getId()!=0)//有回复
        {
            commentHolder.tvCommentReply.setVisibility(View.VISIBLE);
            SpannableString spanString=new SpannableString("@"+bean.getReply_to().getAuthor()+":"+bean.getReply_to().getContent());
            spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext,R.color.comment_at))
                    ,0,bean.getReply_to().getAuthor().length()+2,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            commentHolder.tvCommentReply.setText(spanString);
        }
        else {
            commentHolder.tvCommentReply.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_comment_face)
        CircleImageView civCommentFace;
        @BindView(R.id.tv_comment_name)
        TextView tvCommentName;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;
        @BindView(R.id.tv_comment_reply)
        TextView tvCommentReply;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_comment_expand)
        TextView tvCommentExpand;
        @BindView(R.id.tv_comment_like)
        TextView tvCommentLike;
        public CommentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
