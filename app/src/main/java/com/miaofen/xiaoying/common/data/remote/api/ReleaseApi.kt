package com.miaofen.xiaoying.common.data.remote.api

import com.miaofen.xiaoying.common.data.bean.CommonResponse
import com.miaofen.xiaoying.common.data.bean.request.PublishRequestData
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*

/**
 * 项目名称：com.miaofen.xiaoying.common.data.remote.api
 * 类描述：发布页面api
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface ReleaseApi {

    /**
     * 发布旅行计划
     */
    @POST("weixin/plan/publish")
    fun releasePlan(@Body publishRequestData :PublishRequestData): Observable<CommonResponse<String>>


}