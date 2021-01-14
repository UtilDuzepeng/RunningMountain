package com.miaofen.xiaoying.activity.details.commdia

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.commdia
 * 类描述：评论接口
 * 创建人：duzepeng
 * 创建时间：2020/12/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface CommentContract : IPresenter {

    interface Presenter : IPresenter {
        fun doCommentDialog(planId: Int, content: String)
    }

    interface View : IView<Presenter> {
        fun onCommentSuccess(data: String?)
        fun onCommentError()
    }
}