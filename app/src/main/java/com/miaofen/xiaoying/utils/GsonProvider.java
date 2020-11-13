package com.miaofen.xiaoying.utils;

import com.google.gson.Gson;

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class GsonProvider {

    public static Gson gson(){
        return GsonHolder.singleton;
    }

    private static class GsonHolder {
        private static final Gson singleton = new Gson();
    }
}


