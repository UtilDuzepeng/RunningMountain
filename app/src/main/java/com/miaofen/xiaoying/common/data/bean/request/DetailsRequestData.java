package com.miaofen.xiaoying.common.data.bean.request;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class DetailsRequestData {

    private int planId;

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "DetailsRequestData{" +
                "planId=" + planId +
                '}';
    }
}
