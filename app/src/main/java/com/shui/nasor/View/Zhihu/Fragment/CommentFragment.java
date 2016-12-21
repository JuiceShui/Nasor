package com.shui.nasor.View.Zhihu.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuCommentEntity;
import com.shui.nasor.Presenter.CommentPresenter;
import com.shui.nasor.Presenter.Contract.CommentContract;
import com.shui.nasor.R;
import com.shui.nasor.View.Zhihu.Adapter.CommentAdapter;

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


public class CommentFragment extends BaseFragment<CommentPresenter> implements CommentContract.View {
    @BindView(R.id.commentRecyclerView)
    RecyclerView rvCommentList;

    CommentAdapter mAdapter;
    List<ZhihuCommentEntity.CommentsBean> mList;

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initEventAndData() {
        Bundle bundle = getArguments();
        mPresenter.getData(bundle.getInt("id"),bundle.getInt("kind"));
       // ivProgress.start();
        rvCommentList.setVisibility(View.INVISIBLE);
        mList = new ArrayList<>();
        mAdapter = new CommentAdapter(mList,mContext);
        rvCommentList.setLayoutManager(new LinearLayoutManager(mContext));
        rvCommentList.setAdapter(mAdapter);
    }

    @Override
    public void showData(ZhihuCommentEntity commentBean) {
       // ivProgress.stop();
        rvCommentList.setVisibility(View.VISIBLE);
        mList.clear();
        mList.addAll(commentBean.getComments());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {

    }
}
