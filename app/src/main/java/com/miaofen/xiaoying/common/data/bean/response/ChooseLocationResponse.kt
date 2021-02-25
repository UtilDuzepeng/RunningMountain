package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述 ：地名 位置吗
 * 创建人：duzepeng
 * 创建时间：2021/2/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
data class ChooseLocationResponse(
    var areaName: String,
    var spell: String,
    var areaCode: Int,
    var lon: Double?,
    var lat: Double?
)