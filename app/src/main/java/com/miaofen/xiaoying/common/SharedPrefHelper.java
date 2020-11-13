package com.miaofen.xiaoying.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import com.miaofen.xiaoying.comm.Config;
import com.miaofen.xiaoying.common.data.bean.response.LoginBean;
import com.miaofen.xiaoying.utils.GsonProvider;
import com.miaofen.xiaoying.utils.StringUtil;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.local
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SharedPrefHelper {


    private static final String TAG_SETTING = "tag_setting";
    private static final String User_Name = "UserName";
    private static final String User_Password = "UserPassword";

    //保存注册信息
    public static final String REGISTRATION = "Registrationinformation";
    //保存二维码模板信息
    public static final String TEMPLATE = "Savetemplateinformation";

    // 用户登录信息
    private static final String LOGIN_INFO = "loginInfo";

    //token
    private static final String LOGIN_TOKEN ="logintoken";

    // 获取SharedPreferences
    protected static SharedPreferences getSP(String tag) {
        return Config.applicationContext.getSharedPreferences(tag, Context.MODE_PRIVATE);
    }


    // 获取SharedPreferences的Editor
    protected static SharedPreferences.Editor getSPEditor(String tag) {
        return Config.applicationContext.getSharedPreferences(tag, Context.MODE_PRIVATE).edit();
    }


    //保存用户名密码
    public static void saveUser(String userName, String password) {
        SharedPreferences.Editor editor = getSPEditor(TAG_SETTING);
        if (StringUtil.isNull(userName)) {
            //清空用户名密码
            editor.remove(User_Name);
            editor.remove(User_Password);
            editor.commit();
        } else {
            editor.putString(User_Name, userName);
            if (!StringUtil.isNull(password)) {
                editor.putString(User_Password, password);
            }
            editor.commit();
        }
    }

    //获取用户名密码
    public static Pair<String, String> getUserNameAndPass() {
        return new Pair<>(getSP(TAG_SETTING).getString(User_Name, ""),
                getSP(TAG_SETTING).getString(User_Password, ""));
    }

    //保存注册信息
//    public static void saveRegistrationInformation(RegisterInfo info) {
//        String jsonStr = GsonProvider.gson().toJson(info); //将List转换成Json
//        SharedPreferences.Editor editor = getSPEditor(TAG_SETTING);
//        editor.remove(REGISTRATION);//注册信息
//        editor.commit();
//
//        editor.putString(REGISTRATION, jsonStr);//注册信息
//        editor.commit();
//    }

    //获取注册信息
//    public static @Nullable
//    RegisterInfo getRegisterInfo() {
//        String json = getSP(TAG_SETTING).getString(REGISTRATION, "");
//        try {
//            return GsonProvider.gson().fromJson(json, RegisterInfo.class);
//        } catch (Exception e) {
//            return null;
//        }
//    }

    // 清空本地保存的注册信息
    public static void clearRegisterInfo() {
        SharedPreferences.Editor editor = getSPEditor(TAG_SETTING);
        editor.remove(REGISTRATION);
        editor.commit();
    }

    //保存模板信息
    public static void saveTemplateInformation(String template) {
        SharedPreferences.Editor editor = getSPEditor(TAG_SETTING);
        editor.remove(TEMPLATE);//模板信息
        editor.commit();

        editor.putString(TEMPLATE, template);//模板信息
        editor.commit();
    }

    //获取模板信息
    public static String getTemplate() {
        String json = getSP(TAG_SETTING).getString(TEMPLATE, "");
        return json;
    }

    public static void saveLoginInfo(LoginBean loginInfo) {
        final SharedPreferences.Editor editor = getSPEditor(TAG_SETTING);
        editor.remove(LOGIN_INFO);

        editor.putString(LOGIN_INFO, GsonProvider.gson().toJson(loginInfo));
        editor.apply();
    }

    public static void clearLoginInfo() {
        final SharedPreferences.Editor editor = getSPEditor(TAG_SETTING);
        editor.remove(LOGIN_INFO).apply();
    }

    public static LoginBean getLoginInfo() {
        final String loginInfoJson = getSP(TAG_SETTING).getString(LOGIN_INFO, "");
        try {
            return GsonProvider.gson().fromJson(loginInfoJson, LoginBean.class);
        } catch (Exception e) {
            return null;
        }
    }

    //保持token
    public static void saveTokenData(String tokenData) {
        final SharedPreferences.Editor editor = getSPEditor(TAG_SETTING);
        editor.remove(LOGIN_TOKEN);

        editor.putString(LOGIN_TOKEN, GsonProvider.gson().toJson(tokenData));
        editor.apply();
    }

    //获取toke
    public static String getTokenData() {
        final String token = getSP(TAG_SETTING).getString(LOGIN_TOKEN, "");
        try {
            return GsonProvider.gson().fromJson(token, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static void clearTokenData() {
        final SharedPreferences.Editor editor = getSPEditor(TAG_SETTING);
        editor.remove(LOGIN_TOKEN).apply();
    }

}

