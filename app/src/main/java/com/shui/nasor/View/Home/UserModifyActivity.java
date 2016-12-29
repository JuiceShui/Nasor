package com.shui.nasor.View.Home;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.shui.nasor.APP.App;
import com.shui.nasor.Base.BaseNormalActivity;
import com.shui.nasor.DB.RealmHelper;
import com.shui.nasor.Model.Bean.MyData.BombUserEntity;
import com.shui.nasor.Model.Bean.MyData.RealmUserEntity;
import com.shui.nasor.R;
import com.shui.nasor.Utils.DateFormatUtil;
import com.shui.nasor.Utils.GlideImageLoader;
import com.shui.nasor.Utils.ImageLoader;
import com.shui.nasor.Utils.SharedPreferenceUtils;
import com.shui.nasor.Widget.CircleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 作者： max_Shui on 2016/12/25.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class UserModifyActivity extends BaseNormalActivity {
    private static final int IMAGE_PICKER =1 ;
    private static final int NAME_MODIFY=2;
    private static final int SIGN_MODIFY=3;
    private RealmHelper realmHelper;
    @BindView(R.id.normal_toolbar)
    Toolbar normalToolbar;
    @BindView(R.id.modify_civ_avatar)
    CircleImageView modifyCivAvatar;
    @BindView(R.id.modify_rl_avatar)
    RelativeLayout modifyRlAvatar;
    @BindView(R.id.modify_tv_name)
    TextView modifyTvName;
    @BindView(R.id.modify_rl_name)
    RelativeLayout modifyRlName;
    @BindView(R.id.modify_tv_uid)
    TextView modifyTvUid;
    @BindView(R.id.modify_rl_uid)
    RelativeLayout modifyRlUid;
    @BindView(R.id.modify_tv_sex)
    TextView modifyTvSex;
    @BindView(R.id.modify_rl_sex)
    RelativeLayout modifyRlSex;
    @BindView(R.id.modify_tv_birthday)
    TextView modifyTvBirthday;
    @BindView(R.id.modify_rl_birthday)
    RelativeLayout modifyRlBirthday;
    @BindView(R.id.modify_tv_sign)
    TextView modifyTvSign;
    @BindView(R.id.modify_rl_sign)
    RelativeLayout modifyRlSign;
    @BindView(R.id.modify_tv_private)
    TextView modifyTvPrivate;
    @BindView(R.id.modify_rl_private)
    RelativeLayout modifyRlPrivate;
    @BindView(R.id.modify_tv_safe)
    TextView modifyTvSafe;
    @BindView(R.id.modify_rl_safe)
    RelativeLayout modifyRlSafe;
    @BindView(R.id.modify_tv_cancel)
    TextView modifyTvCancel;
    @BindView(R.id.modify_rl_cancel)
    RelativeLayout modifyRlCancel;
    private int year, monthOfYear, dayOfMonth, hourOfDay, minute;
    private boolean sex=false;//性别
    private BombUserEntity entity;
    private String objectID;
    @Override
    protected void initEventAndData() {
        realmHelper=App.getAppComponent().realmHelper();
        setToolbar(normalToolbar,"个人资料");
        initImageSelector();
        entity=new BombUserEntity();
        objectID=SharedPreferenceUtils.getUser();
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<BombUserEntity> bmobQuery = new BmobQuery<BombUserEntity>();
        bmobQuery.getObject(objectID, new QueryListener<BombUserEntity>() {
            @Override
            public void done(BombUserEntity object,BmobException e) {
                if(e==null){
                    entity=object;
                    copyInfoToRealm(entity);
                    showInfo(entity);
                }else{
                    System.out.println("查询失败user：" + e.getMessage());
                }
            }
        });
        modifyTvBirthday.setText(entity.getBirthday());
    }

    /**
     * 初始化图片选择器
     */
    private void initImageSelector() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_userinfo_modify;
    }
    /**
     * 个性签名点击
     */
    @OnClick(R.id.modify_rl_sign)
    public void onSignClick()
    {
        String sign=modifyTvSign.getText().toString().trim();
        Intent intent=new Intent(this,UserModifySignActivity.class);
        intent.putExtra("sign",sign);
        startActivityForResult(intent,SIGN_MODIFY);
    }
    /**
     * 头像点击
     */
    @OnClick(R.id.modify_rl_avatar)
    public void onAvatarClick()
    {
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    /**
     * 昵称点击
     */
    @OnClick(R.id.modify_rl_name)
    public void onNameClick()
    {   String name=modifyTvName.getText().toString().trim();
        Intent intent=new Intent(this,UserNameModifyActivity.class);
        intent.putExtra("name",name);
        startActivityForResult(intent, NAME_MODIFY);
    }

    /**
     * 生日选择
     */
    @OnClick(R.id.modify_rl_birthday)
    public void onBirthdayClick(){
            setBirthday();
    }

    /**
     * 性别点击
     */
    @OnClick(R.id.modify_rl_sex)
    public void onSexClick()
    {
        showSexDialog();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                setPictureAndUpdate(data);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode==UserNameModifyActivity.NAME_RESULT_CODE_)//姓名修改
        {
            if (data!=null&&requestCode==NAME_MODIFY)
            {
                setNameChange(data);
            }
        }
        if (resultCode==UserModifySignActivity.SIGN_RESULT_CODE)//个性签名修改
        {
            if (data!=null&&requestCode==SIGN_MODIFY)
            {
                setSignChange(data);
            }
        }
    }

    /**
     * 图片选择后的回调执行
     * @param data 图片内容
     */
    private void setPictureAndUpdate(Intent data)
    {
        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
        ImageItem imageItem=images.get(0);
        ImageLoader.load(UserModifyActivity.this,imageItem.path,modifyCivAvatar);
        final BmobFile bmobFile=new BmobFile(new File(imageItem.path));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    //toast("上传文件成功:" + bmobFile.getFileUrl());
                    String newAvatar=bmobFile.getFileUrl();
                    // System.out.println(newAvatar);
                    entity.setAvatar(newAvatar);
                    entity.update(objectID, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Log.i("bmob","更新成功");
                            }else{
                                Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode());
                            }
                        }
                    });
                }else{
                    System.out.println("上传文件失败：" + e.getMessage());
                }
            }
            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }

    /**
     * 设置生日
     */
    private void setBirthday()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                modifyTvBirthday.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                String date= year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                entity.setBirthday(date);
                entity.update(objectID, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Log.i("bmob","更新成功");
                        }else{
                            Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });

            }
        },year, monthOfYear, dayOfMonth);
        DatePicker datePicker=datePickerDialog.getDatePicker();
        //转换时间
        long minTime=0,maxTime=0;
        minTime= DateFormatUtil.StringDateToLong("1900-01-01");
        maxTime=DateFormatUtil.StringDateToLong("2016-10-20");
        datePicker.setMinDate(minTime);//设置最大最小值
        datePicker.setMaxDate(maxTime);
        datePickerDialog.show();
    }

    /**
     * 性别选择
     */
    private void showSexDialog() {
        String sexString=modifyTvSex.getText().toString().trim();
        if (sexString.equals("女")||sexString=="女")
        {
            sex=false;
        }
        else
        {
            sex=true;
        }
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_sex_selector, null);
        // 设置style 控制默认dialog带来的边距问题
        final Dialog dialog = new Dialog(this, R.style.common_dialog);
        dialog.setContentView(view);
        dialog.show();
        RadioButton male= (RadioButton) view.findViewById(R.id.modify_rb_sex_male);
        RadioButton female= (RadioButton) view.findViewById(R.id.modify_rb_sex_female);
        male.setChecked(sex);
        female.setChecked(!sex);
        // 监听
        RadioGroup group = (RadioGroup)view.findViewById(R.id.modify_rg_sex);
               //绑定一个匿名监听器
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup arg0, int arg1) {
                  // TODO Auto-generated method stub
                  //获取变更后的选中项的ID
                  int radioButtonId = arg0.getCheckedRadioButtonId();
                  //根据ID获取RadioButton的实例
                  RadioButton rb = (RadioButton)view.findViewById(radioButtonId);
                  //更新文本内容，以符合选中项
                  if (rb.getId()==R.id.modify_rb_sex_female)
                  {
                        sex=false;
                  }
                  else
                  {
                      sex=true;
                  }
              }
                  });
        Button btnConfirm= (Button) view.findViewById(R.id.modify_btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    dialog.dismiss();
                    changeSex();
            }
        });
        // 设置相关位置，一定要在 show()之后
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(50, 10, 50, 10);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = params.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    /**
     * 改变性别 并上传
     */
    private void changeSex()
    {
        if (sex)
        {
            modifyTvSex.setText("男");
        }
        else
        {
            modifyTvSex.setText("女");
        }
        entity.setSex(sex);
        entity.update(objectID, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","更新成功");
                }else{
                    Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    /**
     * 通知名字改变
     * @param data
     */
    private void setNameChange(Intent data)
    {
        String name=data.getStringExtra("name");
        modifyTvName.setText(name);
    }

    /**
     * 通知签名改变
     * @param data
     */
    private void setSignChange(Intent data) {
        String sign=data.getStringExtra("sign");
        modifyTvSign.setText(sign);
    }
    /**
     * 将数据保存到本地
     */
    private void copyInfoToRealm(BombUserEntity entity)
    {
        RealmUserEntity realmEntity=new RealmUserEntity();
        realmEntity.setObjectId(entity.getObjectId());
        realmEntity.setSign(entity.getSign());
        realmEntity.setName(entity.getName());
        realmEntity.setPassWord(entity.getPassWord());
        realmEntity.setBirthday(entity.getBirthday());
        realmEntity.setEmail(entity.getEmail());
        realmEntity.setFans(entity.getFans());
        realmEntity.setFollower(entity.getFollower());
        realmEntity.setPhone(entity.getPhone());
        realmEntity.setLevel(entity.getLevel());
        realmEntity.setSex(entity.isSex());
        realmEntity.setUID(entity.getUID());
        realmHelper.InsertUserInfo(realmEntity);
    }

    /**
     * 展示数据
     * @param entity
     */
    private void showInfo(BombUserEntity entity) {
        modifyTvBirthday.setText(entity.getBirthday());
        modifyTvSign.setText(entity.getSign());
        modifyTvName.setText(entity.getName());
        modifyTvSex.setText(entity.isSex()==true?"男":"女");
        ImageLoader.load(this,entity.getAvatar(),modifyCivAvatar);
    }
}
