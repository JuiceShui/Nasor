package com.shui.nasor.View.WeChat.Fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shui.nasor.APP.Constants;
import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.WeChat.WXEntity;
import com.shui.nasor.Presenter.Contract.WeChatContract;
import com.shui.nasor.Presenter.WechatPresenter;
import com.shui.nasor.R;
import com.shui.nasor.View.WeChat.Activity.WeChatActivity;
import com.shui.nasor.View.WeChat.Adapter.WeChatAdapter;
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


public class WeChatFragment extends BaseFragment<WechatPresenter> implements WeChatContract.View, SwipeRefreshLayout.OnRefreshListener, WeChatAdapter.onItemClickListener {


    @BindView(R.id.wechatRecyclerView)
    RecyclerView wechatRecyclerView;
    @BindView(R.id.wechatSwipeLayout)
    SwipeRefreshLayout wechatSwipeLayout;
    private WeChatAdapter mAdapter;
    private SpacesItemDecoration spacesItemDecoration;
    private List<WXEntity.NewslistBean> mData=new ArrayList<>();
    private boolean isLastItem=false;//是否下滑到了底部

    @Override
    protected View getLoadingTargetView() {
        return wechatRecyclerView;
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        spacesItemDecoration=new SpacesItemDecoration(5);
        wechatRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        wechatRecyclerView.addItemDecoration(spacesItemDecoration);
        wechatSwipeLayout.setOnRefreshListener(this);
        mAdapter=new WeChatAdapter(mContext,mData);
        wechatRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        wechatRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&isLastItem)
                {
                    //加载更多
                   mPresenter.getMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isLastItem= isSlideToBottom(recyclerView);
            }
        });
        mPresenter.getData(true);
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void showContent(WXEntity entity) {
        if (wechatSwipeLayout.isRefreshing())
        {
            wechatSwipeLayout.setRefreshing(false);
        }
        mData.clear();
        mData.addAll(entity.getNewslist());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMore(WXEntity entity) {
        mData.addAll(entity.getNewslist());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSearch(WXEntity entity) {
        mData.clear();
        mData.addAll(entity.getNewslist());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onRefresh() {
        mPresenter.getData(false);
    }
    /**
     * 检验是否已经显示到了最后一个item
     * @param recyclerView
     * @return 返回是或否
     */
    public  boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onItemClick(View shareView, int position) {
        WXEntity.NewslistBean bean=mData.get(position);
        Intent intent=new Intent(mContext, WeChatActivity.class);
        intent.putExtra("id",bean.getPicUrl());
        intent.putExtra("title",bean.getTitle());
        intent.putExtra("link",bean.getUrl());
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(mActivity,shareView, Constants.SHARE_VIEW);
        startActivity(intent,options.toBundle());
    }

    /**
     * 搜索
     * @param word
     */
    public void doSearch(String word)
    {
        mPresenter.getSearch(word);
    }
}
