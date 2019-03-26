package com.qiye.formermilitaryp.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiye.formermilitaryp.utils.ActivityCollector;
import com.qiye.formermilitaryp.utils.PictureUtils;

import me.leefeng.promptlibrary.PromptDialog;

public class BaseActivity extends AppCompatActivity {
    public PromptDialog mmdialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);

        //创建对象
        mmdialog = new PromptDialog(this);
        //设置自定义属性
        mmdialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
    }

    /**
     * 通过设置全屏，设置状态栏透明
     */
    public void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                View decorView = window.getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //window.setNavigationBarColor(Color.TRANSPARENT);//导航栏颜色也可以正常设置
            } else {
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                //attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    //关闭页面
    public void click_finishActivity(View view){
        finish();
    }

    //tv控件设置文本
    public void tvSetText(String text, TextView textView){
        if (text==null)text="";
        textView.setText(text);
    }

    //获取手机状态栏的高度,返回px
    public int getStatusBarHeight() {
        int statusBarHeight = PictureUtils.dpConversionPx(BaseActivity.this,23);//23dp 转化为 px
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");//获取status_bar_height资源的ID
        if (resourceId > 0)
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);       //根据资源ID获取响应的尺寸值
        //statusBarHeight = px2dip(getApplicationContext(), new Float(statusBarHeight));//px 值 转换成 dp
        return statusBarHeight;
    }

    //构建自定义状态栏的高度
    public void createV_statusBarHeight(View v_statusBar){
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) v_statusBar.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = getStatusBarHeight();// 控件的高强制设成xx px 单位
        v_statusBar.setLayoutParams(linearParams);
    }

    //findViewById代码冗余的优化
    public <A> A f(int viewID) {
        return (A) findViewById(viewID);
    }

    //状态栏的文字颜色图标颜色深度,isDeepColour true 深 false 浅
    public void createStatusBarTextIconColorDepth(boolean isDeepColour){
        if (isDeepColour){
            //设置状态栏文字颜色及图标为深色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }else {
            //设置状态栏文字颜色及图标为浅色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
