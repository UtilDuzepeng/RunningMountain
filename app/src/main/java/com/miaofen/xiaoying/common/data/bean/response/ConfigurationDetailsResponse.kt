package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/25/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class ConfigurationDetailsResponse (
    val receiveNotificationMessage:Boolean,//接收推送消息
    val showPersonalInformation :Boolean,//展示个人资料
    val showTravelPlan:Boolean//展示旅行计划
)