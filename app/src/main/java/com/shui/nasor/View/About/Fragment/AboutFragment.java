package com.shui.nasor.View.About.Fragment;

import android.widget.TextView;

import com.shui.nasor.Base.BaseNormalFragment;
import com.shui.nasor.R;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/11.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class AboutFragment extends BaseNormalFragment {
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void initEventAndData() {
        tvTest.setText(getClass().getSimpleName());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragmenttest;
    }

}
