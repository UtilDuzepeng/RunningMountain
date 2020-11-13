package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

data class LoginBean(
    var token: String?,
    var company: CompanyInfo?,
    var curRegStatus: String? = null,
    var email: String? = null,
    var id: Int? = null,
    var nickName: String? = null,
    var roleId: Int? = null,
    var userInfoRemark: String? = null,
    var userName: String? = null,
    var companyName :String? = null,
    var companyPhone:String? = null,
    var companyAddress:String? = null,
    var companyId:String? = null
) {

    fun isNoPay() = curRegStatus == "3"
}




