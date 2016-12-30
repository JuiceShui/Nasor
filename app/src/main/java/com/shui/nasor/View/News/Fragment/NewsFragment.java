package com.shui.nasor.View.News.Fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shui.nasor.APP.Constants;
import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.News.NewsEntity;
import com.shui.nasor.Presenter.Contract.NewsContract;
import com.shui.nasor.Presenter.NewsPresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.News.Activity.NewsDetailActivity;
import com.shui.nasor.View.News.Adapter.NewsAdapter;
import com.shui.nasor.View.Zhihu.Decoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/29.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View, SwipeRefreshLayout.OnRefreshListener, NewsAdapter.onItemClickListener {
    @BindView(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;
    @BindView(R.id.newsSwipeRefresh)
    SwipeRefreshLayout newsSwipeRefresh;
    private NewsAdapter mAdapter;
    private String defaults="最新";
    private SpacesItemDecoration spacesItemDecoration;
    private String tag;
    private boolean isToBottom=false;
    private List<NewsEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mData=new ArrayList<>();
    public static NewsFragment newInstance(String Tag)
    {
        NewsFragment newsFragment=new NewsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("tag",Tag);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args=getArguments();
        this.tag=args!=null?args.getString("tag"):defaults;
    }
    @Override
    protected View getLoadingTargetView() {
        return newsRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        spacesItemDecoration=new SpacesItemDecoration(3);
        mAdapter=new NewsAdapter(mData,mContext);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        newsRecyclerView.addItemDecoration(spacesItemDecoration);
        newsRecyclerView.setAdapter(mAdapter);
        newsSwipeRefresh.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(this);
        newsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&isToBottom)
                {
                    System.out.println("get more");
                    mPresenter.getMore(tag);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isToBottom=isSlideToBottom(recyclerView);
            }
        });
        mPresenter.getData(tag);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void showData(NewsEntity entity) {
        if (newsSwipeRefresh.isRefreshing())
        {
            newsSwipeRefresh.setRefreshing(false);
        }
        if (entity!=null)
        {
            mData.clear();
            mData.addAll(entity.getShowapi_res_body().getPagebean().getContentlist());
            mAdapter.setData(mData);
            mPresenter.stopChange();
            mPresenter.startChange();
        }
    }

    @Override
    public void showMore(NewsEntity entity) {
        mData.addAll(entity.getShowapi_res_body().getPagebean().getContentlist());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void ChangeTop(int position) {
        mAdapter.changeTopPager(position);
    }
    public  boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onRefresh() {
        mPresenter.getData(tag);
    }

    /**
     * item 的点击事件
     * @param url
     * @param title
     * @param shareView
     */
    @Override
    public void onItemClick(String url, String title, View shareView) {
        mPresenter.stopChange();
        Intent intent=new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,shareView, Constants.SHARE_VIEW);
        startActivity(intent,options.toBundle());
    }
}
