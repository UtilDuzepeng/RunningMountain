package com.miaofen.xiaoying.common.data.bean.request;


/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SignUpPlanRequestData {
    private int planId;//id
    private String contactWay;//联系方式
    private String remark;//备注

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
