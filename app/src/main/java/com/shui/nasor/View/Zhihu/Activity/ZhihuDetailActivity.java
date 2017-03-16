package com.shui.nasor.View.Zhihu.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.Base.BaseActivity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuDetailEntity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuExtraEntity;
import com.shui.nasor.Presenter.Contract.ZhihuNewsContract;
import com.shui.nasor.Presenter.ZhuhiNewsPresenter;
import com.shui.nasor.R;
import com.shui.nasor.Utils.HtmlUtil;
import com.shui.nasor.Utils.ImageLoader;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 作者： max_Shui on 2016/12/12.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class ZhihuDetailActivity extends BaseActivity<ZhuhiNewsPresenter> implements ZhihuNewsContract.View {
    @BindView(R.id.zhihuIVHeader)
    ImageView zhihuIVHeader;
    @BindView(R.id.zhihuTVCopyRight)
    TextView zhihuTVCopyRight;
    @BindView(R.id.zhihuToolbar)
    Toolbar zhihuToolbar;
    @BindView(R.id.zhihuCollapsingToolbarLayouts)
    CollapsingToolbarLayout zhihuCollapsingToolbarLayouts;
    @BindView(R.id.zhihuAppBarLayout)
    AppBarLayout zhihuAppBarLayout;
    @BindView(R.id.zhihuWebView)
    WebView zhihuWebView;
    @BindView(R.id.zhihuProgressBar)
    ImageView zhihuProgressBar;
    @BindView(R.id.zhihuNestedScrollView)
    NestedScrollView zhihuNestedScrollView;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;
    private Intent intent;
    private int id;//id
    private String title;//标题
    private int AllComment;//所有评论
    private int LongComment;//长评论
    private int ShortComment;//短评论
    private boolean isBottomShowing = true;//下方的展示栏是否正在呈现
    private boolean isTransitionEnd=false;
    private String imgUrl;
    private ZhihuDetailEntity entity;
    private boolean isImageShow=false;
    private String shareUrl;//分享的链接
    private String shareTitle;//分享的标题
    @Override
    protected void initInject() {
        getActivityComponent().Inject(this);
    }

    @Override
    protected void initEventData() {
        intent=getIntent();
        id=intent.getIntExtra("id",0);
        title=intent.getStringExtra("title");
        mPresenter.getData(id);
        mPresenter.getExtra(id);
        setToolBar(zhihuToolbar,"");
        zhihuNestedScrollView.setOnScrollChangeListener(new ScrollerListener());
        WebSettings settings = zhihuWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        zhihuWebView.setWebViewClient(new myWebClient());
        getWindow().getSharedElementEnterTransition().addListener(new shareViewEnterListener());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    public void onBackPressedSupport() {
        finishAfterTransition();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK&&zhihuWebView.canGoBack())
        {
            zhihuWebView.goBack();
            return  true;
        }
        else
        {
            finishAfterTransition();
            return true;
        }
    }

    @Override
    public void showContent(ZhihuDetailEntity entity) {
        if (entity!=null)
        {   this.entity=entity;
            imgUrl=entity.getImage();
            mPresenter.queryLike(id);
            zhihuToolbar.setTitle(entity.getTitle());
            ImageLoader.load(this,entity.getImage(),zhihuIVHeader);
            zhihuTVCopyRight.setText(entity.getImage_source());
            String htmlData = HtmlUtil.createHtmlData(entity.getBody(), entity.getCss(), (List<String>) entity.getJs());
            zhihuWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
            shareUrl=entity.getShare_url();
            shareTitle=entity.getTitle();
            tvDetailBottomShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(shareTitle+"|||"+shareUrl);
                        onShare(shareTitle,shareTitle,shareUrl);
                }
            });
        }
    }

    /**
     * 显示额外信息
     * @param entity
     */
    @Override
    public void showExtra(ZhihuExtraEntity entity) {
        if (entity!=null)
        {
            AllComment=entity.getComments();
            ShortComment=entity.getShort_comments();
            LongComment=entity.getLong_comments();
            tvDetailBottomComment.setText(String.format("%d个评论",AllComment));
            tvDetailBottomLike.setText(String.format("%d个赞",entity.getPopularity()));
        }
    }
    //设置喜欢状态
    @Override
    public void setLikeState(boolean likeState) {
        fabLike.setSelected(likeState);
    }
    //点击评论按钮
    @OnClick(R.id.tv_detail_bottom_comment)
    void getComment()
    {
        Intent intent=new Intent(this,CommentActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("longComment",LongComment);
        intent.putExtra("shortComment",ShortComment);
        intent.putExtra("allComment",AllComment);
        startActivity(intent);
    }
    //点击喜欢按钮
    @OnClick(R.id.fab_like)
    void SetLike()
    {
        if (fabLike.isSelected()) {
            fabLike.setSelected(false);
            mPresenter.deleteLike();
        } else {
            fabLike.setSelected(true);
            mPresenter.insertLike();
        }
    }
    private void onShare(String title,String content,String link)
    {
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();
// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(link);
// text是分享文本，所有平台都需要这个字段
        oks.setText(content);
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(link);
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(link);
// 启动分享GUI
        oks.show(this);
}
    //下方的滑动显示
    private class ScrollerListener implements NestedScrollView.OnScrollChangeListener
    {

        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            if (oldScrollY-scrollY>0&&!isBottomShowing)//向上滑
            {
                isBottomShowing=true;
                llDetailBottom.animate().translationY(0);
            }
            else if (scrollY-oldScrollY>0&&isBottomShowing)//向下滑
            {
                isBottomShowing=false;
                llDetailBottom.animate().translationY(llDetailBottom.getHeight());
            }
        }
    }

    /**
     * webView
     */
    private class myWebClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {
            webView.loadUrl(s);
            return true;
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            zhihuProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            zhihuProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * shareView的监听
     */
    private class shareViewEnterListener implements android.transition.Transition.TransitionListener
    {


        @Override
        public void onTransitionStart(android.transition.Transition transition) {

        }

        @Override
        public void onTransitionEnd(android.transition.Transition transition) {//shareview的动画监听
            isTransitionEnd = true;
            if (imgUrl != null) {
                isImageShow = true;
                ImageLoader.load(ZhihuDetailActivity.this,entity.getImage(),zhihuIVHeader);
            }
        }

        @Override
        public void onTransitionCancel(android.transition.Transition transition) {

        }

        @Override
        public void onTransitionPause(android.transition.Transition transition) {

        }

        @Override
        public void onTransitionResume(android.transition.Transition transition) {

        }
    }
}
