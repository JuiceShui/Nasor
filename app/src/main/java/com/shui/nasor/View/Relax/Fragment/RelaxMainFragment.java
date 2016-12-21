package com.shui.nasor.View.Relax.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shui.nasor.Base.BaseNormalFragment;
import com.shui.nasor.R;
import com.shui.nasor.View.Relax.Adapter.RelaxFragmentAdapter;

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


public class RelaxMainFragment extends BaseNormalFragment {


    @BindView(R.id.vp_tab)
    TabLayout vpTab;
    @BindView(R.id.vpPager)
    ViewPager vpPager;
    private RelaxFragmentAdapter mAdapter;
    private List<Fragment> fragments=new ArrayList<>();
    private String[] title=new String[]{"笑话","趣图","妹纸"};
    @Override
    protected void initEventAndData() {
        fragments.add(new TxTJokerFragment());
        fragments.add(new IMGJokerFragment());
        fragments.add(new GirlFragment());
        mAdapter=new RelaxFragmentAdapter(getChildFragmentManager(),fragments);
        vpPager.setAdapter(mAdapter);
        vpTab.addTab(vpTab.newTab().setText(title[0]));
        vpTab.addTab(vpTab.newTab().setText(title[1]));
        vpTab.addTab(vpTab.newTab().setText(title[2]));
        vpTab.setupWithViewPager(vpPager);
        vpTab.getTabAt(0).setText(title[0]);
        vpTab.getTabAt(1).setText(title[1]);
        vpTab.getTabAt(2).setText(title[2]);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_viewpager;
    }

}
