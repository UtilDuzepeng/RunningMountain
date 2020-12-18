package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class OneCommentsResponse(
    var pageNumber: Int?,
    var pageSize: Int?,
    var totalElements: Int?,
    var empty: Boolean?,
    var content: List<ContentBean>?
) {
    data class ContentBean(
        var starCount: Int?,
        var replyCount: Int?,
        var star: Boolean?,
        var createTime: Long?,
        var commentId: Long?,
        var canDelete: Boolean?,
        var classX: String?,
        var content: String?,
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
}