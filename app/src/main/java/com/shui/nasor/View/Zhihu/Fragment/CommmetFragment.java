package com.shui.nasor.View.Zhihu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuCommentEntity;
import com.shui.nasor.Presenter.CommentPresenter;
import com.shui.nasor.Presenter.Contract.CommentContract;
import com.shui.nasor.R;
import com.shui.nasor.View.Zhihu.Adapter.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/12/14.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class CommmetFragment extends BaseFragment<CommentPresenter> implements CommentContract.View {
    @BindView(R.id.commentRecyclerView)
    RecyclerView commentRecyclerView;
    private int id;
    private int kind;
    private List<ZhihuCommentEntity.CommentsBean> mData;
    private CommentAdapter mAdapter;
    public static CommmetFragment newInstance(int id, int kind) {
        CommmetFragment fragmentComment = new CommmetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("kind", kind);
        fragmentComment.setArguments(bundle);
        return fragmentComment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.id = bundle.getInt("id");
        this.kind = bundle.getInt("kind");
    }

    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        mData=new ArrayList<>();
        mAdapter=new CommentAdapter(mData,mContext);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        commentRecyclerView.setAdapter(mAdapter);
        System.out.println("YEADDDDDDDDDDDDDDDDD");
        mPresenter.getData(id,kind);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    public void showData(ZhihuCommentEntity entity) {
        if (entity!=null)
        {
            mData=entity.getComments();
            mAdapter.setData(mData);
            System.out.println("come here");
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
