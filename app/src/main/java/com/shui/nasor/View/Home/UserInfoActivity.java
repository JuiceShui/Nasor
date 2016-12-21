package com.shui.nasor.View.Home;

import android.widget.ImageView;
import android.widget.Toast;

import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.R;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 作者： max_Shui on 2016/12/20.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class UserInfoActivity extends BaseNormalActivity {
    @BindView(R.id.user_Share)
    ImageView userShare;

    @Override
    protected void initEventAndData() {
        userShare.setImageResource(R.drawable.header);
        Person p2 = new Person();
        p2.setName("shui");
        p2.setAddress("成都郫县");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                   Toast.makeText(UserInfoActivity.this,"添加数据成功，返回objectId为："+objectId,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserInfoActivity.this,"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

}
