package com.miaofen.xiaoying.utils;

import android.content.Context;


import org.json.JSONException;
import org.json.JSONObject;



import static com.miaofen.xiaoying.comm.Constant.JSON_ADDRESS;

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：基础定位
 * 创建人：duzepeng
 * 创建时间：2020/12/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class JsonTestUtils {

    private String city = "北京市"; //城市
    private String district = "朝阳区";//区
    private double lat = 36.2;//经度
    private double lon = 116.32;//纬度
    private JSONObject jsonObject = new JSONObject();

    public JsonTestUtils(Context context) {
        toJson(context);
    }

    private void toJson(Context context) {
        try {
            jsonObject.put("city", city);
            jsonObject.put("district", district);
            jsonObject.put("lat", lat);
            jsonObject.put("lon", lon);
            if (CacheUtils.readJson(context, JSON_ADDRESS) == null) {
                CacheUtils.writeJson(context, jsonObject.toString(), JSON_ADDRESS, true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
