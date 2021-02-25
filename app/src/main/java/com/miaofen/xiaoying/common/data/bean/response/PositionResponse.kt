package com.miaofen.xiaoying.common.data.bean.response

import com.miaofen.xiaoying.common.data.bean.response.AAAA.DataBean

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/2/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class PositionResponse(
    var spell: String?,
    var areaCode: Int?,
    var level: Int?,
    var areaName: String?,
    var parentCode: Int?,
    var lon: Double?,
    var lat: Double?
)