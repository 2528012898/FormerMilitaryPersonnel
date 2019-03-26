package com.qiye.formermilitaryp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.base.BaseActivity;

/**
 * 启动页面
 */
public class StartPageActivity extends BaseActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        fullScreen(StartPageActivity.this);
        context=StartPageActivity.this;
        initView();
    }
    private void initView() {
        startActivity(new Intent(context,MainActivity.class));
        finish();
    }
}
