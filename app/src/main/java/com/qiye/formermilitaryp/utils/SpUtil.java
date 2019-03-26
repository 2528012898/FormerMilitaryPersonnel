package com.qiye.formermilitaryp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 存储工具类 方法参数不能传null值
 * 可以将方法放在activity父类中方便调用
 */
public class SpUtil {
    private static SharedPreferences sp;//声明Sharedpreferenced对象
    public static final String storageFlieName = "formerMilitaryPersonnel";
    public static final String spSaveUserIdKeyName = "userId";// 用于存储用户的身份,用于校验用户的登录状态
    public static final String spSaveUserNameKeyName = "userName";//
    public static final String spSaveUserTelKeyName = "userTel";//

    //SharedPreferences 存储
    public static void spSave(Context context, String storageFlieName, String keyName, String value) {
        sp = context.getSharedPreferences(storageFlieName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit(); //获取到edit对象
        edit.putString(keyName, value);            //通过editor对象写入数据
        edit.commit();                             //提交数据存入到xml文件中
    }

    //SharedPreferences 获取
    public static String spGet(Context context, String storageFlieName, String keyName, String giveTacitConsentToValue) {
        sp = context.getSharedPreferences(storageFlieName, Context.MODE_PRIVATE);
        String value = sp.getString(keyName, giveTacitConsentToValue);//取出数据,第一个参数是写入是的键，第二个参数是如果没有获取到数据就默认返回的值。
        return value;
    }
}
