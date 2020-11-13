package com.miaofen.xiaoying.common.data.bean

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class CommonResponse <T> {

    var code: Int = 0
    var msg: String? = null
    var data: T? = null

    fun isSuccessful() = code == RESULT_OK

    fun isExpire() = code == EXPIRE

    fun <N> newResp(data: N): CommonResponse<N> {
        val ret = CommonResponse<N>()
        ret.code = code
        ret.msg = msg
        ret.data = data
        return ret
    }

    companion object {
        private const val RESULT_OK = 200
        private const val EXPIRE = 40001
    }
}