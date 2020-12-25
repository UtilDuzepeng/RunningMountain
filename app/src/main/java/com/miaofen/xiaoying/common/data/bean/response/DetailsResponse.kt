package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：计划详情
 * 创建人：duzepeng
 * 创建时间：2020/12/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
//(var data: DataBean?)
data class DetailsResponse(
    var publisherInfo: PublisherInfoBean?,
    var collection: Boolean?,
    var commentCount: Int?,
    var userPlanDistance: Double?,
    var planDetail: PlanDetailBean?,
    var publisher: Boolean?,
    var buttonInfo: ButtonInfoBean?,
    var joins: List<JoinsBean>?,
    var planTags: List<PlanTagsBean>?,
    var planTrips: List<PlanTripsBean>?,
    var wants: List<WantsBean>?,
    var planImages: List<PlanImagesBean>?
) {

    data class PublisherInfoBean(
        var motorcycle: String?,
        var gender: Int?,
        var personalSignature: String?,
        var avatarUrl: String?,
        var nickName: String?,
        var userId: Long?
    )

    data class PlanDetailBean(
        var exceptedNumber: Int?,
        var destination: String?,
        var planStatus: Int?,
        var title: String?,
        var userId: Long?,
        var content: String?,
        var placeOfDeparture: String?,
        var perCapitaBudget: Int?,
        var createTime: Long?,
        var placeOfDepartureGps: String?,
        var destinationGps: String?,
        var costMethod: Int?,
        var planId: Int?,
        var startTime: Long?,
        var endTime: Long?
    )

    data class ButtonInfoBean(
        var buttonName: String?,
        var buttonAction: Int?,
        var subButtonInfo: List<SubButtonInfoBean>?
    ) {
        data class SubButtonInfoBean(
            var buttonName: String?,
            var buttonAction: Int?
        )
    }

    data class JoinsBean(
        var motorcycle: String?,
        var gender: Int?,
        var personalSignature: String?,
        var avatarUrl: String?,
        var nickName: String?,
        var userId: Int?
    )

    data class PlanTagsBean(
        var tagName: String?
    )

    data class PlanTripsBean(
        var name: String?,
        var remark: String?,
        var sort: Int?,
        var gps: String?
    )

    data class WantsBean(
        var motorcycle: String?,
        var gender: Int?,
        var personalSignature: String?,
        var avatarUrl: String?,
        var nickName: String?,
        var userId: Long?
    )

    data class PlanImagesBean(
        var imageUrl: String?
    )

}