package com.shui.nasor.View.WeChat.Activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.shui.nasor.APP.App;
import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Model.RealmBean.LikeBean;
import com.shui.nasor.Model.RealmBean.LikeType;
import com.shui.nasor.R;
import com.shui.nasor.View.viewHelper.ViewHelperController;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/15.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class WeChatActivity extends BaseNormalActivity {
    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.weChatWebView)
    WebView weChatWebView;
    private RealmHelper realmHelper;
    private Intent intent;
    private String id;
    private String title;
    private String link;
    private MenuItem menuItem;
    private boolean isLiked;//是否标注为喜欢
    private ViewHelperController controller;
    private boolean isShowLoading=false;
    @Override
    protected void initEventAndData() {
        controller=new ViewHelperController(weChatWebView);
        realmHelper= App.getAppComponent().realmHelper();
        intent=getIntent();
        id=intent.getStringExtra("id");
        title=intent.getStringExtra("title");
        link=intent.getStringExtra("link");
        setToolbar(normalToolbar,title);
        WebSettings settings = weChatWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        weChatWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
        weChatWebView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (i<100)
                {
                    if (!isShowLoading)
                    {
                        showLoading();
                        isShowLoading=true;
                    }
                }
                if (i==100)//加载完成
                {
                        hiddenLoading();
                }
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                setTitle(s);
            }
        });
        weChatWebView.loadUrl(link);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wechat;
    }
    //创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.wechat_menu,menu);
        menuItem=menu.findItem(R.id.weChatLike);
        setLikeState(realmHelper.queryLike(id));
        return true;
    }

    private void setLikeState(boolean isLike) {
      if (isLike)
      {
          menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
          isLiked=true;
      }
        else {
          menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
          isLiked=false;
      }
    }
    //选项菜单的点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.weChatLike:
                    if (isLiked)//当前是喜欢
                    {
                        isLiked=false;
                        realmHelper.deleteLike(id);
                        menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
                    }
                else //当前不是喜欢 标记为喜欢
                    {
                        isLiked=true;
                        LikeBean bean=new LikeBean();
                        bean.setId(id);
                        bean.setUrl(link);
                        bean.setPic(id);
                        bean.setTitle(title);
                        bean.setType(LikeType.LIKE_WECHAT);
                        bean.setTime(System.currentTimeMillis());
                        realmHelper.insertLike(bean);
                        menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
                    }
                break;
            case R.id.weChatShare:
                break;
            case R.id.weChatCopy:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressedSupport() {
        finishAfterTransition();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (weChatWebView.canGoBack())
        {
            weChatWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void toggleShowLoading(boolean toggle, String msg) {
        if (null == controller) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            controller.showLoading(msg);
        } else {
            controller.restore();
        }
    }
    private void showLoading() {
        toggleShowLoading(true,null);
    }

    private void hiddenLoading() {
        toggleShowLoading(false,null);
    }

}
