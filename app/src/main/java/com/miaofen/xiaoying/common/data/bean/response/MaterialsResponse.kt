package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class MaterialsResponse(
    var gender: Int?,
    var province: String?,
    var personalSignature: String?,
    var avatarUrl: String?,
    var city: String?,
    var nickName: String?,
    var userId: Int?,
    var birthdayTime: String?
)