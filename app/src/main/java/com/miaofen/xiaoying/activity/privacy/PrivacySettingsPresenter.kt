package com.miaofen.xiaoying.activity.privacy

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.ConfigurationDetails
import com.miaofen.xiaoying.common.data.bean.request.ExhibitionMaterialsRequestData
import com.miaofen.xiaoying.common.data.bean.request.ExhibitionPlanRequestData
import com.miaofen.xiaoying.common.data.bean.request.ReceiveMessagesRequestData
import com.miaofen.xiaoying.common.data.bean.response.ConfigurationDetailsResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.privacy
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/21/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class PrivacySettingsPresenter (view: PrivacySettingsContract.View) :
    BasePresenter<PrivacySettingsContract.View>(view), PrivacySettingsContract.Presenter{

    //展示计划
    private val exhibitionPlanRequestData = ExhibitionPlanRequestData()
    override fun doExhibitionPlan(showTravelPlan: Boolean) {
        exhibitionPlanRequestData.setShowTravelPlan(showTravelPlan)
        RemoteRepository
            .onExhibitionPlan(exhibitionPlanRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<Boolean>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: Boolean?) {
                    mRootView.get()?.onExhibitionPlanSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onExhibitionPlanError()
                }
            })
    }

    //展示资料
    private val exhibitionMaterialsRequestData = ExhibitionMaterialsRequestData()
    override fun doExhibitionMaterials(showPersonalInformation: Boolean) {
        exhibitionMaterialsRequestData.setShowPersonalInformation(showPersonalInformation)
        RemoteRepository
            .onExhibitionMaterials(exhibitionMaterialsRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<Boolean>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: Boolean?) {
                    mRootView.get()?.onExhibitionMaterialsSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onExhibitionMaterialsError()
                }
            })
    }
    //接收消息
    private val receiveMessagesRequestData = ReceiveMessagesRequestData()
    override fun doReceiveMessages(receiveNotificationMessage: Boolean) {
        receiveMessagesRequestData.setReceiveNotificationMessage(receiveNotificationMessage)
        RemoteRepository
            .onReceiveMessages(receiveMessagesRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<Boolean>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: Boolean?) {
                    mRootView.get()?.onReceiveMessagesSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onReceiveMessagesError()
                }
            })
    }

    //配置详情
    private val configurationDetails = ConfigurationDetails()

    override fun doConfigurationDetails() {
        RemoteRepository
            .onConfigurationDetails(configurationDetails)
            .applySchedulers()
            .subscribe(object : CommonObserver<ConfigurationDetailsResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: ConfigurationDetailsResponse?) {
                    mRootView.get()?.onConfigurationDetailsSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onConfigurationDetailsError()
                }
            })

    }

}