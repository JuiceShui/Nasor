package com.shui.nasor.View.Home;

/**
 * 作者： max_Shui on 2016/12/11.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.shui.nasor.Base.BaseActivity;
import com.shui.nasor.Model.Bean.Zhihu.ZhihuWelcomeEntity;
import com.shui.nasor.Presenter.Contract.WelcomeContract;
import com.shui.nasor.Presenter.WelcomePresenter;
import com.shui.nasor.R;
import com.shui.nasor.Utils.ImageLoader;

import butterknife.BindView;

/**
 * 进入时的activity
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {
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

    @Override
    protected void initInject() {
        getActivityComponent().Inject(this);
    }

    @Override
    protected void initEventData() {
        mPresenter.getWelcomeData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }


    @Override
    public void showContent(ZhihuWelcomeEntity welcomeBean) {
        ImageLoader.load(this, welcomeBean.getImg(), splashImage);
        splashImage.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        splashDes.setText(welcomeBean.getText());
    }

    @Override
    public void jumpToMain() {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.activity_out_anim_alpha_higher,R.anim.activity_in_anim_alpha_higher);
    }
}
