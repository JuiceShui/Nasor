package com.shui.nasor.View.Home;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shui.nasor.APP.App;
import com.shui.nasor.APP.AppUtils;
import com.shui.nasor.APP.Constants;
import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.Model.Bean.MyData.BombUserEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.SharedPreferenceUtils;
import com.shui.nasor.Utils.SnackBarUtils;

import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 作者： max_Shui on 2016/12/21.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class LoginActivity extends BaseNormalActivity implements View.OnFocusChangeListener, View.OnClickListener {
    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.rl_login_user_name)
    RelativeLayout rlLoginUserName;
    @BindView(R.id.rl_login_password)
    RelativeLayout rlLoginPassword;
    @BindView(R.id.actv_username)
    AutoCompleteTextView actvUsername;
    @BindView(R.id.edit_password)
    TextInputEditText editPassword;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    private boolean isNameEmpty=true;
    private boolean isPassWordEmpty=true;
    @Override
    protected void initEventAndData() {
        setToolbar(normalToolbar, App.getInstance().getString(R.string.login));
        actvUsername.setOnFocusChangeListener(this);
        editPassword.setOnFocusChangeListener(this);
        actvUsername.addTextChangedListener(new myNameTextWatcher(0));
        editPassword.addTextChangedListener(new myNameTextWatcher(1));
        registerButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        signInButton.setClickable(false);//初始化时 先设置为不可点击  必须设置在监听之后  不然不起作用
        /**
         * 在setOnClickListener()方法中有这样一段代码：
         if (!isClickable()) {
         setClickable(true);
         }
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_forget)
        {
            System.out.println("那就在  啊啊啊啊啊啊啊啊啊啊");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }
    //焦点改变  改变图片
    @Override
    public void onFocusChange(View view, boolean b) {
        switch(view.getId())
        {
            case R.id.actv_username:
                if (b)
                {
                    rlLoginUserName.setVisibility(View.VISIBLE);
                    rlLoginPassword.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.edit_password:
                if (b)
                {
                    rlLoginUserName.setVisibility(View.INVISIBLE);
                    rlLoginPassword.setVisibility(View.VISIBLE);
                }
        }
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId())
        {
            case R.id.sign_in_button:
                //signInButton.setBackgroundColor(AppUtils.getColor(R.color.colorNormal));
                String Phone=actvUsername.getText().toString().trim();//号码
                final String PassWord=editPassword.getText().toString().trim();//密码
                BmobQuery<BombUserEntity> query=new BmobQuery<>();
                query.addWhereEqualTo("phone",Phone);
                query.findObjects(new FindListener<BombUserEntity>() {
                    @Override
                    public void done(List<BombUserEntity> list, BmobException e) {
                        if (list.size()>0)
                        {
                            for (BombUserEntity info:list)
                            {
                                if (info.getPassWord()==PassWord||info.getPassWord().equals(PassWord))
                                {
                                    System.out.println("登录成功");
                                    SharedPreferenceUtils.setUser(info.getObjectId());
                                    Intent intent=getIntent();
                                    SharedPreferenceUtils.setUserLogin(true);
                                    intent.setClass(LoginActivity.this,HomeActivity.class);
                                    intent.putExtra("name",info.getName());
                                    setResult(Constants.ACTIVITY_RESULT,intent);
                                    LoginActivity.this.finishAfterTransition();
                                }
                                else
                                {
                                    SnackBarUtils.showShort(view,"密码错了，再想想吧~~");
                                }
                            }
                        }
                        else {
                            SnackBarUtils.showShort(view,"没有这个账户");
                        }

                    }
                });
                break;
            case R.id.register_button:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 用户名的监听
     */
    private class myNameTextWatcher implements TextWatcher
    {
        int type;

        public myNameTextWatcher(int type) {
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String re=charSequence.toString();
                if (re.length()==0)
                {
                    if (type==0)
                    {
                        isNameEmpty=true;
                    }
                    else
                    {
                        isPassWordEmpty=true;
                    }
                }
                if (re.length()>=1)
                {
                    if (type==0)
                    {
                        isNameEmpty=false;
                    }
                    else
                    {
                        isPassWordEmpty=false;
                    }
                }
        }

        @Override
        public void afterTextChanged(Editable editable) {
                if (!isNameEmpty&&!isPassWordEmpty)
                {
                    signInButton.setClickable(true);
                    signInButton.setBackgroundColor(AppUtils.getColor(R.color.colorAccent));
                }
                else {
                    signInButton.setClickable(false);
                    signInButton.setBackgroundColor(AppUtils.getColor(R.color.button_unenable));
                }
        }
    }
}
