package com.miaofen.xiaoying.common.data.bean.request;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/24/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class IPostRequestData {
    private int page;
    private int size;

    public void setSize(int size) {
        this.size = size;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
