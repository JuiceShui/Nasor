package com.shui.nasor.View.News.Fragment;

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


public class NewsMainFragment extends BaseNormalFragment {

    @BindView(R.id.vp_tab)
    TabLayout vpTab;
    @BindView(R.id.vpPager)
    ViewPager vpPager;
    private List<Fragment> fragments=new ArrayList<>();
    private FragmentAdapter mAdapter;
    private String[]titles=new String[]{"最新","体育","娱乐","科技","旅游"};
    @Override
    protected void initEventAndData() {
        fragments.add(NewsFragment.newInstance(titles[0]));
        fragments.add(NewsFragment.newInstance(titles[1]));
        fragments.add(NewsFragment.newInstance(titles[2]));
        fragments.add(NewsFragment.newInstance(titles[3]));
        fragments.add(NewsFragment.newInstance(titles[4]));
        mAdapter=new FragmentAdapter(getChildFragmentManager(),fragments);
        vpPager.setAdapter(mAdapter);
        vpTab.addTab(vpTab.newTab().setText(titles[0]));
        vpTab.addTab(vpTab.newTab().setText(titles[1]));
        vpTab.addTab(vpTab.newTab().setText(titles[2]));
        vpTab.addTab(vpTab.newTab().setText(titles[3]));
        vpTab.addTab(vpTab.newTab().setText(titles[4]));
        vpTab.setupWithViewPager(vpPager);
        vpTab.getTabAt(0).setText(titles[0]);
        vpTab.getTabAt(1).setText(titles[1]);
        vpTab.getTabAt(2).setText(titles[2]);
        vpTab.getTabAt(3).setText(titles[3]);
        vpTab.getTabAt(4).setText(titles[4]);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_viewpager;
    }

}
