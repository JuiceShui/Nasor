package com.shui.nasor.View.Zhihu.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.APP.Constants;
import com.shui.nasor.Base.BaseActivity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuThemeDetailEntity;
import com.shui.nasor.Presenter.Contract.ThemeDetailContract;
import com.shui.nasor.Presenter.ThemeDetailPresenter;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.View.Zhihu.Adapter.ThemeDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class ThemeDetailActivity extends BaseActivity<ThemeDetailPresenter> implements ThemeDetailContract.View, SwipeRefreshLayout.OnRefreshListener, ThemeDetailAdapter.onItemClickListener {

    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.themeIVHeader)
    ImageView themeIVHeader;
    @BindView(R.id.themeTVDescription)
    TextView themeTVDescription;
    @BindView(R.id.themeAppBarLayout)
    AppBarLayout themeAppBarLayout;
    @BindView(R.id.themeRVContent)
    RecyclerView themeRVContent;
    @BindView(R.id.themeSRLRefresh)
    SwipeRefreshLayout themeSRLRefresh;
    private Intent intent;
    private int id;
    private String name;
    private ThemeDetailAdapter mAdapter;
    private List<ZhihuThemeDetailEntity.StoriesBean> mData=new ArrayList<>();
    @Override
    protected void initInject() {
        getActivityComponent().Inject(this);
    }

    @Override
    protected void initEventData() {
        intent=getIntent();
        id=intent.getIntExtra("id",0);
        name=intent.getStringExtra("name");
        setToolBar(normalToolbar,name);
        mAdapter=new ThemeDetailAdapter(mData,mContext);
        themeRVContent.setLayoutManager(new LinearLayoutManager(mContext));
        themeRVContent.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        themeSRLRefresh.setOnRefreshListener(this);
        themeAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {//根据appbar是否展示完全控制recyclerview能否拉动
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset>=0)
                {
                    themeSRLRefresh.setEnabled(true);
                }
                else {
                    themeSRLRefresh.setEnabled(false);
                }
            }
        });
        mPresenter.getData(id,true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhihu_theme;
    }

    @Override
    public void showContent(ZhihuThemeDetailEntity entity) {
        if (themeSRLRefresh.isRefreshing())
        {
            themeSRLRefresh.setRefreshing(false);
        }
        if (entity!=null)
        {
            mData=entity.getStories();
            mAdapter.setData(mData);
            ImageLoader.load(mContext,entity.getImage(),themeIVHeader);
            themeTVDescription.setText(entity.getDescription());

        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getData(id,false);
    }

    @Override
    public void onItemClick(int position, View shareView) {
        ZhihuThemeDetailEntity.StoriesBean bean=mData.get(position);
        Intent intent=new Intent(mContext,ZhihuDetailActivity.class);
        intent.putExtra("id",bean.getId());
        intent.putExtra("title",bean.getTitle());
        mAdapter.setReadState(position);
        mPresenter.insertRead(bean.getId());
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(mContext,shareView, Constants.SHARE_VIEW);
        startActivity(intent,options.toBundle());
    }
}
