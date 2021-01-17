package com.miaofen.xiaoying.fragment.home.hottest


import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.HomeRequestData
import com.miaofen.xiaoying.common.data.bean.request.TravelPlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.hottest
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class NewestPresenter(view: NewestContract.View) : BasePresenter<NewestContract.View>(view),
    NewestContract.Presenter {

    private val homeRequestData = HomeRequestData()

    override fun doNewest(page: Int, size: Int, latitude: Double, longitude: Double) {

        homeRequestData.setLatitude(latitude)
        homeRequestData.setLongitude(longitude)
        homeRequestData.setPage(page)
        homeRequestData.setSize(size)

        RemoteRepository
            .newest(homeRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<HomeResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: HomeResponse?) {

                    //下拉刷新 无数据
                    if (page == 1 && (data?.content == null || data.content.size == 0)) {
                        mRootView.get()?.onDownNewestNullSuccess()
                        return
                    }

                    //下拉刷新 有数据
                    if (page == 1 && data?.content != null && data.content.size > 0) {
                        mRootView.get()?.onDownNewestSuccess(data)
                        return
                    }

                    //上拉加载 无数据
                    if (data?.content == null || data.content.size == 0) {
                        mRootView.get()?.onNewestNullSuccess()
                        return
                    }

                    //上拉加载 有数据
                    if (data?.content != null && data.content.size > 0) {
                        mRootView.get()?.onNewestSuccess(data)
                    }


                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onNewestError()
                }
            })
    }

    /**
     * 收藏
     */
    var travelPlanRequestData = TravelPlanRequestData()
    override fun doCollection(entityId: Int?) {
        travelPlanRequestData.setEntityId(entityId!!)
        RemoteRepository
            .onCollection(travelPlanRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onCollectionSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onCollectionError()
                }
            })
    }

    //取消收藏
    var cancelCollectionRequestData = TravelPlanRequestData()
    override fun doCancelCollection(entityId: Int?) {

        cancelCollectionRequestData.setEntityId(entityId!!)
        RemoteRepository
            .onCancelCollection(cancelCollectionRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onCancelCollectionSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onCancelCollectionError()
                }
            })
    }

}