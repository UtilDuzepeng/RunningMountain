package com.miaofen.xiaoying.activity.details


import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.details.adapter.JoinsRecyclerAdapter
import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.*
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import com.miaofen.xiaoying.utils.decimalBalanceFormat
import com.miaofen.xiaoying.utils.getCurrentTime
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.details_head_layout.*

/**
 * 项目名称：com.miaofen.xiaoying.activity.details
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ProjectDetailsPresenter(view: ProjectDetailsContract.View) :
    BasePresenter<ProjectDetailsContract.View>(view), ProjectDetailsContract.Presenter {

    //轮播图集合
    private var imagePath = ArrayList<ImagerDataBean>()
    val imagerDataBean = ImagerDataBean()

    //请求计划详情body
    val detailsRequestData = DetailsRequestData()


    /**
     * 旅行计划详情
     */
    override fun doProjectDetails(planId: Int?) {

        if (planId == null) {
            mRootView.get()?.onProjectDetailsError()
            return
        }
        detailsRequestData.setPlanId(planId)

        RemoteRepository
            .details(detailsRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<DetailsResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: DetailsResponse?) {
                    if (data == null) {
                        return
                    }
                    //请求成功
                    mRootView.get()?.onProjectDetailsSuccess(data)

                    /**-------------------------数据解析处理-----------------------**/

                    //轮播图
                    if (data.planImages != null || data.planImages!!.isNotEmpty()) {
                        for (item in data.planImages!!) {
                            imagerDataBean.url = item.imageUrl
                            imagePath.add(imagerDataBean)
                        }
                        mRootView.get()?.onPlanImages(imagePath)
                    }
                    //发布人信息
                    if (data.publisherInfo != null) {
                        mRootView.get()?.onPublisherInfo(data.publisherInfo)
                    }

                    //计划详情
                    if (data.planDetail != null) {
                        //发布时间
                        if (data.planDetail?.createTime != null) {
                            mRootView.get()?.onPlanDetailCreateTime(data.planDetail?.createTime)
                        }
                        //按钮状态
                        if (data.buttonInfo != null) {
                            mRootView.get()?.onPlanDetailButtonInfo(data.buttonInfo,data.planDetail)
                        }
                        //计划名称
                        mRootView.get()?.onPlanDetailTitle(data.planDetail?.title)
                        //计划内容
                        mRootView.get()?.onPlanDetailContent(data.planDetail?.content)

                        //希望人数
                        if (data.planDetail?.exceptedNumber != null) {
                            mRootView.get()
                                ?.onPlanDetailExceptedNumber(data.planDetail?.exceptedNumber)
                        }
                        //人均预算
                        if (data.planDetail?.perCapitaBudget != null) {
                            mRootView.get()
                                ?.onPlanDetailPerCapitaBudget(data.planDetail?.perCapitaBudget)
                        }
                        //计费方式
                        if (data.planDetail?.costMethod != null) {
                            when (data.planDetail?.costMethod) {
                                1 -> {
                                    mRootView.get()
                                        ?.onPlanDetailCostMethod("AA")
                                }
                                2 -> {
                                    mRootView.get()
                                        ?.onPlanDetailCostMethod("男A女免")
                                }
                                3 -> {
                                    mRootView.get()
                                        ?.onPlanDetailCostMethod("免费参加")
                                }
                            }
                        }

                        //始发地
                        mRootView.get()
                            ?.onPlanDetailPlaceOfDeparture(data.planDetail?.placeOfDeparture)

                        //目的地
                        mRootView.get()
                            ?.onPlanDetailDestination(data.planDetail?.destination)
                    }

                    //始发地与我相遇距离
                    if (data.userPlanDistance != null) {
                        mRootView.get()
                            ?.onUserPlanDistance(
                                "距离 " + decimalBalanceFormat(data.userPlanDistance) + "km",
                                true
                            )
                    } else {
                        mRootView.get()?.onUserPlanDistance("", false)
                    }

                    //评论数量
                    if (data.commentCount != null) {
                        mRootView.get()?.onCommentCount("评论(${data.commentCount})")
                    } else {
                        mRootView.get()?.onCommentCount("评论(${0})")
                    }

                    //起止时间
                    if (data.planDetail?.startTime != null && data.planDetail?.endTime != null) {
                        mRootView.get()?.onStartTimeEndTime(
                            "${getCurrentTime(data.planDetail!!.startTime!!)} 至 ${getCurrentTime(
                                data.planDetail!!.endTime!!
                            )}"
                        )
                    }

                    //已报名人数
                    if (data.joins != null) {
                        mRootView.get()?.onJoins("${data.joins?.size}")
                    } else {
                        mRootView.get()?.onJoins("${0}")
                    }

                    //途径地数量
                    if (data.planTrips != null) {
                        mRootView.get()?.onPlanTrips("途径地(${data.planTrips?.size})", data.planTrips)
                    }
                    if (data.planTrips?.size == 0) {
                        mRootView.get()?.onHindPlanTrips()
                    }

                    //想加入的人员信息 头像列表
                    var wantsMale: Int = 0
                    var wantsFemale: Int = 0
                    if (data.wants != null) {
                        for (item in data.wants!!) {
                            when (item.gender) {
                                1 -> {
                                    ++wantsMale
                                }
                                2 -> {
                                    ++wantsFemale
                                }
                            }
                        }
                        mRootView.get()?.onWants("想约的(${wantsMale}男${wantsFemale}女）", data.wants)
                    }

                    if (data.wants?.size == 0) {
                        mRootView.get()?.onHindWants()
                    }

                    //已报名人数
                    var joinsMale: Int = 0
                    var joinsFemale: Int = 0
                    if (data.joins != null) {
                        for (item in data.joins!!) {
                            when (item.gender) {
                                1 -> {
                                    ++joinsMale
                                }
                                2 -> {
                                    ++joinsFemale
                                }
                            }
                        }
                        mRootView.get()?.onJoinsData(
                            "${data.joins?.size}人已报名(${joinsMale}男${joinsFemale}女）",
                            data.joins
                        )
                    }

                    if (data.joins?.size == 0) {
                        mRootView.get()?.onHindJoinsData()
                    }
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onProjectDetailsError()
                }
            })
    }


    /**-------------------------一级评论列表-----------------------------**/

    //请求计划详情一级评论body
    val oneCommentsData = OneCommentsData()


    //一级评论列表
    override fun doOneComments(planId: Int?, page: Int?, size: Int?) {
        if (planId == null) {
            mRootView.get()?.onOneCommentsError()
            return
        }
        oneCommentsData.setPlanId(planId)

        if (page == null) {
            mRootView.get()?.onOneCommentsError()
            return
        }
        oneCommentsData.setPage(page)

        if (size == null) {
            mRootView.get()?.onOneCommentsError()
            return
        }
        oneCommentsData.setSize(size)



        RemoteRepository
            .oneComments(oneCommentsData)
            .applySchedulers()
            .subscribe(object : CommonObserver<OneCommentsResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: OneCommentsResponse?) {
                    //下拉刷新 无数据
                    if (page == 1 && (data?.content == null || data.content?.size == 0)) {
                        mRootView.get()?.onDownOneCommentsNullSuccess()
                        return
                    }
                    //下拉刷新 有数据
                    if (page == 1 && data?.content != null && data.content?.size!! > 0) {
                        mRootView.get()?.onDownOneCommentsSuccess(data)
                        return
                    }

                    //上拉加载 无数据
                    if (data?.content == null || data.content?.size == 0) {
                        mRootView.get()?.onOneCommentsNullSuccess()
                        return
                    }

                    //上拉加载 有数据
                    if (data.content != null && data.content?.size!! > 0) {
                        mRootView.get()?.onOneCommentsSuccess(data)
                    }

                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onOneCommentsError()
                }
            })
    }


    /*—————————————————————————————删除评论———————————————————————————————*/
    private val deleteCommentRequestData = DeleteCommentRequestData()

    override fun doDeleteComment(commentId: Long?) {
        deleteCommentRequestData.setCommentId(commentId)
        RemoteRepository
            .onDeleteComment(deleteCommentRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
//                    if (data == null) {
//                        return
//                    }
                    mRootView.get()?.onDeleteCommentSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onDeleteCommentError()
                }
            })
    }

    /*———————————————————————点赞评论—————————————————————————*/
    private val fabulousRequestData = FabulousRequestData()
    override fun doFabulous(commentId: Long?) {
        fabulousRequestData.setCommentId(commentId)
        RemoteRepository
            .onFabulous(fabulousRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onFabulousSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onFabulousError()
                }
            })
    }

    //取消点赞评论
    private val unStarRequestData = FabulousRequestData()
    override fun doUnStar(commentId: Long?) {
        unStarRequestData.setCommentId(commentId)
        RemoteRepository
            .onUnStar(unStarRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onUnStarSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onUnStarError()
                }
            })

    }

    //解散小队
    private val dissolutionRequestData = DissolutionRequestData()
    override fun doDissolution(planId: Int) {
        RemoteRepository
            .onDissolution(dissolutionRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onDissolutionSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onDissolutionError()
                }
            })
    }


}