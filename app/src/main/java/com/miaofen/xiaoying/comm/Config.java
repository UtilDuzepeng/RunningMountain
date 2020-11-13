package com.miaofen.xiaoying.comm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

import com.miaofen.xiaoying.MyApplication;


/**
 * 项目名称：com.miaofen.xiaoying.comm
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class Config {

    public static MyApplication applicationContext;
    public static boolean isDebug = false;
    public static float density;
    public static int screenWidth;// 屏幕的宽  px
    public static int screenHeight;// 屏幕整体高度（包括手机状态栏）px
    public static int versionCode;//当前app版本号
    public static String versionName;//当前app版本名称
    public static String appName;//当前app的名称
    public static String appPackage;//当前app包名


    public Config(MyApplication application) {
        Config.applicationContext = application;

        DisplayMetrics dm = applicationContext.getResources().getDisplayMetrics();
        density = dm.density;
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        //获取当前软件信息
        initPackageInfo();
    }

    private static void initPackageInfo() {
        // 获取packagemanager的实例
        PackageManager packageManager = applicationContext.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(applicationContext.getPackageName(), PackageManager.GET_SIGNATURES);
            versionCode = packInfo.versionCode;
            versionName = packInfo.versionName;
            appName = packInfo.applicationInfo.loadLabel(packageManager).toString();
            appPackage = applicationContext.getPackageName();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

