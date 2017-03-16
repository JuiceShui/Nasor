package com.shui.nasor.View.Home.Fragment;

import android.widget.TextView;

import com.shui.nasor.Base.BaseNormalFragment;
import com.shui.nasor.R;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/28.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class HomeFragment extends BaseNormalFragment {
    @BindView(R.id.fragment_name)
    TextView fragmentName;

    @Override
    protected void initEventAndData() {
        fragmentName.setText("");
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_user_test;
    }
}
