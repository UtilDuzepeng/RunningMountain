package com.miaofen.xiaoying.activity.signup.refuse

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.RefuseRequestData
import com.miaofen.xiaoying.common.data.bean.response.RefuseResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.signup.refuse
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class RefusePresenter(view: RefuseContract.View) :
    BasePresenter<RefuseContract.View>(view), RefuseContract.Presenter {

    var refuseRequestData = RefuseRequestData()

    override fun doRefuse(planId: Int) {
        refuseRequestData.setPlanId(planId)
        RemoteRepository
            .onRefuse(refuseRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<List<RefuseResponse>>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: List<RefuseResponse>?) {
                    mRootView.get()?.onRefuseSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onRefuseError()
                }
            })
    }

}