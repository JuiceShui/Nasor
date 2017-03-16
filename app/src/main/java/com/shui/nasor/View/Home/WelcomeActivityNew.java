package com.shui.nasor.View.Home;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shui.nasor.Base.BaseActivity;
import com.shui.nasor.Presenter.Contract.WelcomeContractNew;
import com.shui.nasor.Presenter.WelcomePresenterNew;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;

import butterknife.BindView;

/**
 * 作者： JuiceShui on 2017/3/14.
 * We improve ourselves by victories over ourselves.
 * There must be contests, and we must win.
 */
public class WelcomeActivityNew extends BaseActivity<WelcomePresenterNew> implements WelcomeContractNew.View {
    @BindView(R.id.splash_image)
    ImageView splashImage;
    @BindView(R.id.splash_logo)
    TextView splashLogo;
    @BindView(R.id.splash_des)
    TextView splashDes;
    @BindView(R.id.splash_version)
    TextView splashVersion;
    @BindView(R.id.splash_copyRight)
    TextView splashCopyRight;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void initInject() {
        getActivityComponent().Inject(this);
    }

    @Override
    protected void initEventData() {
        mPresenter.getData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void jumpToMain() {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.activity_out_anim_alpha_higher,R.anim.activity_in_anim_alpha_higher);
    }

    @Override
    public void showPic(String url,String title) {
        ImageLoader.load(this, url, splashImage);
        splashCopyRight.setText(title);
        splashImage.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
    }
}
