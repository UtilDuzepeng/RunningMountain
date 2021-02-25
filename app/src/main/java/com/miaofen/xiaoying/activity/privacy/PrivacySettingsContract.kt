package com.miaofen.xiaoying.activity.privacy

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.ConfigurationDetailsResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.privacy
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/21/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface PrivacySettingsContract {

    interface Presenter : IPresenter {
        fun doExhibitionPlan(showTravelPlan: Boolean)//展示计划
        fun doExhibitionMaterials(showPersonalInformation :Boolean)//展示资料
        fun doReceiveMessages(receiveNotificationMessage :Boolean)//接收消息
        fun doConfigurationDetails()//配置详情
    }

    interface View : IView<Presenter> {
        fun onExhibitionPlanSuccess(data: Boolean?)
        fun onExhibitionPlanError()
        fun onExhibitionMaterialsSuccess(data: Boolean?)
        fun onExhibitionMaterialsError()
        fun onReceiveMessagesSuccess(data: Boolean?)
        fun onReceiveMessagesError()
        fun onConfigurationDetailsSuccess(data: ConfigurationDetailsResponse?)
        fun onConfigurationDetailsError()
    }

}