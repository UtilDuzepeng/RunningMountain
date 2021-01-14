package com.miaofen.xiaoying.activity.details.replycomm

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.SubCommentsRequestData
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.replycomm
 * 类描述：一级回复评论网络请求
 * 创建人：duzepeng
 * 创建时间：2021/1/8
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ReplyCommPresenter(view: ReplyCommContract.View) :
    BasePresenter<ReplyCommContract.View>(view), ReplyCommContract.Presenter {

    var subCommentsRequestData = SubCommentsRequestData()
    override fun doReplyComm(commentId: Long) {

        subCommentsRequestData.setCommentId(commentId)

        RemoteRepository
            .onSubComments(subCommentsRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onReplyCommSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onReplyCommError()
                }
            })
    }

}