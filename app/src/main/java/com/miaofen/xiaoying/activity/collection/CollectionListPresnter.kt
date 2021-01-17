package com.miaofen.xiaoying.activity.collection

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.CollectionRequestData
import com.miaofen.xiaoying.common.data.bean.response.CollectionListResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.collection
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class CollectionListPresnter (view: CollectionListContract.View) :
    BasePresenter<CollectionListContract.View>(view), CollectionListContract.Presenter {

    private var collectionRequestData = CollectionRequestData()

    override fun doCollectionList(page: Int, size: Int) {
        collectionRequestData.setPage(page)
        collectionRequestData.setSize(size)
        RemoteRepository
            .onCollectionList(collectionRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<CollectionListResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: CollectionListResponse?) {
//                    mRootView.get()?.onCollectionListSuccess(data)
                    //下拉刷新 无数据
                    if (page == 1 && (data?.content == null || data.content!!.isEmpty())) {
                        mRootView.get()?.onCollectionListNullSuccess()
                        return
                    }

                    //下拉刷新 有数据
                    if (page == 1 && data?.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onCollectionListSuccess(data)
                        return
                    }

                    //上拉加载 无数据
                    if (data?.content == null || data.content!!.isEmpty()) {
                        mRootView.get()?.onCollectionNullSuccess()
                        return
                    }

                    //上拉加载 有数据
                    if (data.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onCollectionSuccess(data)
                    }

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onCollectionListError()
                }
            })
    }


}