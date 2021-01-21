package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class SecondaryReplyResponse(
    var topPlanComment: TopPlanCommentBean?,
    var subPlanCommentList: List<SubPlanCommentListBean>?
) {
    data class TopPlanCommentBean(
        var userInfo: UserInfoBean?,
        var starCount: Int?,
        var star: Boolean?,
        var createTime: Long?,
        var commentId: Long?,
        var parentCommentId: Int?,
        var canDelete: Boolean?,
        var follow: Int?,
        var content: String?
    ) {
        data class UserInfoBean(
            var motorcycle: String?,
            var gender: Int?,
            var personalSignature: String?,
            var avatarUrl: String?,
            var nickName: String?,
            var userId: Int?
        )
    }

    data class SubPlanCommentListBean(
        var userInfo: UserInfoBeanX?,
        var starCount: Int?,
        var star: Boolean?,
        var createTime: Long?,
        var commentId: Long?,
        var parentCommentId: Long?,
        var canDelete: Boolean?,
        var follow: String?,
        var content: String?
    ) {
        data class UserInfoBeanX(
            var motorcycle: String?,
            var gender: Int?,
            var personalSignature: String?,
            var avatarUrl: String?,
            var nickName: String?,
            var userId: Int?
        )
    }
}