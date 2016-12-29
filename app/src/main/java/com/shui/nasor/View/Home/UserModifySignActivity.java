package com.shui.nasor.View.Home;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.Model.Bean.MyData.BombUserEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.SharedPreferenceUtils;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 作者： max_Shui on 2016/12/26.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class UserModifySignActivity extends BaseNormalActivity {
    public static final int SIGN_RESULT_CODE = 3;
    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.modify_tiet_sign)
    TextInputEditText modifyTietSign;
    @BindView(R.id.modify_tv_signcount)
    TextView modifyTvSigncount;
    private Intent intent;
    private String sign;
    private int maxCount=65;

    @Override
    protected void initEventAndData() {
        setToolbar(normalToolbar, "修改个性签名");
        intent = getIntent();
        sign = intent.getStringExtra("sign");
        modifyTietSign.setText(sign);
        modifyTietSign.requestFocus();
        modifyTietSign.setCursorVisible(true);
        modifyTvSigncount.setText((maxCount-sign.length())+"");
        modifyTietSign.addTextChangedListener(new signTextWatcher());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_usersign_modify;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.username_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_modify_save) {
            //保存
            BombUserEntity entity=new BombUserEntity();
            final String sign=modifyTietSign.getText().toString().trim();
            String objectID= SharedPreferenceUtils.getUser();
            entity.setSign(sign);
            entity.update(objectID, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e==null)//更新成功
                    {
                        Intent intent=new Intent(UserModifySignActivity.this,UserModifyActivity.class);
                        intent.putExtra("sign",sign);
                        setResult(SIGN_RESULT_CODE,intent);
                        finishAfterTransition();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
    private class signTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String inputting = charSequence.toString();//获取已经输入的字符
            if (inputting.length() <maxCount+1) {
                modifyTvSigncount.setText((maxCount-inputting.length())+"");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
                String after=modifyTietSign.getText().toString();
            if (after.length()>=maxCount+1)
            {
                String newString=after.substring(0,maxCount);
                modifyTietSign.setText(newString);
                modifyTietSign.setSelection(newString.length());
            }
        }
    }
}
