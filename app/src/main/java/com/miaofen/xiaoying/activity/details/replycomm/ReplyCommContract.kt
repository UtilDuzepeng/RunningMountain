package com.miaofen.xiaoying.activity.details.replycomm

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.SecondaryReplyResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.replycomm
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/8
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ReplyCommContract {

    interface Presenter : IPresenter {
        fun doReplyComm(commentId: Long)
        fun doDeleteComment(commentId: Long?)//删除评论
        fun doFabulous(commentId: Long?)//点赞评论
        fun doUnStar(commentId: Long?)//取消点赞
        fun doCancelAttention(cancelFollowId: Long)//取消关注
    }

    interface View : IView<Presenter> {
        fun onReplyCommSuccess(data: SecondaryReplyResponse?)
        fun onReplyCommError()

        /*——————————————————————————删除评论成功————————————————————————————*/
        fun onDeleteCommentSuccess(data: String?)
        fun onDeleteCommentError()

        /*———————————————————————点赞评论————————————————————————————————*/
        fun onFabulousSuccess(data: String?)
        fun onFabulousError()

        /*————————————————————取消评论点赞——————————————————————————*/
        fun onUnStarSuccess(data: String?)
        fun onUnStarError()

        /*————————————————————取消关注——————————————————————————*/
        fun onCancelAttentionSuccess(data: Boolean?)
        fun onCancelAttentionError()


    }
}