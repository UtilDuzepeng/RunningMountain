package com.miaofen.xiaoying.activity.location

import com.miaofen.xiaoying.activity.gender.GenderContract
import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.AccessAreaRequestData
import com.miaofen.xiaoying.common.data.bean.request.GenderRequestData
import com.miaofen.xiaoying.common.data.bean.request.ProvinceRequestData
import com.miaofen.xiaoying.common.data.bean.response.PositionResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.location
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/2/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class PositionPresenter(view: PositionContract.View) :
    BasePresenter<PositionContract.View>(view), PositionContract.Presenter {

    var provinceRequestData = ProvinceRequestData()
    override fun doPosition() {
        RemoteRepository
            .onPosition(provinceRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<List<PositionResponse>>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: List<PositionResponse>?) {
                    mRootView.get()?.onPositionSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onPositionError()
                }
            })
    }


    val accessAreaRequestData = AccessAreaRequestData()
    override fun doAccessArea(areaCode: Int) {
        accessAreaRequestData.setAreaCode(areaCode)
        RemoteRepository
            .onAccessArea(accessAreaRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<List<PositionResponse>>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: List<PositionResponse>?) {
                    if (data == null || data.isEmpty()) {
                        mRootView.get()?.onAccessAreaSuccess()
                        return
                    }
                    mRootView.get()?.onAccessAreaSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onAccessAreaError()
                }
            })
    }


}