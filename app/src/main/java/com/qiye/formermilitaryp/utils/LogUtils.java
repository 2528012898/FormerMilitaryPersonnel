package com.qiye.formermilitaryp.utils;

import android.util.Log;

/**
 * Log print Tools class create 18.11.16
 * 方法最好放在父activity中,方便用于调用
 */
public class LogUtils {
    private static final boolean WhetherOpenLog = true;//日记是否打开,true : 本类日记可用,false : 本类日记不可用

    /**
     * Log.e
     */

    //Log.e("debug",""+param3)
    public static void print_e(String param3) {
        if (!WhetherOpenLog) return;//日记可用控制
        Log.e("debug", "" + param3);
    }

    //Log.e("debug",param2+""+param3)
    public static void print_e(String param2, String param3) {
        if (!WhetherOpenLog) return;//日记可用控制
        Log.e("debug", param2 + ":" + param3);
    }

    //Log.e(""+param1,""+param3)
    public static void print_e2(String param1, String param3) {
        if (!WhetherOpenLog) return;//日记可用控制
        Log.e("" + param1, "" + param3);
    }

    //Log.e(""+param1,param2+":"+param3)
    public static void print_e(String param1, String param2, String param3) {
        if (!WhetherOpenLog) return;//日记可用控制
        Log.e("" + param1, param2 + ":" + param3);
    }

    /**
     * log.d
     */
    public static void print_d(String param3) {

    }

}
