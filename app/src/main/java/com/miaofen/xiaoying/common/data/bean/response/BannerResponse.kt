package com.miaofen.xiaoying.common.data.bean.response

import com.google.gson.annotations.SerializedName

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：首页轮播实体类
 * 创建人：duzepeng
 * 创建时间：2020/12/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class BannerResponse(
    var coverImage: String?,
    val entityId: String?,
    val sort: Int?,
    val page: String?,
    val title: String,
    val type: Int?,
    @SerializedName("class")
    var classX: String?
)