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

data class CollectionListResponse(
    var pageNumber: Int?,
    var pageSize: Int?,
    var totalElements: Int?,
    var empty: Boolean?,
    var content: List<ContentBean?>? = null
) {
    data class ContentBean(
        var publisherInfo: PublisherInfoBean?,
        var planInfo: PlanInfoBean?
    ) {
        data class PublisherInfoBean(
            var motorcycle: String?,
            var gender: Int?,
            var personalSignature: String?,
            var avatarUrl: String?,
            var nickName: String?,
            var userId: Int?
        )

        data class PlanInfoBean(
            var exceptedNumber: Int?,
            var destination: String?,
            var planStatus: Int?,
            var title: String?,
            var userId: Int?,
            var content: String?,
            var placeOfDeparture: String?,
            var perCapitaBudget: Int?,
            var createTime: Long?,
            var placeOfDepartureGps: String?,
            var destinationGps: String?,
            var costMethod: Int?,
            var startTime: Long?,
            var planId: Int?,
            var endTime: Long?
        )
    }
}