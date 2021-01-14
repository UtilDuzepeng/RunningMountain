package com.miaofen.xiaoying.common.data.bean.response

import java.util.*

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：拒绝列表放回实体类
 * 创建人：duzepeng
 * 创建时间：2021/1/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class RefuseResponse(
    var createTime: Long?,
    var contactWay: String?,
    var planId: Int?,
    var remark: String?,
    var joinId: Int?,
    var userInfo: UserInfoBean?
) {
    data class UserInfoBean(
        var motorcycle: String?,
        var gender: Int?,
        var personalSignature: String?,
        var avatarUrl: String?,
        var nickName: String?,
        var userId: Long?
    )
}