
package com.qiye.formermilitaryp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.activity.person.SexSelectActivity;
import com.qiye.formermilitaryp.base.BaseActivity;
import com.qiye.formermilitaryp.bean.request.RegRequestBean;
import com.qiye.formermilitaryp.utils.LogUtils;
import com.qiye.formermilitaryp.utils.networkRequest2.NetApi;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultListener;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultSub;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册
 */
public class RegActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private TextView tv_sex;
    private EditText et_age;
    private EditText et_tel;
    private EditText et_pwd;
    private EditText et_iDNumber;
    private EditText et_placeOfResidence;
    private EditText et_nowAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        fullScreen(RegActivity.this);
        initView();
    }

    private void initView() {
        createV_statusBarHeight(findViewById(R.id.v_statusBar));
        //标题
        tvSetText("注册", (TextView) findViewById(R.id.tv_public_title_title));

        et_name = findViewById(R.id.et_name);
        LinearLayout ll_sex=f(R.id.ll_sex);
        ll_sex.setOnClickListener(this);
        tv_sex = f(R.id.tv_sex);
        et_age = f(R.id.et_age);
        et_tel = f(R.id.et_tel);
        et_pwd = f(R.id.et_pwd);
        et_iDNumber = f(R.id.et_iDNumber);
        et_placeOfResidence = f(R.id.et_placeOfResidence);
        et_nowAddress = f(R.id.et_nowAddress);
        ((View)f(R.id.tv_ok)).setOnClickListener(this);
    }




    //页面请求码
    private final int pageRequestCode=4869;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_sex://性别选择
                Intent intent=new Intent(RegActivity.this, SexSelectActivity.class);
                startActivityForResult(intent,pageRequestCode);
                break;
            case R.id.tv_ok://提交
                submit();
                break;
        }
    }

    //提交
    private void submit(){
        String ageStr=et_age.getText().toString();
        String certNumberStr=et_iDNumber.getText().toString();
        String homeAddressStr=et_nowAddress.getText().toString();
        String passwordStr=et_pwd.getText().toString();
        String phoneStr=et_tel.getText().toString();
        String registeredPlaceStr=et_placeOfResidence.getText().toString();
        String sexStr=tv_sex.getText().toString();
        String userNameStr=et_name.getText().toString();

        ageStr=ageStr.trim();
        certNumberStr=certNumberStr.trim();
        homeAddressStr=homeAddressStr.trim();
        passwordStr=passwordStr.trim();
        phoneStr=phoneStr.trim();
        registeredPlaceStr=registeredPlaceStr.trim();
        sexStr=sexStr.trim();
        userNameStr=userNameStr.trim();

        boolean isInfoComplete=true;
        if (TextUtils.isEmpty(ageStr))isInfoComplete=false;
        if (TextUtils.isEmpty(certNumberStr))isInfoComplete=false;
        if (TextUtils.isEmpty(homeAddressStr))isInfoComplete=false;
        if (TextUtils.isEmpty(passwordStr))isInfoComplete=false;
        if (TextUtils.isEmpty(phoneStr))isInfoComplete=false;
        if (TextUtils.isEmpty(registeredPlaceStr))isInfoComplete=false;
        if (TextUtils.isEmpty(sexStr))isInfoComplete=false;
        if (TextUtils.isEmpty(userNameStr))isInfoComplete=false;

        if (!isInfoComplete){
            mmdialog.showSuccess("您的信息未填写完整");
            return;
        }

        try{
            Integer.valueOf(ageStr);
        }catch (Exception e){
            mmdialog.showSuccess("您的年龄输入格式有误");
            return;
        }

        int sexNumber=sexStr.equals("男")?1:2;

        RegRequestBean bean=new RegRequestBean();
        bean.setAge(Integer.parseInt(ageStr));
        bean.setCertNumber(certNumberStr);
        bean.setHomeAddress(homeAddressStr);
        bean.setPassword(passwordStr);
        bean.setPhone(phoneStr);
        bean.setRegisteredPlace(registeredPlaceStr);
        bean.setSex(sexNumber);
        bean.setUserName(userNameStr);

        NetApi.reg(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("regActivity",""+result);
                mmdialog.showSuccess("注册成功,管理员审核通过后即可登录");
                finish();
            }

            @Override
            public void onFault(String errorMsg) {
                mmdialog.showError(errorMsg);
            }
        }));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (pageRequestCode==requestCode){//页面请求的回调
            if (resultCode==SexSelectActivity.pageSuccessResponseCode){//性别选择回调
                tvSetText(data.getStringExtra(SexSelectActivity.pageResponseTextKeyName),tv_sex);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(0,500);//状态栏
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0://状态栏
                    createStatusBarTextIconColorDepth(true);
                    break;
            }
        }
    };
}
