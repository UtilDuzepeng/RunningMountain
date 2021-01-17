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

data class PersonalResponse(
    var motorcycle: Boolean?,
    var avatarUrl: String?,
    var fansNumber: Int?,
    var nickName: String?,
    var collectionCount: Int?,
    var userId: Int?,
    var followsNumber: Int?
)