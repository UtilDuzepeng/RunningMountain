package com.miaofen.xiaoying.activity.travelplan.participate

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.ParticipateRequestData
import com.miaofen.xiaoying.common.data.bean.response.ParticipateResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.travelplan.participate
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/24/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ParticipatePresenter (view: ParticipateContract.View) :
    BasePresenter<ParticipateContract.View>(view), ParticipateContract.Presenter {

    private val participateRequestData = ParticipateRequestData()
    override fun doParticipate(page: Int, size: Int) {
        participateRequestData.setPage(page)
        participateRequestData.setSize(size)
        RemoteRepository
            .onParticipate(participateRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<ParticipateResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: ParticipateResponse?) {

                    //下拉刷新 无数据
                    if (page == 1 && (data?.content == null || data.content!!.isEmpty())) {
                        mRootView.get()?.onParticipateNullSuccess()
                        return
                    }

                    //下拉刷新 有数据
                    if (page == 1 && data?.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onParticipateSuccess(data)
                        return
                    }

                    //上拉加载 无数据
                    if (data?.content == null || data.content!!.isEmpty()) {
                        mRootView.get()?.onParticipatePullNullSuccess()
                        return
                    }

                    //上拉加载 有数据
                    if (data.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onParticipatePullSuccess(data)
                    }

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onParticipateError()
                }
            })
    }

}