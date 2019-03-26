package com.qiye.formermilitaryp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.github.ikidou.fragmentBackHandler.BackHandledFragment;
import com.qiye.formermilitaryp.utils.PictureUtils;

import me.leefeng.promptlibrary.PromptDialog;

public class BaseFragment extends BackHandledFragment {


    public PromptDialog mmdialog;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //创建对象
        mmdialog = new PromptDialog(getActivity());
        //设置自定义属性
        mmdialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);


    }

    //获取手机状态栏的高度,返回px
    public int getStatusBarHeight() {
        int statusBarHeight = PictureUtils.dpConversionPx(getActivity(),23);//23dp 转化为 px
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


}
