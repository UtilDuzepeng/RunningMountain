package com.miaofen.xiaoying.activity.travelplan.ipost

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.IPostRequestData
import com.miaofen.xiaoying.common.data.bean.response.IPostResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.travelplan.ipost
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/24/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class IPostPresenter (view: IPostContract.View) :
    BasePresenter<IPostContract.View>(view), IPostContract.Presenter {

    private val iPostRequestData = IPostRequestData()
    override fun doIPost(page: Int, size: Int) {
        iPostRequestData.setPage(page)
        iPostRequestData.setSize(size)
        RemoteRepository
            .onIPost(iPostRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<IPostResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: IPostResponse?) {

                    //下拉刷新 无数据
                    if (page == 1 && (data?.content == null || data.content!!.isEmpty())) {
                        mRootView.get()?.onIPostNullSuccess()
                        return
                    }

                    //下拉刷新 有数据
                    if (page == 1 && data?.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onIPostSuccess(data)
                        return
                    }

                    //上拉加载 无数据
                    if (data?.content == null || data.content!!.isEmpty()) {
                        mRootView.get()?.onIPostPullNullSuccess()
                        return
                    }

                    //上拉加载 有数据
                    if (data.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onIPostPullSuccess(data)
                    }

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onIPostError()
                }
            })
    }

}