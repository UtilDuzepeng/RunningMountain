package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：用户对外基本资料
 * 创建人：duzepeng
 * 创建时间：2021/1/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class PersonalHomPagerResponse (
    var planCount :Int?,
    var gender :Int?,
    var avatarUrl :String?,
    var nickName :String?,
    var showPlan :Boolean?,
    var follow :Boolean?,
    var userId :Long?,
    var motorcycle :String?,
    var personalSignature :String?,
    var fansNumber :Int?,
    var canEditProfile :Boolean?,
    var showProfile :Boolean?,
    var followsNumber :Int?

)