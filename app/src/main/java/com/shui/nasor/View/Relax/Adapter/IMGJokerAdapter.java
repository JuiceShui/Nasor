package com.shui.nasor.View.Relax.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shui.nasor.APP.AppUtils;
import com.shui.nasor.Model.Bean.Relax.IMGJokerEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/12/16.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class IMGJokerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<IMGJokerEntity.ShowapiResBodyBean.ContentlistBean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private final static float SIZE_SCALE_01 = 4 / 3f;
    private final static float SIZE_SCALE_02 = 4 / 4f;
    private HashMap<Integer, Float> indexMap = new HashMap<Integer, Float>();
    private int screenWidth;
    private OnItemClickListener listener;
    private onHeaderClickListener headerClickListener;
    private onFooterClickListener footerClickListener;
    //布局类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    private int mHeaderCount = 1;//头布局个数
    private int mFooterCount = 1;//尾布局个数

    public IMGJokerAdapter(List<IMGJokerEntity.ShowapiResBodyBean.ContentlistBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        screenWidth = AppUtils.getScreenSize(mContext)[0];
        this.mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 获取内容的个数
     *
     * @return
     */
    public int getContentSize() {
        return mData.size();
    }

    /**
     * 当前的item是否是尾布局
     *
     * @param position 当前位置
     * @return 是||否
     */
    public boolean isFooterView(int position) {
        return mFooterCount != 0 && (position >= getContentSize() + mHeaderCount);
    }

    /**
     * 当前item是否是头布局
     *
     * @param position
     * @return
     */
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && (position < mHeaderCount);
    }

    /**
     * 根据位置判断当前的view是什么
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int dataCount = getContentSize();//获取数据的个数 并赋值给dataCount
        if (position < mHeaderCount && mHeaderCount != 0) {
            return ITEM_TYPE_HEADER;//头布局
        } else if (mFooterCount != 0 && (position >= (mHeaderCount + dataCount))) {
            return ITEM_TYPE_BOTTOM;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }

    //判断是否需要将某个item设置为占据整行！
    //这里将尾布局设置为占据整行！！
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            if (holder.getItemViewType() == ITEM_TYPE_BOTTOM || holder.getItemViewType() == ITEM_TYPE_HEADER) {
                p.setFullSpan(true);//当当前holder的type是footer时  设置为占据整行
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_BOTTOM || viewType == ITEM_TYPE_HEADER) {
            return new HeadFootHolder(mInflater.inflate(R.layout.holder_footer, parent, false));
        } else {
            return new ImgJokerHolder(mInflater.inflate(R.layout.holder_item_imgjoker_picture,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ImgJokerHolder)
        {
            IMGJokerEntity.ShowapiResBodyBean.ContentlistBean bean=mData.get(position-mHeaderCount);
            ((ImgJokerHolder) holder).imgJokerTVTitle.setText(bean.getTitle());
            ImageLoader.load(mContext,bean.getImg(),((ImgJokerHolder) holder).imgJokerIVPic);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View shareView = view.findViewById(R.id.imgJokerIVPic);
                    if (listener!=null)
                    {
                        listener.onItemClick(shareView,holder.getAdapterPosition());
                    }
                }
            });
            resizeItemView(((ImgJokerHolder) holder).imgJokerIVPic,getScaleType(position));
        }
        else if (position==0&&holder instanceof HeadFootHolder)
        {
          ((HeadFootHolder) holder).imgJokerTVPage.setText("获取上一页");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (headerClickListener!=null)
                    {
                        headerClickListener.onHeaderClick();
                    }
                }
            });
        }
        else {
            ((HeadFootHolder) holder).imgJokerTVPage.setText("获取下一页");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (footerClickListener!=null)
                    {
                        footerClickListener.onFooterClick();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + mHeaderCount + mFooterCount;
    }

    private float getScaleType(int position) {
        if (!indexMap.containsKey(position)) {
            float scaleType;

            if (position == 0) {
                scaleType = SIZE_SCALE_01;
            } else if (position == 1) {
                scaleType = SIZE_SCALE_02;
            } else {
                scaleType = AppUtils.getRandomInt() % 2 == 0 ? SIZE_SCALE_01 : SIZE_SCALE_02;
            }

            indexMap.put(position, scaleType);
        }

        return indexMap.get(position);
    }

    private void resizeItemView(ImageView frontCoverImage, float scaleType) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) frontCoverImage.getLayoutParams();
        params.width = screenWidth / 2;
        params.height = (int) (params.width / scaleType) - AppUtils.dip2px(8);
        frontCoverImage.setLayoutParams(params);
    }

    //头布局和尾布局
    public class HeadFootHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgJokerTVPage)
        TextView imgJokerTVPage;
        @BindView(R.id.cv_item)
        CardView cvItem;

        HeadFootHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ImgJokerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgJokerTVTitle)
        TextView imgJokerTVTitle;
        @BindView(R.id.imgJokerIVPic)
        ImageView imgJokerIVPic;

        ImgJokerHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public  void  setListener(OnItemClickListener listener)
    {
        this.listener=listener;
    }

    public interface  onHeaderClickListener
    {
        void onHeaderClick();
    }
    public void setOnHeaderClickListener(onHeaderClickListener onHeaderClickListener)
    {
        this.headerClickListener=onHeaderClickListener;
    }
    public interface  onFooterClickListener
    {
        void onFooterClick();
    }
    public void setOnFooterClickListener(onFooterClickListener onFooterClickListener)
    {
        this.footerClickListener=onFooterClickListener;
    }
}
