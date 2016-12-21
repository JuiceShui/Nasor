package com.shui.nasor.View.Zhihu.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shui.nasor.Base.BaseNormalFragment;
import com.shui.nasor.R;
import com.shui.nasor.View.Commen.FragmentAdapter;

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


public class ZhiHuMainFragment extends BaseNormalFragment {

    @BindView(R.id.vp_tab)
    TabLayout NewsTab;
    @BindView(R.id.vpPager)
    ViewPager NewsViewpager;
    private FragmentAdapter mAdapter;
    private List<Fragment> fragments=new ArrayList<Fragment>();
    private String [] mTitle=new String[]{"日报","主题","专栏","热门"};

    @Override
    protected void initEventAndData() {
        fragments.add(new DailyFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new SectionFragment());
        fragments.add(new HotFragment());
        mAdapter=new FragmentAdapter(getChildFragmentManager(),fragments);
        NewsViewpager.setAdapter(mAdapter);
        NewsTab.addTab(NewsTab.newTab().setText(mTitle[0]));
        NewsTab.addTab(NewsTab.newTab().setText(mTitle[1]));
        NewsTab.addTab(NewsTab.newTab().setText(mTitle[2]));
        NewsTab.addTab(NewsTab.newTab().setText(mTitle[3]));
        NewsTab.setupWithViewPager(NewsViewpager);
        NewsTab.getTabAt(0).setText(mTitle[0]);
        NewsTab.getTabAt(1).setText(mTitle[1]);
        NewsTab.getTabAt(2).setText(mTitle[2]);
        NewsTab.getTabAt(3).setText(mTitle[3]);
    }

    @Override
    protected int getLayout() {
        {
            return R.layout.fragment_viewpager;
        }


    }
}
