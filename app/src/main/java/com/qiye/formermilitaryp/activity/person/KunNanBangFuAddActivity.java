package com.qiye.formermilitaryp.activity.person;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.base.BaseActivity;
import com.qiye.formermilitaryp.bean.request.InsertSupportBean;
import com.qiye.formermilitaryp.bean.request.UpdateSupportBean;
import com.qiye.formermilitaryp.bean.response.KunNanBangFuDetailsBean;
import com.qiye.formermilitaryp.utils.LogUtils;
import com.qiye.formermilitaryp.utils.networkRequest2.NetApi;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultListener;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultSub;

/**
 * 困难帮扶增加修改页面
 */
public class KunNanBangFuAddActivity extends BaseActivity implements View.OnClickListener {


    int flag=0;//0 add 1 update
    KunNanBangFuDetailsBean.DataBean dataBean=null;//修改时展示信息的数据源

    private EditText et_name;
    private EditText et_event;
    private EditText et_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kun_nan_bang_fu_add);
        initData();
        initView();
    }

    private void initData() {
        try{
            flag= Integer.parseInt(getIntent().getStringExtra("flag"));
        }catch (Exception e){

        }
        dataBean= (KunNanBangFuDetailsBean.DataBean) getIntent().getSerializableExtra("data");
    }

    private void initView() {
        TextView tv_public_title_title=f(R.id.tv_public_title_title);
        tv_public_title_title.setText("困难帮扶");

        et_name = f(R.id.et_name);
        et_event = f(R.id.et_event);
        et_description = f(R.id.et_description);
        View tv_ok=f(R.id.tv_ok);
        tv_ok.setOnClickListener(this);

        if (flag==1){//修改
            String details=dataBean.getDetails();
            String event=dataBean.getEvent();
            String realName=dataBean.getRealName();
            if (TextUtils.isEmpty(details))details="";
            if (TextUtils.isEmpty(event))event="";
            if (TextUtils.isEmpty(realName))realName="";
            et_name.setText(realName);
            et_description.setText(details);
            et_event.setText(event);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ok://提交
                submitCheck();
                break;
        }
    }

    //提交的前置校验
    private void submitCheck(){
        String name=et_name.getText().toString();
        String event=et_event.getText().toString();
        String description=et_description.getText().toString();

        name=name.trim();
        event=event.trim();
        description=description.trim();

        boolean isInfoComplete=true;
        if (TextUtils.isEmpty(name))isInfoComplete=false;
        if (TextUtils.isEmpty(event))isInfoComplete=false;
        if (TextUtils.isEmpty(description))isInfoComplete=false;

        if (!isInfoComplete){
            mmdialog.showSuccess("您还没有信息未填写");
            return;
        }

        if (flag==1){//修改
            UpdateSupportBean updateSupportBean=new UpdateSupportBean();
            updateSupportBean.setDetails(description);
            updateSupportBean.setRealName(name);
            updateSupportBean.setEvent(event);
            String id=dataBean.getId();
            if (TextUtils.isEmpty(id)){//修改时无法判断修改哪一个帮扶
                mmdialog.showSuccess("无该困难帮扶信息,修改失败");
                return;
            }
            updateSupportBean.setId(id);
            submit_update(updateSupportBean);
            return;
        }

        InsertSupportBean insertSupportBean=new InsertSupportBean();
        insertSupportBean.setDetails(description);
        insertSupportBean.setRealName(name);
        insertSupportBean.setEvent(event);
        //只有添加逻辑
        NetApi.insertOrUpdateSupport(insertSupportBean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("添加帮扶",result);
                mmdialog.showSuccess("添加成功");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },1000);
            }

            @Override
            public void onFault(String errorMsg) {
                mmdialog.showError(errorMsg);
            }
        }));

    }

    private void submit_update(UpdateSupportBean updateSupportBean){
        NetApi.insertOrUpdateSupport2(updateSupportBean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("修改帮扶",result);
                mmdialog.showSuccess("修改成功");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },1000);
            }

            @Override
            public void onFault(String errorMsg) {
                mmdialog.showError(errorMsg);
            }
        }));
    }
}
