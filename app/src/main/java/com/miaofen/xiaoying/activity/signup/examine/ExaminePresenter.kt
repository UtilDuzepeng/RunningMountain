package com.miaofen.xiaoying.activity.signup.examine

import com.miaofen.xiaoying.activity.details.commdia.CommentContract
import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.CancelRequestData
import com.miaofen.xiaoying.common.data.bean.request.ExamineRequestData
import com.miaofen.xiaoying.common.data.bean.request.PassRequestData
import com.miaofen.xiaoying.common.data.bean.response.ExamineResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.signup.examine
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ExaminePresenter(view: ExamineContract.View) :
    BasePresenter<ExamineContract.View>(view), ExamineContract.Presenter {

    var examineRequestData = ExamineRequestData()
    override fun doExamine(planId: Int) {
        examineRequestData.setPlanId(planId)
        RemoteRepository
            .onExamine(examineRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<List<ExamineResponse>>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: List<ExamineResponse>?) {
                    mRootView.get()?.onExamineSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onExamineError()
                }
            })
    }

    var passRequestData = PassRequestData()
    override fun onPass(joinId: Int?, planId: Int?) {
        passRequestData.setJoinId(joinId!!)
        passRequestData.setJoinId(planId!!)
        RemoteRepository
            .onPass(passRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onPassSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onPassError()
                }
            })
    }


    var cancelRequestData = CancelRequestData()
    override fun onRefuse(joinId: Int?, planId: Int?, reason: String?) {
        cancelRequestData.setJoinId(joinId!!)
        cancelRequestData.setPlanId(planId!!)
        cancelRequestData.setReason(reason)
        RemoteRepository
            .onRefuse(cancelRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onRefuseSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onRefuseError()
                }
            })
    }

}