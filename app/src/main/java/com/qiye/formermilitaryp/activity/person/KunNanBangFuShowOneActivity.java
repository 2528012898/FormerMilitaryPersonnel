package com.qiye.formermilitaryp.activity.person;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.base.BaseActivity;
import com.qiye.formermilitaryp.bean.response.KunNanBangFuDetailsBean;
import com.qiye.formermilitaryp.utils.LogUtils;
import com.qiye.formermilitaryp.utils.networkRequest2.NetApi;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultListener;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultSub;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的困难帮扶页面
 */
public class KunNanBangFuShowOneActivity extends BaseActivity implements View.OnClickListener {

    String id;//帮扶id
    private TextView tv_name;
    private TextView tv_event;
    private TextView tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de_kun_nan_bang_fu);
        initData();
        initView();

    }

    private void initView() {
        TextView tv_public_title_title=f(R.id.tv_public_title_title);
        tv_public_title_title.setText("困难帮扶");

        TextView tv_public_title_right=f(R.id.tv_public_title_right);
        tv_public_title_right.setText("修改");
        tv_public_title_right.setVisibility(View.VISIBLE);
        tv_public_title_right.setOnClickListener(this);

        tv_name = f(R.id.tv_name);
        tv_event = f(R.id.tv_event);
        tv_description = f(R.id.tv_description);
    }

    private void initData() {
         id=getIntent().getStringExtra("id");//帮扶id
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    //数据源
    KunNanBangFuDetailsBean.DataBean dataBean=null;

    //获取帮扶详情数据
    private void getData(){
        Map<String ,String> map=new HashMap<>();
        map.put("id",id);
        NetApi.selectSupportById(map,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("获取帮扶详情信息",result);
                KunNanBangFuDetailsBean kunNanBangFuDetailsBean=new Gson().fromJson(result,KunNanBangFuDetailsBean.class);
                if (kunNanBangFuDetailsBean.getData()==null){
                    return;
                }
                dataBean=kunNanBangFuDetailsBean.getData();
                String details=dataBean.getDetails();
                String event=dataBean.getEvent();
                String realName=dataBean.getRealName();
                if (TextUtils.isEmpty(details))details="";
                if (TextUtils.isEmpty(event))event="";
                if (TextUtils.isEmpty(realName))realName="";
                tv_name.setText(realName);
                tv_description.setText(details);
                tv_event.setText(event);
            }

            @Override
            public void onFault(String errorMsg) {
                mmdialog.showError(errorMsg);
            }
        }));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_public_title_right://修改
                if (dataBean==null){
                    mmdialog.showSuccess("系统繁忙请稍后再试,或检查您的网络情况和登录状态");
                    return;
                }
                Intent intent=new Intent(KunNanBangFuShowOneActivity.this,KunNanBangFuAddActivity.class);
                intent.putExtra("flag","1");
                intent.putExtra("data",dataBean);
                startActivity(intent);
                break;
        }
    }
}
