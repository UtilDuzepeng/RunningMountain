package com.miaofen.xiaoying.activity.personal

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.*
import com.miaofen.xiaoying.common.data.bean.response.DetailedResponse
import com.miaofen.xiaoying.common.data.bean.response.ExternalDisplayResponse
import com.miaofen.xiaoying.common.data.bean.response.PersonalHomPagerResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.personal
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class PersonalHomPagerPresenter(view: PersonalHomPagerContract.View) :
    BasePresenter<PersonalHomPagerContract.View>(view), PersonalHomPagerContract.Presenter {

    private val basicInformationRequestData = BasicInformationRequestData()
    override fun doPersonalHomPager(userId: Long) {
        basicInformationRequestData.setUserId(userId)
        RemoteRepository
            .onBasicInformation(basicInformationRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<PersonalHomPagerResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: PersonalHomPagerResponse?) {
                    mRootView.get()?.onPersonalHomPagerSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onPersonalHomPagerError()
                }
            })
    }

    private val detailedDataRequestData = DetailedDataRequestData()
    override fun doDetailsData(userId: Long) {
        detailedDataRequestData.setUserId(userId)
        RemoteRepository
            .onDetails(detailedDataRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<DetailedResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: DetailedResponse?) {
                    mRootView.get()?.onDetailsDataSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onDetailsDataError()
                }
            })
    }

    //对外展示旅行计划
    private val externalDisplayRequestData = ExternalDisplayRequestData()
    override fun doExternalDisplay(userId: Long, page: Int, size: Int) {
        externalDisplayRequestData.setUserId(userId)
        externalDisplayRequestData.setPage(page)
        externalDisplayRequestData.setSize(size)
        RemoteRepository
            .onExternalDisplay(externalDisplayRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<ExternalDisplayResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: ExternalDisplayResponse?) {
                    mRootView.get()?.onExternalDisplaySuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onExternalDisplayError()
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