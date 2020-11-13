package com.miaofen.xiaoying.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.miaofen.xiaoying.comm.CommonAppDelegate;

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ToastUtils {
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;
    public static void showToast(String s) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(CommonAppDelegate.getContext(), s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;
    }
    public static void showToast(int resId) {
        showToast(CommonAppDelegate.getContext().getString(resId));
    }
}

