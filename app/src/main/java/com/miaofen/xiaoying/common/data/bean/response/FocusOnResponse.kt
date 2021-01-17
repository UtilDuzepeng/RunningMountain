package com.miaofen.xiaoying.common.data.bean.response

import com.miaofen.xiaoying.common.data.bean.response.AAAA.DataBean

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class FocusOnResponse(
    var pageNumber: Int?,
    var pageSize: Int?,
    var totalElements: Int?,
    var empty: Boolean?,
    var content: List<ContentBean?>? = null
) {
    data class ContentBean(
        var motorcycle: String?,
        var personalSignature: String?,
        var avatarUrl: String?,
        var nickName: String?,
        var selfFollowUser: Boolean?,
        var userId: Int?,
        var userFollowSelf: Boolean?
    )
}