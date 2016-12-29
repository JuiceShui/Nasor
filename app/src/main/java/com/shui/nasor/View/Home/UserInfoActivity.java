package com.shui.nasor.View.Home;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.Base.BaseActivity;
import com.shui.nasor.Model.Bean.MyData.BombUserEntity;
import com.shui.nasor.Model.Bean.MyData.RealmUserEntity;
import com.shui.nasor.Presenter.Contract.UserInfoContract;
import com.shui.nasor.Presenter.UserInfoPresenter;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.View.Commen.FragmentAdapter;
import com.shui.nasor.View.Home.Fragment.HomeFragment;
import com.shui.nasor.View.Home.Fragment.MyCoinFragment;
import com.shui.nasor.View.Home.Fragment.MyFollowFragment;
import com.shui.nasor.View.Home.Fragment.MyHabbitFragment;
import com.shui.nasor.View.Home.Fragment.MyLikeFragment;
import com.shui.nasor.View.Home.Fragment.MyTieziFragment;
import com.shui.nasor.Widget.CircleImageView;
import com.shui.nasor.Widget.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： max_Shui on 2016/12/20.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements UserInfoContract.View {

    @BindView(R.id.infoIVHeader)
    ImageView infoIVHeader;
    @BindView(R.id.infoTVName)
    TextView infoTVName;
    @BindView(R.id.infoTVSign)
    TextView infoTVSign;
    @BindView(R.id.infoBTNInfo)
    Button infoBTNInfo;
    @BindView(R.id.infoToolbar)
    Toolbar infoToolbar;
    @BindView(R.id.infoCollapsingToolbarLayouts)
    CollapsingToolbarLayout infoCollapsingToolbarLayouts;
    @BindView(R.id.infoAppBarLayout)
    AppBarLayout infoAppBarLayout;
    @BindView(R.id.infoVpTab)
    TabLayout infoVpTab;
    @BindView(R.id.infoVpPager)
    WrapContentHeightViewPager infoVpPager;
    @BindView(R.id.infoNestedScrollView)
    NestedScrollView infoNestedScrollView;
    @BindView(R.id.infoCIVAvatar)
    CircleImageView infoCIVAvatar;
    @BindView(R.id.infoTVToolbar)
    TextView infoTVToolbar;
    @BindView(R.id.infoTVFollower)
    TextView infoTVFollower;
    @BindView(R.id.infoTVFans)
    TextView infoTVFans;

    @Override
    protected void initInject() {
        getActivityComponent().Inject(this);
    }

    private RealmUserEntity realmUserEntity;
    private BombUserEntity bombUserEntity;
    private ToolbarState state = ToolbarState.STATE_EXPANDED;
    private FragmentAdapter mFragmentAdapter;
    private List<Fragment> fragments=new ArrayList<>();
    private String[] mTitle=new String[]{"主页","发帖","收藏","追随","兴趣圈","资助"};
    /**
     * toolbar的状态改变监听   http://www.jianshu.com/p/6418ac4f2605
     */
    private enum ToolbarState {
        STATE_EXPANDED,//展开
        STATE_COLLAPSED,//折叠
        STATE_MIDDLE,//中间
        STATE_COLLAPSED_MIDDLE;//折叠转为中间
    }

    @Override
    protected void initEventData() {
        setToolBar(infoToolbar, "");
        mPresenter.getNetData();
        initFragment();//初始化碎片
        infoAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                toolbarState(appBarLayout, verticalOffset);
            }
        });

    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        fragments.add(new HomeFragment());
        fragments.add(new MyTieziFragment());
        fragments.add(new MyLikeFragment());
        fragments.add(new MyFollowFragment());
        fragments.add(new MyHabbitFragment());
        fragments.add(new MyCoinFragment());
        mFragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),fragments);
        infoVpPager.setAdapter(mFragmentAdapter);
        infoVpTab.addTab(infoVpTab.newTab().setText(mTitle[0]));
        infoVpTab.addTab(infoVpTab.newTab().setText(mTitle[1]));
        infoVpTab.addTab(infoVpTab.newTab().setText(mTitle[2]));
        infoVpTab.addTab(infoVpTab.newTab().setText(mTitle[3]));
        infoVpTab.addTab(infoVpTab.newTab().setText(mTitle[4]));
        infoVpTab.addTab(infoVpTab.newTab().setText(mTitle[5]));
        infoVpTab.setupWithViewPager(infoVpPager);
        infoVpTab.getTabAt(0).setText(mTitle[0]);
        infoVpTab.getTabAt(1).setText(mTitle[1]);
        infoVpTab.getTabAt(2).setText(mTitle[2]);
        infoVpTab.getTabAt(3).setText(mTitle[3]);
        infoVpTab.getTabAt(4).setText(mTitle[4]);
        infoVpTab.getTabAt(5).setText(mTitle[5]);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

    @OnClick(R.id.infoBTNInfo)
    public void onInfo() {
        Intent intent = new Intent(UserInfoActivity.this, UserModifyActivity.class);
        startActivity(intent);
    }

    @Override
    public void showNetData(BombUserEntity entity) {
        this.bombUserEntity = entity;
        ImageLoader.load(this, entity.getAvatar(), infoCIVAvatar);
        infoTVName.setText(entity.getName());
        infoTVSign.setText(entity.getSign());
        infoTVFans.setText(entity.getFans()+" 粉丝");
        infoTVFollower.setText(entity.getFollower()+" 关注");
    }

    @Override
    public void showLocalData(RealmUserEntity entity) {
        this.realmUserEntity = entity;
    }

    /**
     * 改变 toolbar
     *
     * @param appBarLayout
     * @param verticalOffset
     */
    private void toolbarState(AppBarLayout appBarLayout, int verticalOffset) {

        if (verticalOffset == 0)//展开状态
        {
            if (state != ToolbarState.STATE_EXPANDED) {
                state = ToolbarState.STATE_EXPANDED;//标记为展开
                infoTVToolbar.setVisibility(View.INVISIBLE);
                infoTVToolbar.setText("");
            }
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (state != ToolbarState.STATE_COLLAPSED)//标记为折叠
            {
                state = ToolbarState.STATE_COLLAPSED;
                infoTVToolbar.setVisibility(View.VISIBLE);
                if (realmUserEntity != null) {
                    infoTVToolbar.setText(realmUserEntity.getName());
                }
                if (bombUserEntity != null) {
                    infoTVToolbar.setText(bombUserEntity.getName());
                }
            }
        } else {
            if (state != ToolbarState.STATE_MIDDLE) {
                if (state == ToolbarState.STATE_COLLAPSED)//如果当前是折叠
                {
                    state = ToolbarState.STATE_COLLAPSED_MIDDLE;//状态标记为折叠转中间
                }
                state = ToolbarState.STATE_MIDDLE;//修改标记为中间
                infoTVToolbar.setVisibility(View.INVISIBLE);
            }
        }
    }
}
