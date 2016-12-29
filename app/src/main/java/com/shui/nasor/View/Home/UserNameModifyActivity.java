package com.shui.nasor.View.Home;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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


public class UserNameModifyActivity extends BaseNormalActivity {
    public static final int NAME_RESULT_CODE_ = 2;
    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.modify_tiet_name)
    TextInputEditText modifyTietName;
    @BindView(R.id.modify_tv_name_alert)
    TextView modifyTvNameAlert;
    private Intent intent;
    private String name;
    private int minLength=3;
    private int maxLength=12;
    @Override
    protected void initEventAndData() {
        intent=getIntent();
        name=intent.getStringExtra("name");
        setToolbar(normalToolbar,"修改昵称");
        modifyTietName.setText(name);
        modifyTietName.requestFocus();
        modifyTietName.setCursorVisible(true);
        modifyTietName.addTextChangedListener(new nameTextWatcher());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_username_modify;
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
            final String name=modifyTietName.getText().toString();
            String objectID= SharedPreferenceUtils.getUser();
            entity.setName(name);
            entity.update(objectID, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e==null)//更新成功
                    {
                        Intent intent=new Intent(UserNameModifyActivity.this,UserModifyActivity.class);
                        intent.putExtra("name",name);
                        setResult(NAME_RESULT_CODE_,intent);
                        finishAfterTransition();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
    private class nameTextWatcher implements TextWatcher
    {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String inputting = charSequence.toString();//获取已经输入的字符
            if (inputting.length() <minLength) {
                modifyTvNameAlert.setVisibility(View.VISIBLE);
                modifyTvNameAlert.setText("至少要3个字吧");
            }
            if (inputting.length()>maxLength)
            {
                modifyTvNameAlert.setVisibility(View.VISIBLE);
                modifyTvNameAlert.setText("呃呃~太长了吧");
            }
            else
            {
                modifyTvNameAlert.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String after=modifyTietName.getText().toString();
            if (after.length()>=maxLength+1)
            {
                String newString=after.substring(0,maxLength);
                modifyTietName.setText(newString);
                modifyTietName.setSelection(newString.length());
            }
        }
    }
}
