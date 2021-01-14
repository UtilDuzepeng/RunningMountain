package com.miaofen.xiaoying.common.data.bean.response

import com.google.gson.annotations.SerializedName

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class HomeResponse(
    var pageNumber: Int?,//页数
    val pageSize: Int?,//页码
    val totalElements: Int?,//总数
    val empty: Boolean?,
    val content: List<ContentBean>? = null//内容
) {
    data class ContentBean(
        var gender: Int?,//性别
        val joinNumber: Int?,
        val destination: String?,//目的地
        val heatCount: Int?,
        val planStatus: Int?,
        val title: String?,//标题
        val mtUserId: Long?,//发布人ID
        val content: String?,//内容
        val motorcycle: String?,//座驾
        val placeOfDeparture: String?,//始发地
        val collectionNumber: Int?,//收藏数量
        val startTime: Long?,//始发时间
        val id: Int?,//计划ID
        @SerializedName("class")
        var classX: String?,
        val avatarUrl: String?,//发布人头像
        val nickName: String?,//发布人昵称
        val collection: Boolean?,//是否收藏
        val commentCount: Int?,//评论数量
        val userPlanDistance: Double?,//与我相距
        val createTime: Long?,//发布时间
        val placeOfDepartureGps: String?,//始发地GPS
        val endTime: Long?,//结束时间
        val images: List<ImagesBean>?,//图片list
        val tags: List<TagsBean>?,//标签list
        val trips: List<TripsBean>?,//途经地list
        val pastTime :String?
    ) {
        data class ImagesBean(
            var imageUrl: String?,//封面图
            @SerializedName("class")
            val classX: String?
        )

        data class TagsBean(
            var tagName: String?,//标签名
            @SerializedName("class")
            val classX: String?
        )

        data class TripsBean(
            var name: String?,//途经地名
            val remark: String?,
            val sort: Int?,//途经地顺序
            val gps: String?,
            @SerializedName("class")
            val classX: String?
        )
    }
}