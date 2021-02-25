package com.miaofen.xiaoying.common.data.bean.request;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：用户对外展示旅行计划
 * 创建人：duzepeng
 * 创建时间：2021/1/31
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ExternalDisplayRequestData {

    private Long userId;
    private int page;
    private int size;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
