package com.miaofen.xiaoying.common.data.bean.request;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：计划发布者拒绝报名请求类
 * 创建人：duzepeng
 * 创建时间：2021/1/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class CancelRequestData {
    private int joinId;
    private int planId;
    private String reason;

    public void setJoinId(int joinId) {
        this.joinId = joinId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
