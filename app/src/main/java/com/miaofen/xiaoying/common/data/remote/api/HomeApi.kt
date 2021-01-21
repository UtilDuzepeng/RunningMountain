package com.miaofen.xiaoying.common.data.remote.api

import com.miaofen.xiaoying.common.data.bean.CommonResponse
import com.miaofen.xiaoying.common.data.bean.request.*
import com.miaofen.xiaoying.common.data.bean.response.*
import io.reactivex.Observable
import mlxy.utils.S
import retrofit2.http.Body
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

    /**
     * 搜索推荐
     */
    @POST("weixin/search/recommend")
    fun recommend(): Observable<CommonResponse<ArrayList<String>>>

    /**
     * 轮播图
     */
    @POST("weixin/index/recommends")
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

    /**
     * 搜索历史
     */
    @POST("weixin/user/search/history")
    fun onHistory(): Observable<CommonResponse<ArrayList<String>>>

    /**
     * 搜索计划
     */
    @POST("weixin/search/plan")
    fun onPlan(@Body planRequestData: PlanRequestData): Observable<CommonResponse<PlanResponse>>

    /**
     * 搜索用户
     */
    @POST("weixin/search/user")
    fun onSearchUser(@Body planRequestData: PlanRequestData): Observable<CommonResponse<SearchUserResponse>>

    /**
     * 删除评论
     */
    @POST("weixin/plan/comment/delete")
    fun onDeleteComment(@Body deleteCommentRequestData: DeleteCommentRequestData): Observable<CommonResponse<String>>

    /**
     * 点赞旅行计划评论
     */
    @POST("weixin/plan/comment/star")
    fun onFabulous(@Body fabulousRequestData: FabulousRequestData): Observable<CommonResponse<String>>

    /**
     * 取消点赞旅行计划评论
     */
    @POST("weixin/plan/comment/unStar")
    fun onUnStar(@Body fabulousRequestData: FabulousRequestData?): Observable<CommonResponse<String>>

    /**
     * 发布旅行计划评论
     */
    @POST("weixin/plan/comment/publish")
    fun onPubComment(@Body pubCommentRequestData: PubCommentRequestData): Observable<CommonResponse<String>>

    /**
     * 查看一级计划回复列表
     */
    @POST("weixin/plan/subComments")
    fun onSubComments(@Body subCommentsRequestData: SubCommentsRequestData): Observable<CommonResponse<SecondaryReplyResponse>>

    /**
     * 审核中列表
     */
    @POST("weixin/plan/auditing/users")
    fun onExamine(@Body examineRequestData: ExamineRequestData): Observable<CommonResponse<List<ExamineResponse>>>

    /**
     * 拒绝列表
     */
    @POST("weixin/plan/refused/users")
    fun onRefuse(@Body refuseRequestData: RefuseRequestData): Observable<CommonResponse<List<RefuseResponse>>>


    /**
     * 计划发布者通过报名
     */
    @POST("weixin/plan/pass")
    fun onPass(@Body passRequestData: PassRequestData): Observable<CommonResponse<String>>

    /**
     * 计划发布者拒绝报名
     */
    @POST("weixin/plan/refuse")
    fun onRefuse(@Body cancelRequestData: CancelRequestData): Observable<CommonResponse<String>>

    /**
     * 报名旅行计划
     */
    @POST("weixin/plan/join")
    fun onSignUpPlan(@Body signUpPlanRequestData: SignUpPlanRequestData): Observable<CommonResponse<String>>

    /**
     * 清除历史记录
     */
    @POST("weixin/user/search/history/clear")
    fun onClearRecord(): Observable<CommonResponse<String>>

    /**
     * 收藏旅行计划
     */
    @POST("weixin/user/collect/plan")
    fun onCollection(@Body travelPlanRequestData: TravelPlanRequestData): Observable<CommonResponse<String>>

    /**
     * 取消收藏旅行计划
     */
    @POST("weixin/user/cancelCollect/plan")
    fun onCancelCollection(@Body travelPlanRequestData: TravelPlanRequestData): Observable<CommonResponse<String>>

    /**
     * 取消关注
     */
    @POST("weixin/user/cancelFollow")
    fun onCancelAttention(@Body cancelAttentionRequestData: CancelAttentionRequestData): Observable<CommonResponse<String>>


}