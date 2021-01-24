package com.miaofen.xiaoying.activity.fans

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.CancelAttentionRequestData
import com.miaofen.xiaoying.common.data.bean.request.CollectionRequestData
import com.miaofen.xiaoying.common.data.bean.request.FocusUsersRequestData
import com.miaofen.xiaoying.common.data.bean.response.FansResponse
import com.miaofen.xiaoying.common.data.bean.response.FocusOnResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.fans
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class FansPresenter (view: FansContract.View) :
    BasePresenter<FansContract.View>(view), FansContract.Presenter {

    private var fansRequestData = CollectionRequestData()

    override fun doFans(page: Int, size: Int) {
        fansRequestData.setPage(page)
        fansRequestData.setSize(size)
        RemoteRepository
            .onFans(fansRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<FansResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: FansResponse?) {

                    //下拉刷新 无数据
                    if (page == 1 && (data?.content == null || data.content!!.isEmpty())) {
                        mRootView.get()?.onFansNullSuccess()
                        return
                    }

                    //下拉刷新 有数据
                    if (page == 1 && data?.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onFansSuccess(data)
                        return
                    }

                    //上拉加载 无数据
                    if (data?.content == null || data.content!!.isEmpty()) {
                        mRootView.get()?.onFansOnNullSuccess()
                        return
                    }

                    //上拉加载 有数据
                    if (data.content != null && data.content!!.isNotEmpty()) {
                        mRootView.get()?.onFansOnSuccess(data)
                    }

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onFansOnError()
                }
            })
    }

    //关注
    private val focusUsersRequestData = FocusUsersRequestData()
    override fun doFocusOnUsers(followId: Long?) {
        focusUsersRequestData.setFollowId(followId!!)
        RemoteRepository
            .onFocusOnUsers(focusUsersRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<Boolean>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: Boolean?) {

                    mRootView.get()?.onFocusOnUsersSuccess(data!!)

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onFocusOnUsersError()
                }
            })
    }

    /*-----------取消关注----------*/
    private val cancelAttentionRequestData =  CancelAttentionRequestData()
    override fun doCancelAttentio(followId: Long?) {
        cancelAttentionRequestData.setCancelFollowId(followId!!)
        RemoteRepository
            .onCancelAttention(cancelAttentionRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<Boolean>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: Boolean?) {

                    mRootView.get()?.onCancelAttentioSuccess(data!!)

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onCancelAttentioError()
                }
            })
    }


}