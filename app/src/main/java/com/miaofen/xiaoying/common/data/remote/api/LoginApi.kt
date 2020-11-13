package com.miaofen.xiaoying.common.data.remote.api

import com.miaofen.xiaoying.common.data.bean.CommonResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 项目名称：com.miaofen.xiaoying.common.data.remote.api
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface LoginApi {
    /**
     * 登录
     */
//    @FormUrlEncoded
    @POST("users/login/password")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<CommonResponse<String>>

}