package com.shui.nasor.View.Zhihu.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.R;
import com.shui.nasor.View.Zhihu.Adapter.CommentFragmentAdapter;
import com.shui.nasor.View.Zhihu.Fragment.CommentFragment;

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
//评论activity

public class CommentActivity extends BaseNormalActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.commentTab)
    TabLayout commentTab;
    @BindView(R.id.commentPager)
    ViewPager commentPager;
    private Intent intent;
    private int id;
    private int longComment;
    private int allComment;
    private int shortComment;
    private CommentFragmentAdapter mAdapter;
    private List<CommentFragment> fragments = new ArrayList<>();

    @Override
    protected void initEventAndData() {
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        longComment = intent.getIntExtra("longComment", 0);
        shortComment = intent.getIntExtra("shortComment", 0);
        allComment = intent.getIntExtra("allComment", 0);
        setToolbar(toolbar, String.format("%d个评论", allComment));
        //-----------长评论fragment
        CommentFragment LongCommentFragment = new CommentFragment();
        Bundle bundleLong = new Bundle();
        bundleLong.putInt("id", id);
        bundleLong.putInt("kind", 0);
        LongCommentFragment.setArguments(bundleLong);
        //-------------短评论fragment
        CommentFragment ShortCommentFragment = new CommentFragment();
        Bundle bundleShort = new Bundle();
        bundleShort.putInt("id", id);
        bundleShort.putInt("kind", 1);
        ShortCommentFragment.setArguments(bundleShort);

        //
        // CommmetFragment longFragment=CommmetFragment.newInstance(id,0);
        // CommmetFragment shortFragment=CommmetFragment.newInstance(id,1);
        fragments.add(ShortCommentFragment);
        fragments.add(LongCommentFragment);
        //
        mAdapter = new CommentFragmentAdapter(getSupportFragmentManager(), fragments);
        commentPager.setAdapter(mAdapter);
        commentTab.addTab(commentTab.newTab().setText(String.format("%d个短评论", shortComment)));
        commentTab.addTab(commentTab.newTab().setText(String.format("%d长短评论", longComment)));
        commentTab.setupWithViewPager(commentPager);
        commentTab.getTabAt(0).setText(String.format("%d个短评论", shortComment));
        commentTab.getTabAt(1).setText(String.format("%d个长评论", longComment));

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
