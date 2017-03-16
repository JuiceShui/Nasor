package com.shui.nasor.View.Home;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.Model.Bean.MyData.BombUserEntity;
import com.shui.nasor.R;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * 作者： max_Shui on 2016/12/21.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class RegisterActivity extends BaseNormalActivity implements View.OnClickListener {

    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.register_et_phone)
    TextInputEditText registerEtPhone;
    @BindView(R.id.register_tv_phone)
    TextView registerTvPhone;
    @BindView(R.id.register_et_code)
    TextInputEditText registerEtCode;
    @BindView(R.id.register_btn_code)
    Button registerBtnCode;
    @BindView(R.id.register_tv_code)
    TextView registerTvCode;
    @BindView(R.id.register_et_nickname)
    TextInputEditText registerEtNickname;
    @BindView(R.id.register_tv_nick)
    TextView registerTvNick;
    @BindView(R.id.register_et_password)
    TextInputEditText registerEtPassword;
    @BindView(R.id.register_tv_password)
    TextView registerTvPassword;
    @BindView(R.id.register_et_repassword)
    EditText registerEtRepassword;
    @BindView(R.id.register_tv_repassword)
    TextView registerTvRepassword;
    @BindView(R.id.register_btn_success)
    Button registerBtnSuccess;
    private static final String DEFAULT_COUNTRY_ID = "86";
    private EventHandler eventHandler;//短信的回调接口
    private OnSendMessageHandler osmHandler;//短信验证码接口
    private static final int TYPE_PHONE=1;
    private static final int TYPE_CODE=2;
    private static final int TYPE_NAME=3;
    private static final int TYPE_PASSWORD=4;
    private static final int TYPE_REPASSWORD=5;
    private String passwordCheck="";
    private boolean PASSWORD_CHECK=false,SMS_CHECK=false,NAME_CHECK=false,PASSWORD_LENGTH_CHECK=false;
    private boolean phoneEmpty=true,codeEmpty=true,nameEmpty=true,passEmpty=true,rePassEmpty=true;
    @Override
    protected void initEventAndData() {
        registerBtnCode.setOnClickListener(this);
        registerBtnCode.setClickable(false);
        initEventHandler();//初始化短信回调
        setToolbar(normalToolbar,"注册");//设置toolbar
        registerEtPhone.addTextChangedListener(new myTextChange(TYPE_PHONE));
        registerEtCode.addTextChangedListener(new myTextChange(TYPE_CODE));
        registerEtNickname.addTextChangedListener(new myTextChange(TYPE_NAME));
        registerEtPassword.addTextChangedListener(new myTextChange(TYPE_PASSWORD));
        registerEtRepassword.addTextChangedListener(new myTextChange(TYPE_REPASSWORD));
        registerBtnSuccess.setOnClickListener(this);//注册按钮
        registerBtnSuccess.setClickable(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.register_btn_code:
                String phone=registerEtPhone.getText().toString();
                if (phone!=null)
                {
                    System.out.println(phone);
                    getSMS(phone,DEFAULT_COUNTRY_ID);
                }
                break;
            case R.id.register_btn_success:
                    if (PASSWORD_CHECK&&SMS_CHECK&&NAME_CHECK&&PASSWORD_LENGTH_CHECK)
                    {
                        BombUserEntity userInfo=new BombUserEntity();
                        userInfo.setName(registerEtNickname.getText().toString().trim());
                        userInfo.setPhone(registerEtPhone.getText().toString().trim());
                        userInfo.setPassWord(registerEtPassword.getText().toString().trim());
                        userInfo.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if(e==null){
                                    System.out.println("创建成功");
                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                    finish();
                                    //overridePendingTransition(R.anim.activity_in_anim_transion_horzita_left,R.anim.activity_out_anim_transion_horzital_left);
                                }else{
                                    System.out.println("创建数据失败：" + e.getMessage());
                                }
                            }
                        });
                    }
                break;
        }
    }

    private void getSMS(String phone, String defaultCountryId) {
        SMSSDK.getVerificationCode(defaultCountryId, phone.trim(), osmHandler);//获取验证码
    }

    private class  mySMS implements OnSendMessageHandler
    {
        @Override
        public boolean onSendMessage(String s, String s1) {
            return true;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        SMSSDK.registerEventHandler(eventHandler);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
    private void initEventHandler()
    {
        eventHandler=new EventHandler()
        {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        System.out.println("提交验证码成功");
                        SMS_CHECK=true;//标记成功
                        //提交验证码成功
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        System.out.println("获取验证码成功"+result);
                        //获取验证码成功
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                        System.out.println("返回支持发送验证码的国家列表");
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
    }
    private class myTextChange implements TextWatcher
    {
        private int type;

        public myTextChange(int type) {
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String input=charSequence.toString();
            switch (type)
            {
                case TYPE_PHONE://长度不为11不可点击
                    if (input.length()==11)
                    {
                        registerBtnCode.setClickable(true);
                        phoneEmpty=false;
                    }
                    else
                    {
                        registerBtnCode.setClickable(false);
                    }
                    if (input.length()<=0)
                    {
                        phoneEmpty=true;
                    }
                    break;
                case TYPE_CODE:
                    if (input.length()>0)
                    {
                        codeEmpty=false;
                        if (input.length()==4)
                        {
                            SMSSDK.submitVerificationCode("86", registerEtPhone.getText().toString(), input.trim());
                        }
                    }
                    else
                    {
                        codeEmpty=true;
                    }
                    break;
                case TYPE_NAME:
                    if (input.length()>0)
                    {
                        nameEmpty=false;
                    }
                    else
                    {
                        nameEmpty=true;
                    }
                    break;
                case TYPE_PASSWORD:
                    if (input.length()>0)
                    {
                        passEmpty=false;
                    }
                    else
                    {
                        passEmpty=true;
                    }
                    break;
                case TYPE_REPASSWORD:
                    if (input.length()>0)
                    {
                        rePassEmpty=false;
                    }
                    else
                    {
                        rePassEmpty=true;
                    }
                    break;
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
            registerTvRepassword.setVisibility(View.INVISIBLE);
            registerTvNick.setVisibility(View.INVISIBLE);
            if (!nameEmpty&&!codeEmpty&&!nameEmpty&&!passEmpty&&!rePassEmpty)
            {
                registerBtnSuccess.setClickable(true);
            }
            else {
                registerBtnSuccess.setClickable(false);
            }
            switch (type) {
                case TYPE_PHONE://长度不为11不可点击
                    break;
                case TYPE_CODE:
                    break;
                case TYPE_NAME:
                    if (registerEtNickname.getText().toString().length()>=4&&registerEtNickname.getText().toString().length()<=14)
                    {
                        NAME_CHECK=true;
                    }
                    else if(registerEtNickname.getText().toString().length()<4)
                    {
                        NAME_CHECK=false;
                        registerTvNick.setVisibility(View.VISIBLE);
                        registerTvNick.setText("昵称长度不够哦");
                    }
                    else {
                        NAME_CHECK=false;
                        registerTvNick.setVisibility(View.VISIBLE);
                        registerTvNick.setText("昵称长度太长了！！！！");
                    }
                    break;
                case TYPE_PASSWORD:
                    int passwordLength=registerEtPassword.getText().toString().trim().length();
                    if (passwordLength>=6&&passwordLength<=16)
                    {
                        PASSWORD_LENGTH_CHECK=true;
                    }
                    else {
                        PASSWORD_LENGTH_CHECK=false;
                        registerTvPassword.setText("密码长度不合适哦~~");
                    }
                    break;
                case TYPE_REPASSWORD:
                    String pass=registerEtPassword.getText().toString();
                    String repass=registerEtRepassword.getText().toString();
                    if (pass==repass||repass.equals(pass))
                    {
                        PASSWORD_CHECK=true;//密码验证成功
                    }
                    else
                    {
                        registerTvRepassword.setVisibility(View.VISIBLE);
                        registerTvRepassword.setText("两次密码不一致哦~~~~~~~~~");
                        PASSWORD_CHECK=false;//密码不一致
                    }
                    break;
            }}
    }
}
