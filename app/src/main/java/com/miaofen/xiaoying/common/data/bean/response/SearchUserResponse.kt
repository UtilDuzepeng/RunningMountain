package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：搜索用户列表实体类
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class SearchUserResponse(
    var pageNumber: Int?,
    var pageSize: Int?,
    var totalElements: Int?,
    var content: List<ContentBean>?

) {
    data class ContentBean(
        var motorcycle: String?,
        var personalSignature: String?,
        var avatarUrl: String?,
        var nickName: String?,
        var selfFollowUser: Boolean?,//自己是否已关注该用户
        var userId: Int?,
        var userFollowSelf: Boolean?//用户是否关注自己
    )
}