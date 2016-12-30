package com.shui.nasor.View.Zhihu.Fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shui.nasor.APP.Constants;
import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuHotEntity;
import com.shui.nasor.Presenter.Contract.HotContract;
import com.shui.nasor.Presenter.HotPresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.Zhihu.Activity.ZhihuDetailActivity;
import com.shui.nasor.View.Zhihu.Adapter.HotAdapter;
import com.shui.nasor.View.Zhihu.Decoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/11.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View, HotAdapter.onItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.hotRecyclerView)
    RecyclerView hotRecyclerView;
    @BindView(R.id.hotSwipeRefresh)
    SwipeRefreshLayout hotSwipeRefresh;
    private List<ZhihuHotEntity.RecentBean> mData=new ArrayList<>();
    private SpacesItemDecoration spacesItemDecoration;
    private HotAdapter mAdapter;

    @Override
    protected View getLoadingTargetView() {
        return hotRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        spacesItemDecoration=new SpacesItemDecoration(10);
        hotRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        hotRecyclerView.addItemDecoration(spacesItemDecoration);
        mAdapter=new HotAdapter(mData,mContext);
        hotRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        hotSwipeRefresh.setOnRefreshListener(this);
        mPresenter.getData();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    public void showContent(ZhihuHotEntity entity) {
        if (hotSwipeRefresh.isRefreshing())
        {
            hotSwipeRefresh.setRefreshing(false);
        }
        if (entity!=null)
        {
            mData=entity.getRecent();
            mAdapter.setData(mData);
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onItemClick(View view, int position) {
        ZhihuHotEntity.RecentBean bean=mData.get(position);
        Intent intent=new Intent(mContext, ZhihuDetailActivity.class);
        intent.putExtra("id",bean.getNews_id());
        intent.putExtra("title",bean.getTitle());
        mPresenter.insertRead(bean.getNews_id());
        mAdapter.setReadState(position);
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(mActivity,view, Constants.SHARE_VIEW);
        startActivity(intent,options.toBundle());
    }

    @Override
    public void onRefresh() {
        mPresenter.getData();
    }
}
