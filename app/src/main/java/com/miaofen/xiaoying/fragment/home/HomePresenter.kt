package com.miaofen.xiaoying.fragment.home

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.RecommendRequestData
import com.miaofen.xiaoying.common.data.bean.request.RotationChartRequestData
import com.miaofen.xiaoying.common.data.bean.response.BannerResponse
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

class HomePresenter(view:HomeContract.View):BasePresenter<HomeContract.View>(view),HomeContract.Presenter {

    /**
     * 轮播图
     */
    private val rotationChartRequestData = RotationChartRequestData()
    override fun doRotationChart() {
        RemoteRepository
            .rotationChart(rotationChartRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<List<BannerResponse>>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: List<BannerResponse>?) {

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

    /**
     * 热门推荐
     */
    private val recommendRequestData = RecommendRequestData()
    override fun recommend() {
        RemoteRepository
            .recommend(recommendRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<ArrayList<String>>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: ArrayList<String>?) {

                    if (data == null) {
                        return
                    }
                    mRootView.get()?.onRecommendSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onRecommendError()
                }
            })
    }

}