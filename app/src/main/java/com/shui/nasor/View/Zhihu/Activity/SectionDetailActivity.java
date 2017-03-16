package com.shui.nasor.View.Zhihu.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.shui.nasor.APP.Constants;
import com.shui.nasor.Base.BaseActivity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuSectionDetailEntity;
import com.shui.nasor.Presenter.Contract.SectionDetailContract;
import com.shui.nasor.Presenter.SectionDetailPresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.Zhihu.Adapter.SectionDetailAdapter;
import com.shui.nasor.View.Zhihu.Decoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/14.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class SectionDetailActivity extends BaseActivity<SectionDetailPresenter> implements SectionDetailContract.View, SwipeRefreshLayout.OnRefreshListener, SectionDetailAdapter.onItemClickListener {
    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.sectionDetailRecyclerView)
    RecyclerView sectionDetailRecyclerView;
    @BindView(R.id.sectionDetailSwipeRefresh)
    SwipeRefreshLayout sectionDetailSwipeRefresh;
    private SpacesItemDecoration spacesItemDecoration;
    private SectionDetailAdapter mAdapter;
    private List<ZhihuSectionDetailEntity.StoriesBean> mData=new ArrayList<>();
    private Intent intent;
    private int id;
    private String title;
    @Override
    protected void initInject() {
        getActivityComponent().Inject(this);
    }

    @Override
    protected void initEventData() {
        intent=getIntent();
        id=intent.getIntExtra("id",0);
        title=intent.getStringExtra("title");
        setToolBar(normalToolbar,title);
        mAdapter=new SectionDetailAdapter(mData,mContext);
        spacesItemDecoration=new SpacesItemDecoration(8);
        sectionDetailRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        sectionDetailRecyclerView.addItemDecoration(spacesItemDecoration);
        sectionDetailRecyclerView.setAdapter(mAdapter);
        sectionDetailSwipeRefresh.setOnRefreshListener(this);
        mAdapter.setItemClickListener(this);
        mPresenter.getData(id,true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_section;
    }

    @Override
    public void showData(ZhihuSectionDetailEntity entity) {
        if (sectionDetailSwipeRefresh.isRefreshing())
        {
            sectionDetailSwipeRefresh.setRefreshing(false);
        }
        if (entity!=null)
        {
            mData=entity.getStories();
            mAdapter.setData(mData);
        }
    }
    @Override
    public void onRefresh() {
        mPresenter.getData(id,false);
    }
    //item的点击
    @Override
    public void onItemClick(int position, String title, ImageView shareView) {
        mPresenter.insertRead(mData.get(position).getId());
        mAdapter.setReadState(position,true);
        Intent intent=new Intent(mContext,ZhihuDetailActivity.class);
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(mContext,shareView, Constants.SHARE_VIEW);
        intent.putExtra("id",mData.get(position).getId());
        intent.putExtra("title",title);
        startActivity(intent,options.toBundle());
    }
}
