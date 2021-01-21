package com.miaofen.xiaoying.activity.details.commdia

import com.miaofen.xiaoying.activity.details.ProjectDetailsContract
import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.PubCommentRequestData
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.commdia
 * 类描述：请求评论
 * 创建人：duzepeng
 * 创建时间：2020/12/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class CommentPresenter (view: CommentContract.View) :
    BasePresenter<CommentContract.View>(view), CommentContract.Presenter{


    var pubCommentRequestData: PubCommentRequestData = PubCommentRequestData()
    override fun doCommentDialog(replyCommentId : Long,planId: Int, content: String) {

        if (content.isEmpty()){
            ToastUtils.showToast("发布内容为空")
            return
        }
        pubCommentRequestData.setReplyCommentId(replyCommentId)
        pubCommentRequestData.setPlanId(planId)
        pubCommentRequestData.setContent(content)
        RemoteRepository
            .onPubComment(pubCommentRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onCommentSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onCommentError()
                }
            })
    }

}