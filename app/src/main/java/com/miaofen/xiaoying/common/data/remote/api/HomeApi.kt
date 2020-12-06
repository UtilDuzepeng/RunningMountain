package com.miaofen.xiaoying.common.data.remote.api

import com.miaofen.xiaoying.common.data.bean.CommonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 项目名称：com.miaofen.xiaoying.common.data.remote.api
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface HomeApi {

    @GET("weixin/index/recommends")
    fun rotationChart(): Observable<CommonResponse<String>>
}