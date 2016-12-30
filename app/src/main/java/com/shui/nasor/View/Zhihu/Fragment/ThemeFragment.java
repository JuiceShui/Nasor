package com.shui.nasor.View.Zhihu.Fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuThemeListEntity;
import com.shui.nasor.Presenter.Contract.ThemeContract;
import com.shui.nasor.Presenter.ThemePresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.Zhihu.Activity.ThemeDetailActivity;
import com.shui.nasor.View.Zhihu.Adapter.ThemeAdapter;
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


public class ThemeFragment extends BaseFragment<ThemePresenter> implements ThemeContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.themeRecyclerView)
    RecyclerView themeRecyclerView;
    @BindView(R.id.themeSwipeRefresh)
    SwipeRefreshLayout themeSwipeRefresh;
    private List<ZhihuThemeListEntity.OthersBean> mData=new ArrayList<>();
    private ThemeAdapter mAdapter;
    SpacesItemDecoration spacesItemDecoration;

    @Override
    protected View getLoadingTargetView() {
        return themeRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        spacesItemDecoration=new SpacesItemDecoration(8);
        themeRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mAdapter=new ThemeAdapter(mData,mContext);
        themeRecyclerView.addItemDecoration(spacesItemDecoration);
        themeRecyclerView.setAdapter(mAdapter);
        mPresenter.getData();
        themeSwipeRefresh.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(new ThemeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int id, String name) {
                Intent intent=new Intent();
                intent.setClass(mContext, ThemeDetailActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_theme;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(ZhihuThemeListEntity entity) {
        if (themeSwipeRefresh.isRefreshing())
        {
            themeSwipeRefresh.setRefreshing(false);
        }
        if (entity!=null)
        {
            mData=entity.getOthers();
            mAdapter.setData(mData);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getData();
    }
}
