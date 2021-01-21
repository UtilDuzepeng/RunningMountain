package com.miaofen.xiaoying.activity.details.replycomm

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.CancelAttentionRequestData
import com.miaofen.xiaoying.common.data.bean.request.DeleteCommentRequestData
import com.miaofen.xiaoying.common.data.bean.request.FabulousRequestData
import com.miaofen.xiaoying.common.data.bean.request.SubCommentsRequestData
import com.miaofen.xiaoying.common.data.bean.response.SecondaryReplyResponse
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
            .subscribe(object : CommonObserver<SecondaryReplyResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: SecondaryReplyResponse?) {
                    mRootView.get()?.onReplyCommSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onReplyCommError()
                }
            })
    }

    /*—————————————————————————————删除评论———————————————————————————————*/
    private val deleteCommentRequestData = DeleteCommentRequestData()

    override fun doDeleteComment(commentId: Long?) {
        deleteCommentRequestData.setCommentId(commentId)
        RemoteRepository
            .onDeleteComment(deleteCommentRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
//                    if (data == null) {
//                        return
//                    }
                    mRootView.get()?.onDeleteCommentSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onDeleteCommentError()
                }
            })
    }


    /*———————————————————————点赞评论—————————————————————————*/
    private val fabulousRequestData = FabulousRequestData()
    override fun doFabulous(commentId: Long?) {
        fabulousRequestData.setCommentId(commentId)
        RemoteRepository
            .onFabulous(fabulousRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onFabulousSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onFabulousError()
                }
            })
    }

    //取消点赞评论
    private val unStarRequestData = FabulousRequestData()
    override fun doUnStar(commentId: Long?) {
        unStarRequestData.setCommentId(commentId)
        RemoteRepository
            .onUnStar(unStarRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onUnStarSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onUnStarError()
                }
            })

    }

    //取消关注
    private var cancelAttentionRequestData = CancelAttentionRequestData()
    override fun doCancelAttention(cancelFollowId: Int) {
        cancelAttentionRequestData.setCancelFollowId(cancelFollowId)
        RemoteRepository
            .onCancelAttention(cancelAttentionRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onCancelAttentionSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onCancelAttentionError()
                }
            })

    }


}