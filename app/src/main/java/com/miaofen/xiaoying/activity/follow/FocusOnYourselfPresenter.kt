package com.miaofen.xiaoying.activity.follow

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.CollectionRequestData
import com.miaofen.xiaoying.common.data.bean.response.FocusOnResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.follow
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class FocusOnYourselfPresenter(view: FocusOnYourselfContract.View) :
    BasePresenter<FocusOnYourselfContract.View>(view), FocusOnYourselfContract.Presenter {

    private var focusOnYourselfRequestData = CollectionRequestData()

    override fun doFocusOnYourself(page: Int, size: Int) {
        focusOnYourselfRequestData.setPage(page)
        focusOnYourselfRequestData.setSize(size)
        RemoteRepository
            .onFocusOn(focusOnYourselfRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<FocusOnResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: FocusOnResponse?) {

                    //下拉刷新 无数据
                    if (page == 1 && (data?.content == null || data.content!!.isEmpty())) {
                        mRootView.get()?.onFocusOnYourselfNullSuccess()
                        return
                    }

                    //下拉刷新 有数据
                    if (page == 1 && data?.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onFocusOnYourselfSuccess(data)
                        return
                    }

                    //上拉加载 无数据
                    if (data?.content == null || data.content!!.isEmpty()) {
                        mRootView.get()?.onFocusOnNullSuccess()
                        return
                    }

                    //上拉加载 有数据
                    if (data.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onFocusOnSuccess(data)
                    }

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onFocusOnError()
                }
            })
    }

}