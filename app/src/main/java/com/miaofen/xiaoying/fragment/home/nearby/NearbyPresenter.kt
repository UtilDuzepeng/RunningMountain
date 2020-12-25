package com.miaofen.xiaoying.fragment.home.nearby

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.HomeRequestData
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.nearby
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class NearbyPresenter(view: NearbyContract.View) : BasePresenter<NearbyContract.View>(view),
    NearbyContract.Presenter {

    private val homeRequestData = HomeRequestData()

    override fun doNearby(page: Int, size: Int, latitude: Double, longitude: Double) {
        homeRequestData.setLatitude(latitude)
        homeRequestData.setLongitude(longitude)
        homeRequestData.setPage(page)
        homeRequestData.setSize(size)

        RemoteRepository
            .nearby(homeRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<HomeResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: HomeResponse?) {
                    //下拉刷新 无数据
                    if (page == 1 && (data?.content == null || data.content.size == 0)) {
                        mRootView.get()?.onDownNearbytNullSuccess()
                        return
                    }

                    //下拉刷新 有数据
                    if (page == 1 && data?.content != null && data.content.size > 0) {
                        mRootView.get()?.onDownNearbySuccess(data)
                        return
                    }

                    //上拉加载 无数据
                    if (data?.content == null || data.content.size == 0) {
                        mRootView.get()?.onNearbyNullSuccess()
                        return
                    }
                    //上拉加载 有数据
                    if (data?.content != null && data.content.size > 0) {
                        mRootView.get()?.onNearbySuccess(data)
                    }

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onNearbyError()
                }
            })
    }

}