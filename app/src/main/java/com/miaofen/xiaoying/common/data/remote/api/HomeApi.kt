package com.miaofen.xiaoying.common.data.remote.api

import com.miaofen.xiaoying.common.data.bean.CommonResponse
import com.miaofen.xiaoying.common.data.bean.request.DetailsRequestData
import com.miaofen.xiaoying.common.data.bean.request.HomeRequestData
import com.miaofen.xiaoying.common.data.bean.request.OneCommentsData
import com.miaofen.xiaoying.common.data.bean.response.BannerResponse
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    /**
     * 搜索推荐
     */
    @POST("weixin/search/recommend")
    fun recommend(): Observable<CommonResponse<ArrayList<String>>>

    /**
     * 轮播图
     */
    @GET("weixin/index/recommends")
    fun rotationChart(): Observable<CommonResponse<List<BannerResponse>>>

    /**
     * 首页最新列表
     */
    @POST("weixin/index/plan/latest")
    fun newest(@Body homeRequestData: HomeRequestData): Observable<CommonResponse<HomeResponse>>

    /**
     * 首页热门列表
     */
    @POST("weixin/index/plan/hot")
    fun hot(@Body homeRequestData: HomeRequestData): Observable<CommonResponse<HomeResponse>>

    /**
     * 首页附近列表
     */
    @POST("weixin/index/plan/near")
    fun nearby(@Body homeRequestData: HomeRequestData): Observable<CommonResponse<HomeResponse>>

    /**
     * 旅行计划详情
     */
    @POST("weixin/plan/detail")
    fun details(@Body detailsRequestData: DetailsRequestData?): Observable<CommonResponse<DetailsResponse>>

    /**
     * 旅行计划详情一级评论
     */
    @POST("weixin/plan/comments")
    fun oneComments(@Body oneCommentsData: OneCommentsData?): Observable<CommonResponse<OneCommentsResponse>>

}