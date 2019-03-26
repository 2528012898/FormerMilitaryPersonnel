package com.qiye.formermilitaryp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.adapter.MainActivityFragmentAdapter;
import com.qiye.formermilitaryp.base.BaseActivity;
import com.qiye.formermilitaryp.fragment.home.HomeFragment;
import com.qiye.formermilitaryp.fragment.home.MakeAnAppointmentFragment;
import com.qiye.formermilitaryp.fragment.home.MyFragment;
import com.qiye.formermilitaryp.fragment.home.PolicyPublicityFragment;
import com.qiye.formermilitaryp.utils.LogUtils;
import com.qiye.formermilitaryp.utils.SpUtil;
import com.qiye.formermilitaryp.utils.networkRequest2.CookieUtil;
import com.qiye.formermilitaryp.utils.networkRequest2.NetApi;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultListener;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultSub;
import com.qiye.formermilitaryp.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Context context;

    private NoScrollViewPager vp;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    //构建底部按钮大小相关
    private ImageView cursor;
    private int screenWidth;
    private FrameLayout.LayoutParams params;
    private final int extraBorderSize = 0;
    private ViewGroup.LayoutParams rb_pager1_params;

    private ArrayList<Fragment> fragments = new ArrayList<>();

    private MainActivityFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        fullScreen(MainActivity.this);
        initView();
        initData();
    }

    private void initView() {



        vp = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        //构建底部按钮大小相关
        cursor = findViewById(R.id.cursor);
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        //碎片初始化
        LoadFragment();
    }

    /**
     * 初始化fragmentList
     */
    private void LoadFragment() {
        HomeFragment homeFragment = new HomeFragment();
        MakeAnAppointmentFragment makeAnAppointmentFragment = new MakeAnAppointmentFragment();
        PolicyPublicityFragment policyPublicityFragment = new PolicyPublicityFragment();
        MyFragment myFragment = new MyFragment();
        fragments.add(homeFragment);
        fragments.add(makeAnAppointmentFragment);
        fragments.add(policyPublicityFragment);
        fragments.add(myFragment);
        adapter = new MainActivityFragmentAdapter(getSupportFragmentManager(), fragments);
    }

    private void initData() {
        //构建底部按钮大小相关
        setIconSize();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb1:
                        createStatusBarTextIconColorDepth(false);//状态栏文字图标颜色浅色
                        vp.setCurrentItem(0, false);
                        break;
                    case R.id.rb2:
                        createStatusBarTextIconColorDepth(true);//
                        vp.setCurrentItem(1, false);
                        break;
                    case R.id.rb3:
                        createStatusBarTextIconColorDepth(true);//
                        vp.setCurrentItem(2,false);//
                        break;
                    case R.id.rb4:
                        createStatusBarTextIconColorDepth(true);//
                        vp.setCurrentItem(3, false);
                        break;

                }
            }
        });

        //构建底部按钮大小相关
        if (fragments.size() != 0) {
            params.width = screenWidth / fragments.size() + extraBorderSize;
        }

        vp.setOffscreenPageLimit(3);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //构建底部按钮大小相关
                if (fragments.size() != 0) {
                    setCursorOffset((int) (v * screenWidth / fragments.size() + (screenWidth / fragments.size() * i)));
                }
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        rg.check(R.id.rb1);
                        break;
                    case 1:
                        rg.check(R.id.rb2);
                        break;
                    case 2:
                        rg.check(R.id.rb3);
                        break;
                    case 3:
                        rg.check(R.id.rb4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    /**
     * 设置底部标签大小
     */
    private void setIconSize() {
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        int a = width < height ? width : height;
        int icon_size = (int) (a * 0.060);
        Drawable drawable1 = getResources().getDrawable(R.drawable.home_rb1_img_selector);
        drawable1.setBounds(0, 0, icon_size, icon_size);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb1.setCompoundDrawables(null, drawable1, null, null);//只放上面

        Drawable drawable2 = getResources().getDrawable(R.drawable.home_rb2_img_selector);
        drawable2.setBounds(0, 0, icon_size, icon_size);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb2.setCompoundDrawables(null, drawable2, null, null);//只放上面

        Drawable drawable3 = getResources().getDrawable(R.drawable.home_rb3_img_selector);
        drawable3.setBounds(0, 0, icon_size, icon_size);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb3.setCompoundDrawables(null, drawable3, null, null);//只放上面

        Drawable drawable4 = getResources().getDrawable(R.drawable.home_rb4_img_selector);
        drawable4.setBounds(0, 0, icon_size, icon_size);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb4.setCompoundDrawables(null, drawable4, null, null);//只放上面


        params = (FrameLayout.LayoutParams) cursor.getLayoutParams();
        rb_pager1_params = rb1.getLayoutParams();
    }


    //构建底部按钮大小相关
    private void setCursorOffset(int x) {
        params.leftMargin = x - extraBorderSize / 2 + 0;
        cursor.setLayoutParams(params);
    }


    //登录状态的校验
    private void loginStatusCheck(){
        String cookie= SpUtil.spGet(context,SpUtil.storageFlieName,SpUtil.spSaveUserIdKeyName,"");
        if (TextUtils.isEmpty(cookie)){//未登录
            //mmdialog.showSuccess("");
            Intent intent=new Intent(context,LoginActivity.class);
            startActivity(intent);
        }else{

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }





    @Override
    protected void onResume() {
        super.onResume();
        loginStatusCheck();//登录状态的校验
        if (vp.getCurrentItem()==0)createStatusBarTextIconColorDepth(false);//状态栏文字图标颜色浅色
        if (vp.getCurrentItem()!=0)createStatusBarTextIconColorDepth(true);//发现页面状态栏文字...深色

    }
}
