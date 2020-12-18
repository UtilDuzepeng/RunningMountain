package com.miaofen.xiaoying.common.data.bean.request;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：首页列表请求
 * 创建人：duzepeng
 * 创建时间：2020/12/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class HomeRequestData {
    private double latitude;
    private double longitude;
    private int page;
    private int size;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "HomeRequestData{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
