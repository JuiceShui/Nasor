package com.shui.nasor.View.Zhihu.Fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuDailyEntity;
import com.shui.nasor.Presenter.Contract.DailyContract;
import com.shui.nasor.Presenter.DailyPresenter;
import com.shui.nasor.R;
import com.shui.nasor.Utils.CircularAnim;
import com.shui.nasor.View.Home.WeatherActivity;
import com.shui.nasor.View.Zhihu.Activity.ZhihuDetailActivity;
import com.shui.nasor.View.Zhihu.Adapter.DailyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * 日报fragment
 */


public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.dailyRecyclerView)
    RecyclerView dailyRecyclerView;
    @BindView(R.id.dailyFloatAcBar)
    FloatingActionButton dailyFloatAcBar;
    @BindView(R.id.dailyRefreshLayout)
    SwipeRefreshLayout dailyRefreshLayout;
    private DailyAdapter mAdapter;
    private List<ZhihuDailyEntity.StoriesBean> mData=new ArrayList<>();
    private List<ZhihuDailyEntity.TopStoriesBean> mTopData=new ArrayList<>();

    @Override
    protected View getLoadingTargetView() {
        return dailyRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }
    @Override
    protected void initEventAndData() {
        mPresenter.getData(true);
        mAdapter=new DailyAdapter(mContext,mTopData,mData);
        dailyRecyclerView.setItemAnimator(new DefaultItemAnimator());
        dailyRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dailyRecyclerView.setAdapter(mAdapter);
        dailyRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(new DailyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mPresenter.insertReadToDB(mData.get(position).getId());
                mAdapter.setReadState(position,true);
                mAdapter.notifyItemChanged(position);
                Intent intent=new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id",mData.get(position).getId());
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(mActivity,view,"shareView");
                startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_daily;
    }

    @Override
    public void ShowContent(ZhihuDailyEntity entity) {
        if(dailyRefreshLayout.isRefreshing()) {
            dailyRefreshLayout.setRefreshing(false);
        } else {
           // ivProgress.stop();
        }
        if (entity!=null)
        {
            mTopData=entity.getTop_stories();
            mData=entity.getStories();
            mAdapter.setData(entity);
            mPresenter.stopChange();
            mPresenter.startChange();
        }
    }

    @Override
    public void TopChange(int count) {
        mAdapter.changeTopPager(count);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onRefresh() {
        mPresenter.getData(false);
    }
    @OnClick(R.id.dailyFloatAcBar)
    public void goToWeather()
    {
        CircularAnim.fullActivity((Activity) mContext, dailyFloatAcBar)
                .colorOrImageRes(R.color.colorPrimary)
                .go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        startActivity(new Intent(mContext, WeatherActivity.class));
                    }
                });
    }
}
