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
import com.shui.nasor.Model.Bean.Relax.IMGJokerEntity;
import com.shui.nasor.Presenter.Contract.IMGJokerContract;
import com.shui.nasor.Presenter.IMGJokerPresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.Relax.Activity.ImgJokerActivity;
import com.shui.nasor.View.Relax.Adapter.IMGJokerAdapter;

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


public class IMGJokerFragment extends BaseFragment<IMGJokerPresenter> implements IMGJokerContract.View, SwipeRefreshLayout.OnRefreshListener, IMGJokerAdapter.OnItemClickListener {
    @BindView(R.id.wechatRecyclerView)
    RecyclerView imgJokerRecyclerView;
    @BindView(R.id.wechatSwipeLayout)
    SwipeRefreshLayout imgJokerSwipeLayout;
    private IMGJokerAdapter mAdapter;
    private List<IMGJokerEntity.ShowapiResBodyBean.ContentlistBean> mData=new ArrayList<>();

    @Override
    protected View getLoadingTargetView() {
        return imgJokerRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        mAdapter=new IMGJokerAdapter(mData,mContext);
        imgJokerSwipeLayout.setOnRefreshListener(this);
        imgJokerRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        imgJokerRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(this);
        mAdapter.setOnFooterClickListener(new IMGJokerAdapter.onFooterClickListener() {
            @Override
            public void onFooterClick() {
                mPresenter.getNextData();
            }
        });
        mAdapter.setOnHeaderClickListener(new IMGJokerAdapter.onHeaderClickListener() {
            @Override
            public void onHeaderClick() {
                mPresenter.getPreData();
            }
        });
        mPresenter.getData(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void showData(IMGJokerEntity entity) {
        if (imgJokerSwipeLayout.isRefreshing())
        {
            imgJokerSwipeLayout.setRefreshing(false);
        }
        mData.clear();
        mData.addAll(entity.getShowapi_res_body().getContentlist());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNextPage(IMGJokerEntity entity) {
        mData.clear();
        mData.addAll(entity.getShowapi_res_body().getContentlist());
        mAdapter.notifyDataSetChanged();
        imgJokerRecyclerView.scrollToPosition(0);
    }

    @Override
    public void showPrePage(IMGJokerEntity entity) {
        mData.clear();
        mData.addAll(entity.getShowapi_res_body().getContentlist());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoPrePage() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onRefresh() {
        mPresenter.getData(false);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(mContext, ImgJokerActivity.class);
        intent.putExtra("id",mData.get(position-1).getId());
        intent.putExtra("url",mData.get(position-1).getImg());
        intent.putExtra("title",mData.get(position-1).getTitle());
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,view, Constants.SHARE_VIEW);
        mContext.startActivity(intent,options.toBundle());
    }
}
