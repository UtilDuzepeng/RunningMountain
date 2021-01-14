package com.miaofen.xiaoying.common.data.bean.request;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：发布评论请求类
 * 创建人：duzepeng
 * 创建时间：2021/1/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class PubCommentRequestData {
    private long replyCommentId;//回复的ID（为null或0则为发布1级评论）
    private int planId;//计划ID
    private String content;//内容

    public void setReplyCommentId(long replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
