package com.shui.nasor.View.Relax.Fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.shui.nasor.APP.Constants;
import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.Relax.GirlEntity;
import com.shui.nasor.Presenter.Contract.GirlContract;
import com.shui.nasor.Presenter.GirlPresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.Relax.Activity.PhotoActivity;
import com.shui.nasor.View.Relax.Adapter.GirlAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/16.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class GirlFragment extends BaseFragment<GirlPresenter> implements GirlContract.View, SwipeRefreshLayout.OnRefreshListener, GirlAdapter.OnItemClickListener {
    @BindView(R.id.wechatRecyclerView)
    RecyclerView girlRecyclerView;
    @BindView(R.id.wechatSwipeLayout)
    SwipeRefreshLayout girlSwipeLayout;
    private GirlAdapter mAdapter;
    private boolean isLastItemVisible;
    StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private static final int SPAN_COUNT = 2;//列数
    private List<GirlEntity.ShowapiResBodyBean.NewslistBean> mData=new ArrayList<>();

    @Override
    protected View getLoadingTargetView() {
        return girlRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        mAdapter=new GirlAdapter(mData,mContext);
        girlSwipeLayout.setOnRefreshListener(this);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        girlRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        girlRecyclerView.setAdapter(mAdapter);
        girlRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&isLastItemVisible)
                {
                    mPresenter.getMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                    isLastItemVisible=isToBottom(recyclerView);
            }
        });
        mAdapter.setListener(this);
        mPresenter.getData();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void showData(GirlEntity entity) {
        if (girlSwipeLayout.isRefreshing())
        {
            girlSwipeLayout.setRefreshing(false);
        }
        mData.clear();
        mData.addAll(entity.getShowapi_res_body().getNewslist());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMore(GirlEntity entity) {
        int beforeSize=mData.size();//添加前的数量
        System.out.println("beforeSize："+beforeSize);
        mData.addAll(entity.getShowapi_res_body().getNewslist());
        int afterSize=mData.size();//添加后的数量
        System.out.println("afterSize："+afterSize);
        for (int i=beforeSize;i<afterSize;i++)
        {
            mAdapter.notifyItemChanged(i);
        }
    }


    @Override
    public void showError(String msg) {

    }

    @Override
    public void onRefresh() {
        mPresenter.getData();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(mContext, PhotoActivity.class);
        intent.putExtra("id",mData.get(position).getPicUrl());
        intent.putExtra("url",mData.get(position).getPicUrl());
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,view, Constants.SHARE_VIEW);
        startActivity(intent,options.toBundle());
    }
    private boolean isToBottom(RecyclerView recyclerView)
    {
        if (recyclerView==null) return false;
        if (recyclerView.computeVerticalScrollExtent()+recyclerView.computeVerticalScrollOffset()>=recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }
}
