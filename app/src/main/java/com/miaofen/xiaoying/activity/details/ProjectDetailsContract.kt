package com.miaofen.xiaoying.activity.details

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.request.DeleteCommentRequestData
import com.miaofen.xiaoying.common.data.bean.request.DetailsRequestData
import com.miaofen.xiaoying.common.data.bean.request.FabulousRequestData
import com.miaofen.xiaoying.common.data.bean.request.OneCommentsData
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse
import mlxy.utils.S

/**
 * 项目名称：com.miaofen.xiaoying.activity.details
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface ProjectDetailsContract {
    interface Presenter : IPresenter {
        fun doProjectDetails(planId: Int?)
        fun doOneComments(planId: Int?, page: Int?, size: Int?)
        fun doDeleteComment(deleteCommentRequestData: DeleteCommentRequestData?)//删除评论
        fun doFabulous(commentId: Long?)//点赞评论
        fun doUnStar(commentId: Long?)//取消点赞
    }

    interface View : IView<Presenter> {
        //计划详情请求成功
        fun onProjectDetailsSuccess(data: DetailsResponse)

        //请求失败
        fun onProjectDetailsError()

        //轮播图
        fun onPlanImages(planImages: ArrayList<ImagerDataBean>?)

        //发布人信息
        fun onPublisherInfo(publisherInfo: DetailsResponse.PublisherInfoBean?)

        //计划详情 发布时间
        fun onPlanDetailCreateTime(createTime: Long?)

        //计划详情 按钮状态
        fun onPlanDetailButtonInfo(buttonInfo: DetailsResponse.ButtonInfoBean?)

        //计划详情 标题
        fun onPlanDetailTitle(title: String?)

        //计划详情 内容
        fun onPlanDetailContent(content: String?)

        //计划详情 希望人数
        fun onPlanDetailExceptedNumber(exceptedNumber: Int?)

        //计划详情 人均预算
        fun onPlanDetailPerCapitaBudget(perCapitaBudget: Int?)

        //计划详情 计费方式
        fun onPlanDetailCostMethod(costMethod: String?)

        //计划详情 始发地
        fun onPlanDetailPlaceOfDeparture(placeOfDeparture: String?)

        //计划详情 目的地
        fun onPlanDetailDestination(destination: String?)

        //始发地与我相遇距离
        fun onUserPlanDistance(userPlanDistance: String?, type: Boolean)

        //评论数量
        fun onCommentCount(commentCount: String?)

        //起止时间
        fun onStartTimeEndTime(time: String)

        //已报名人数
        fun onJoins(joinsSize: String)

        //途经地
        fun onPlanTrips(tvPlanTrips: String, dataPlanTrips: List<DetailsResponse.PlanTripsBean>?)

        //暂无途径地
        fun onHindPlanTrips()

        //想加入的人员信息
        fun onWants(tvWants: String, wants: List<DetailsResponse.WantsBean>?)

        //暂无想加入的人员
        fun onHindWants()

        //已报名人数
        fun onJoinsData(tvJoinsName: String, joins: List<DetailsResponse.JoinsBean>?)

        //暂无报名人数
        fun onHindJoinsData()

        //一级评论列表下拉成功 没有数据
        fun onDownOneCommentsNullSuccess()

        //一级评论列表下拉成功 有数据
        fun onDownOneCommentsSuccess(data: OneCommentsResponse?)

        //一级评论列表上啦加载 有数据
        fun onOneCommentsSuccess(data: OneCommentsResponse?)

        //上啦加载 没有数据
        fun onOneCommentsNullSuccess()

        fun onOneCommentsError()

        /*——————————————————————————删除评论成功————————————————————————————*/
        fun onDeleteCommentSuccess(data: String?)
        fun onDeleteCommentError()

        /*———————————————————————点赞评论————————————————————————————————*/
        fun onFabulousSuccess(data: String?)
        fun onFabulousError()

        /*————————————————————取消评论点赞——————————————————————————*/
        fun onUnStarSuccess(data: String?)
        fun onUnStarError()


    }
}