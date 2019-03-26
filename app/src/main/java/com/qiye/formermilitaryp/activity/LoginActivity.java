package com.qiye.formermilitaryp.activity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.base.BaseActivity;
import com.qiye.formermilitaryp.bean.response.LoginResponseBean;
import com.qiye.formermilitaryp.utils.ActivityCollector;
import com.qiye.formermilitaryp.utils.LogUtils;
import com.qiye.formermilitaryp.utils.SpUtil;
import com.qiye.formermilitaryp.utils.networkRequest2.NetApi;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultListener;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultSub;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Context context;
    private EditText et_tel;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;
        initView();
    }

    private void initView() {
        //标题
        tvSetText("登录", (TextView) findViewById(R.id.tv_public_title_title));

        findViewById(R.id.tv_reg).setOnClickListener(this);
        et_tel = f(R.id.et_tel);
        et_pwd = f(R.id.et_pwd);
        View tv_login= f(R.id.tv_login);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_reg:
                Intent intent = new Intent(context, RegActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login:
                submit_login();
                break;
        }
    }

    private void submit_login(){
        String telStr=et_tel.getText().toString();
        String pwdStr=et_pwd.getText().toString();

        telStr=telStr.trim();
        pwdStr=pwdStr.trim();

        boolean isInfoComplete=true;
        if (TextUtils.isEmpty(telStr))isInfoComplete=false;
        if (TextUtils.isEmpty(pwdStr))isInfoComplete=false;

        if (!isInfoComplete){
            mmdialog.showSuccess("手机号或密码不能为空");
            return;
        }
        Map<String,String > hashMap=new HashMap<>();
        hashMap.put("userName",telStr);
        hashMap.put("password",pwdStr);

        NetApi.login(hashMap,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("login",result);
                mmdialog.showSuccess("登录成功");
                LoginResponseBean bean=new Gson().fromJson(result,LoginResponseBean.class);
                try{
                    String userId=bean.getData().getUser().getId();
                    if (TextUtils.isEmpty(userId))Integer.valueOf("s");
                    String userName=bean.getData().getUser().getUserName();
                    String userTel=bean.getData().getUser().getPhone();
                    SpUtil.spSave(context,SpUtil.storageFlieName,SpUtil.spSaveUserIdKeyName,userId);
                    SpUtil.spSave(context,SpUtil.storageFlieName,SpUtil.spSaveUserNameKeyName,userName);
                    SpUtil.spSave(context,SpUtil.storageFlieName,SpUtil.spSaveUserTelKeyName,userTel);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },1000);
                }catch (Exception e){
                    mmdialog.showSuccess("登录异常,用户身份异常");
                }
            }

            @Override
            public void onFault(String errorMsg) {
                mmdialog.showError(errorMsg);
            }
        }));
    }

    //关闭页面
    public void click_finishActivity(View view) {
        ActivityCollector.finishAllActivity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //这里写你要在用户按下返回键同时执行的动作
            moveTaskToBack(false);          //核心代码：屏蔽返回行为
            //前提是上个页面不能finish掉,结束该页面时结束上一个页面可以在上一个页面写一个自毁的静态方法;如果不存在上个页面这段代码是否有效?(待测试)
            ActivityCollector.finishAllActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(0, 500);//状态栏
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://状态栏
                    //createStatusBarTextIconColorDepth(true);//未使用状态栏沉浸时,使用产生ui bug
                    break;
            }
        }
    };
}
