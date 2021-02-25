package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/31
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class DetailedResponse(
    var userInfo: UserInfoBean?,
    var authenticationList: List<AuthenticationListBean>?,
    var haveBeenToScenicSpot: List<String>?,
    var haveBeenToCity: List<String>?
) {
    data class UserInfoBean(
        var gender: Int?,
        var distance: Long?,
        var province: String?,
        var createTime: Long?,
        var city: String?,
        var updateTime: Long?,
        var birthdayTime: String?
    )

    data class AuthenticationListBean(
        var authenticationType: Int?,
        var authentication: Boolean?
    )
}