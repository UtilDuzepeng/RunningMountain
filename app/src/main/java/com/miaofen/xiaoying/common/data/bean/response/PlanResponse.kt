package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：搜索计划实体类
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class PlanResponse(
    var pageNumber: Int?,
    var pageSize: Int?,
    var totalElements: Int?,
    var empty: Boolean?,
    var content: List<ContentBean>?
) {
    data class ContentBean(
        var motorcycle: String?,
        var gender: Int?,
        var avatarUrl: String?,
        var nickName: String?,
        var id: Int?,
        var title: String?,
        var mtUserId: Long?,
        var content: String?
    )
}
