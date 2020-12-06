package com.miaofen.xiaoying;

import android.app.Application;
import android.content.Context;

import com.miaofen.xiaoying.comm.CommonAppDelegate;

/**
 * 项目名称：com.miaofen.xiaoying
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        CommonAppDelegate.init(this);
        mContext = this;
    }
}
