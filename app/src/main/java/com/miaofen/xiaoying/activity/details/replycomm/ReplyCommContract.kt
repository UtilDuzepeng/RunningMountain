package com.miaofen.xiaoying.activity.details.replycomm

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView

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
    }

    interface View : IView<Presenter> {
        fun onReplyCommSuccess(data: String?)
        fun onReplyCommError()
    }
}