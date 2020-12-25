package com.miaofen.xiaoying.common.data.bean.request;

import mlxy.utils.S;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：搜索计划请求类
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class PlanRequestData {

    private String keyword;
    private int page;
    private int size;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
