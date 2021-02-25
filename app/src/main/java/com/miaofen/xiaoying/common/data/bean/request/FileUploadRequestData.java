package com.miaofen.xiaoying.common.data.bean.request;

import android.util.Log;

import com.miaofen.xiaoying.utils.Sha256;

import java.io.File;

import mlxy.utils.S;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/2/8
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class FileUploadRequestData {

    private File file;
    private String type;
    private String sign;

    private StringBuffer stringBuffer = new StringBuffer();

    public void setFile(File file) {
        this.file = file;
    }

    public void setType(String type) {
        switch (type) {
            case "TRAVEL_PLAN_IMAGES"://旅行计划
                this.type = "travelPlanImages";
                break;
            case "USER_AVATAR"://用户头像
                this.type = "userAvatar";
                break;
            case "USER_FEEDBACK"://用户反馈
                this.type = "userFeedback";
                break;
            case "MOTORCYCLE_CERTIFICATION"://用户认证
                this.type = "motorcycleCertification";
                break;
        }

    }

    public void setSign() {
        if (type != null && file != null){
            stringBuffer.delete(0,stringBuffer.length());
            stringBuffer.append(getFile().getName());
            stringBuffer.append("&");
            stringBuffer.append(getType());
            stringBuffer.append("&");
            stringBuffer.append("^wmL@M#$^O&apkkh");
            Log.e("TAG","加密前  ： " + stringBuffer.toString());
//            this.sign = Sha256.getSHA256(getFile().getName()+"&"+getType()+"&"+"^wmL@M#$^O&apkkh");
            this.sign = Sha256.getSHA256(stringBuffer.toString());
            Log.e("TAG","加密后  ： " + sign);
        }

    }

    public File getFile() {
        return file;
    }

    public String getSign() {
        return sign;
    }

    public String getType() {
        return type;
    }

}
