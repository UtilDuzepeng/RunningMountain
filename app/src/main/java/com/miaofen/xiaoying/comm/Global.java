package com.miaofen.xiaoying.comm;

import com.miaofen.xiaoying.common.data.bean.response.LoginBean;
import com.miaofen.xiaoying.common.SharedPrefHelper;

/**
 * 项目名称：com.miaofen.xiaoying.comm
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class Global {
    private static LoginBean user;

    private static String tokenData;

    public static String getToken() {
        if (tokenData == null) {
            tokenData = SharedPrefHelper.getTokenData();
        }
        return tokenData;
    }


    public static void setToken(String token) {
        Global.tokenData = token;
        if (tokenData == null) {
            SharedPrefHelper.clearTokenData();
        } else {
            SharedPrefHelper.saveTokenData(token);
        }
    }


    public static LoginBean getUser() {
        if (user == null) {
            user = SharedPrefHelper.getLoginInfo();
        }
        return user;
    }

    public static void setUser(LoginBean user) {
        Global.user = user;
        if (user == null) {
            SharedPrefHelper.clearLoginInfo();
        } else {
            SharedPrefHelper.saveLoginInfo(user);
        }
    }




}
