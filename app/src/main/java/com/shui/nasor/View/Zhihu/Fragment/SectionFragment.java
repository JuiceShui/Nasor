package com.shui.nasor.View.Zhihu.Fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuSectionListEntity;
import com.shui.nasor.Presenter.Contract.SectionContract;
import com.shui.nasor.Presenter.SectionPresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.Zhihu.Activity.SectionDetailActivity;
import com.shui.nasor.View.Zhihu.Adapter.SectionAdapter;

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


public class SectionFragment extends BaseFragment<SectionPresenter> implements SectionContract.View, SectionAdapter.onItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.sectionRecyclerView)
    RecyclerView sectionRecyclerView;
    @BindView(R.id.sectionSwipeRefresh)
    SwipeRefreshLayout sectionSwipeRefresh;
    private SectionAdapter mAdapter;
    private List<ZhihuSectionListEntity.DataBean> mData=new ArrayList<>();

    @Override
    protected View getLoadingTargetView() {
        return sectionRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        sectionRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mAdapter=new SectionAdapter(mData,mContext);
        sectionRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        sectionSwipeRefresh.setOnRefreshListener(this);
        mPresenter.getData(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_section;
    }

    @Override
    public void showContent(ZhihuSectionListEntity entity) {
        if (sectionSwipeRefresh.isRefreshing())
        {
            sectionSwipeRefresh.setRefreshing(false);
        }
        if (entity!=null)
        {
            mData=entity.getData();
            mAdapter.setData(mData);
        }
    }

    @Override
    public void showError(String msg) {

    }
    //item的点击事件
    @Override
    public void onItemClick(int id, String title) {
        Intent intent=new Intent(mContext, SectionDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("title",title);
        mContext.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mPresenter.getData(false);
    }
}
