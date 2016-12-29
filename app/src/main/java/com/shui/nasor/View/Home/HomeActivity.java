package com.shui.nasor.View.Home;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.shui.nasor.APP.App;
import com.shui.nasor.APP.Constants;
import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.Model.Bean.MyData.BombUserEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.SharedPreferenceUtils;
import com.shui.nasor.View.About.Fragment.AboutFragment;
import com.shui.nasor.View.Like.Fragment.LikeFragment;
import com.shui.nasor.View.News.Fragment.NewsMainFragment;
import com.shui.nasor.View.Relax.Fragment.RelaxMainFragment;
import com.shui.nasor.View.Setting.Fragment.SettingMainFragment;
import com.shui.nasor.View.WeChat.Fragment.WeChatFragment;
import com.shui.nasor.View.Zhihu.Fragment.ZhiHuMainFragment;
import com.shui.nasor.Widget.CircleImageView;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import me.yokeyword.fragmentation.SupportFragment;

public class HomeActivity extends BaseNormalActivity {

    @BindView(R.id.home_title)
    TextView homeTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_search)
    MaterialSearchView homeSearch;
    @BindView(R.id.fl_fragment_container)
    FrameLayout flFragmentContainer;
    @BindView(R.id.content_home)
    RelativeLayout contentHome;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ZhiHuMainFragment mZhihuFragment;
    private NewsMainFragment mNewsFragment;
    private WeChatFragment mWechatFragmet;
    private RelaxMainFragment mRelaxFragment;
    private LikeFragment mLikeFragment;
    private SettingMainFragment mSettingFragment;
    private AboutFragment mAboutFragment;
    private int showFragment= Constants.TYPE_ZHIHU;
    private int hideFragment=Constants.TYPE_ZHIHU;
    private MenuItem mLastMenuItem;
    private MenuItem mSearchMenuItem;
    private String userInfo;
    private TextView tv_name,tv_email;
    private CircleImageView iv_avatar;
    private View header_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null)//第一次启动
        {

        }
        else
        {
            showFragment= SharedPreferenceUtils.getCurrentItem();
            hideFragment=Constants.TYPE_ZHIHU;
            showHideFragment(getCurrentFragment(showFragment),getCurrentFragment(hideFragment));
            navView.getMenu().findItem(R.id.nav_zhihu).setChecked(false);
            toolbar.setTitle(navView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
            hideFragment=showFragment;
        }
    }

    @Override
    protected void initEventAndData() {
        //设置 navigtionView的headvView
        header_view=navView.inflateHeaderView(R.layout.nav_header_home);
        iv_avatar= (CircleImageView) header_view.findViewById(R.id.nav_image_header);
        tv_name= (TextView) header_view.findViewById(R.id.nav_tv_name);
        tv_email= (TextView) header_view.findViewById(R.id.nav_tv_email);
        userInfo=SharedPreferenceUtils.getUser();
        if (userInfo=="")//当没有登录的时候
        {
            System.out.println("user null");
            iv_avatar.setImageResource(R.drawable.header);
            tv_name.setVisibility(View.INVISIBLE);
            tv_email.setVisibility(View.VISIBLE);
            tv_email.setText(App.getInstance().getString(R.string.click_to_logo));
            iv_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CircleImageView imageView= (CircleImageView) findViewById(R.id.nav_image_header);
                    Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,imageView,Constants.SHARE_VIEW);
                    //startActivity(intent,options.toBundle());
                    startActivityForResult(intent,Constants.ACTIVITY_REQUEST,options.toBundle());
                }
            });
        }
        else //当已经登陆的时候
        {
            System.out.println("user have");
            iv_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("have on click");
                    CircleImageView imageView= (CircleImageView) findViewById(R.id.nav_image_header);
                    Intent intent=new Intent(HomeActivity.this,UserInfoActivity.class);
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,imageView,Constants.SHARE_VIEW);
                    startActivity(intent,options.toBundle());
                }
            });
            BmobQuery<BombUserEntity> bmobQuery = new BmobQuery<>();
            bmobQuery.getObject(userInfo, new QueryListener<BombUserEntity>() {
                @Override
                public void done(BombUserEntity userInfo, BmobException e) {
                    if (e==null)
                    {
                        System.out.println("enter there");
                        tv_email.setVisibility(View.VISIBLE);
                        tv_name.setVisibility(View.VISIBLE);
                        tv_name.setText(userInfo.getName());
                        tv_email.setText(userInfo.getEmail());
                    }
                }
            });
        }
        setToolbar(toolbar,"知乎日报");
        mZhihuFragment=new ZhiHuMainFragment();
        mNewsFragment=new NewsMainFragment();
        mWechatFragmet=new WeChatFragment();
        mRelaxFragment=new RelaxMainFragment();
        mLikeFragment=new LikeFragment();
        mSettingFragment=new SettingMainFragment();
        mAboutFragment=new AboutFragment();
        mDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        mLastMenuItem=navView.getMenu().findItem(R.id.nav_zhihu);
        loadMultipleRootFragment(R.id.fl_fragment_container,0,mZhihuFragment,mNewsFragment,mWechatFragmet,mRelaxFragment,
                mLikeFragment,mSettingFragment,mAboutFragment);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_zhihu:
                        showFragment=Constants.TYPE_ZHIHU;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.nav_news:
                        showFragment=Constants.TYPE_NEWS;
                        mSearchMenuItem.setVisible(true);
                        break;
                    case R.id.nav_wechat:
                        showFragment=Constants.TYPE_WECHAT;
                        mSearchMenuItem.setVisible(true);
                        break;
                    case R.id.nav_relax:
                        showFragment=Constants.TYPE_RELAX;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.nav_like:
                        showFragment=Constants.TYPE_LIKE;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.nav_setting:
                        showFragment=Constants.TYPE_SETTING;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.nav_about:
                        showFragment=Constants.TYPE_ABOUT;
                        mSearchMenuItem.setVisible(false);
                        break;

                }
                if (mLastMenuItem!=null)
                {
                    mLastMenuItem.setChecked(false);
                }
                mLastMenuItem=item;
                SharedPreferenceUtils.setCurrentItem(showFragment);
                item.setChecked(true);
                toolbar.setTitle(item.getTitle());
                drawerLayout.closeDrawers();
                showHideFragment(getCurrentFragment(showFragment),getCurrentFragment(hideFragment));
                hideFragment=showFragment;
                return true;
            }
        });
        homeSearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(showFragment == Constants.TYPE_NEWS) {
                    // mGankFragment.doSearch(query);
                } else if(showFragment == Constants.TYPE_WECHAT) {
                   // mWechatFragment.DoSearch(query);
                    mWechatFragmet.doSearch(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        mSearchMenuItem=item;
        homeSearch.setMenuItem(item);
        return true;
    }


    @Override
    public void onBackPressedSupport() {
        if (homeSearch.isSearchOpen())
        {
            homeSearch.closeSearch();
        }
        else {
            showExitDialog();
        }
    }
    private void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }
    private SupportFragment getCurrentFragment(int current)
    {
        switch (current)
        {
            case Constants.TYPE_ZHIHU:
                return mZhihuFragment;
            case Constants.TYPE_NEWS:
                return mNewsFragment;
            case Constants.TYPE_WECHAT:
                return mWechatFragmet;
            case Constants.TYPE_RELAX:
                return mRelaxFragment;
            case Constants.TYPE_LIKE:
                return mLikeFragment;
            case Constants.TYPE_SETTING:
                return mSettingFragment;
            case Constants.TYPE_ABOUT:
                return mAboutFragment;
        }
        return mZhihuFragment;
    }
    private int getCurrentItem(int item)
    {
        switch (item)
        {
            case Constants.TYPE_ZHIHU:
                return R.id.nav_zhihu;
            case Constants.TYPE_RELAX:
                return R.id.nav_relax;
            case Constants.TYPE_WECHAT:
                return R.id.nav_wechat;
            case Constants.TYPE_LIKE:
                return R.id.nav_like;
            case Constants.TYPE_SETTING:
                return R.id.nav_setting;
            case Constants.TYPE_ABOUT:
                return R.id.nav_about;
            case Constants.TYPE_NEWS:
                return R.id.nav_news;
        }
        return R.id.nav_zhihu;
    }
    //结果回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode+"|||||||"+resultCode);
        if (requestCode==Constants.ACTIVITY_REQUEST&&resultCode==Constants.ACTIVITY_RESULT)
        {
            if (data!=null) {
                tv_name.setVisibility(View.VISIBLE);
                tv_name.setText(data.getStringExtra("name"));
                System.out.println("name"+data.getStringExtra("name"));
            }
        }
    }
}
