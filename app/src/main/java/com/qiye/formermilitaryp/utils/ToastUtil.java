package com.qiye.formermilitaryp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void showLong(Context context,String toastStr){
        if (toastStr==null)return;
        Toast.makeText(context, toastStr, Toast.LENGTH_SHORT).show();
    }
}
