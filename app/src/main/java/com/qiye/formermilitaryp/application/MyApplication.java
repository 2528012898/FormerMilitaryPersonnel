package com.qiye.formermilitaryp.application;

import android.app.Application;
import android.content.Context;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

public class MyApplication extends Application {
    public static Context appContext;
    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        //网路请求框架初始化
        appContext = getApplicationContext();
        app = this;
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }


    public static MyApplication getApp() {
        return app;
    }


}