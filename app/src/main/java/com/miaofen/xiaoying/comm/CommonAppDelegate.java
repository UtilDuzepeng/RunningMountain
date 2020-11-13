package com.miaofen.xiaoying.comm;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称：com.miaofen.xiaoying.comm
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class CommonAppDelegate {

    private static Application sContext;

    public static void init(Application appContext) {
        sContext = appContext;
    }

    public static Context getContext(){
        if (sContext == null) {
            throw new RuntimeException("Did you call CommonAppDelegate.init() to init common lib?");
        }
        return sContext;
    }
}


