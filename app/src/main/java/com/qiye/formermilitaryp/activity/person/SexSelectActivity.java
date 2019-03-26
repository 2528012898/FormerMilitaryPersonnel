package com.qiye.formermilitaryp.activity.person;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qiye.formermilitaryp.R;

/**
 * 性别选择
 */
public class SexSelectActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_select);

        findViewById(R.id.tv1).setOnClickListener(this);
        findViewById(R.id.tv2).setOnClickListener(this);
        findViewById(R.id.tv3).setOnClickListener(this);
    }

    //页面成功响应码
    public static final int pageSuccessResponseCode = 4869;

    //页面响应携带文本的键
    public final static String pageResponseTextKeyName="sex";

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1://男
                Intent intent = new Intent();
                intent.putExtra(pageResponseTextKeyName,"男");
                setResult(pageSuccessResponseCode,intent);
                finish();
                break;
            case R.id.tv2://女
                 intent = new Intent();
                intent.putExtra(pageResponseTextKeyName,"女");
                setResult(pageSuccessResponseCode,intent);
                finish();
                break;
            case R.id.tv3:
                finish();
                break;
        }
    }
}
