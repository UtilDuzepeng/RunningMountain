package com.miaofen.xiaoying.common.data.bean.request;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：旅行计划详情一级评论
 * 创建人：duzepeng
 * 创建时间：2020/12/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class OneCommentsData {
    private int planId;
    private int page;
    private int size;

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
