package com.miaofen.xiaoying.fragment.home

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.fragment.homefragment
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class RotationChartPresenter(view:RotationChartContract.View):BasePresenter<RotationChartContract.View>(view),RotationChartContract.Presenter {

    override fun doRotationChart() {
        RemoteRepository
            .rotationChart()
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {

                    if (data == null) {
                        return
                    }
                    mRootView.get()?.onRotationChartSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onRotationChartError()
                }
            })
    }

}