package com.miaofen.xiaoying.common.data.bean.request;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：修改地区
 * 创建人：duzepeng
 * 创建时间：2/21/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class RevisionAreaRequestData {

    private String province;
    private String city;

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
