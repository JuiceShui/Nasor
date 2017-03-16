package com.shui.nasor.View.Relax.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.Relax.TXTJokerEntity;
import com.shui.nasor.Presenter.Contract.TXTJokerContract;
import com.shui.nasor.Presenter.TXTJokerPresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.Relax.Adapter.TXTJokerAdapter;
import com.shui.nasor.View.Zhihu.Decoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/15.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class TxTJokerFragment extends BaseFragment<TXTJokerPresenter> implements TXTJokerContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.wechatRecyclerView)
    RecyclerView txtJokerRecyclerView;
    @BindView(R.id.wechatSwipeLayout)
    SwipeRefreshLayout txtJokerSwipeLayout;
    private SpacesItemDecoration spacesItemDecoration;
    private TXTJokerAdapter mAdapter;
    private boolean isLastItemVisible=false;
    private List<TXTJokerEntity.ShowapiResBodyBean.ContentlistBean> mData=new ArrayList<>();

    @Override
    protected View getLoadingTargetView() {
        return txtJokerRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        mAdapter=new TXTJokerAdapter(mContext,mData);
        spacesItemDecoration=new SpacesItemDecoration(3);
        txtJokerRecyclerView.addItemDecoration(spacesItemDecoration);
        txtJokerRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        txtJokerRecyclerView.setAdapter(mAdapter);
        txtJokerSwipeLayout.setOnRefreshListener(this);
        txtJokerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                isLastItemVisible=isBottom(recyclerView);
            }
        });
        mPresenter.getData(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void showData(TXTJokerEntity entity) {
        if (txtJokerSwipeLayout.isRefreshing())
        {
            txtJokerSwipeLayout.setRefreshing(false);
        }
         if (entity!=null)
           {
        mData.clear();
        mData.addAll(entity.getShowapi_res_body().getContentlist());
        mAdapter.notifyDataSetChanged();
            }
    }

    @Override
    public void showMore(TXTJokerEntity entity) {

        if (entity!=null)
        {
            mData.addAll(entity.getShowapi_res_body().getContentlist());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onRefresh() {
        mPresenter.getData(false);
    }
    private boolean isBottom(RecyclerView view)
    {
        if (view==null) return false ;
        if (view.computeVerticalScrollOffset()+view.computeVerticalScrollExtent()>=view.computeVerticalScrollRange())
            return  true;
        return false;
    }
}
