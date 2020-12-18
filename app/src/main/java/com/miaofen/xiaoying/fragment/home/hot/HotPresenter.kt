package com.miaofen.xiaoying.fragment.home.hot

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.HomeRequestData
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.hot
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class HotPresenter(view: HotContract.View) : BasePresenter<HotContract.View>(view),
    HotContract.Presenter {

    private val homeRequestData = HomeRequestData()

    override fun doHot(page: Int, size: Int) {

        homeRequestData.setLatitude(36.2)
        homeRequestData.setLongitude(116.32)
        homeRequestData.setPage(page)
        homeRequestData.setSize(size)

        RemoteRepository
            .hot(homeRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<HomeResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: HomeResponse?) {
//                    if (data == null) {
//                        return
//                    }
                    mRootView.get()?.onHotSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onHotError()
                }
            })
    }

}