package com.miaofen.xiaoying.activity.details

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.request.DetailsRequestData
import com.miaofen.xiaoying.common.data.bean.request.OneCommentsData
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse
import com.miaofen.xiaoying.fragment.ImageAdapter
import com.miaofen.xiaoying.utils.getCurrentTime
import com.miaofen.xiaoying.view.RefreshLayout
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_project_details.*
import kotlinx.android.synthetic.main.details_head_layout.*
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.toobar_layout.title_bar_back


/**
 * 旅行计划详情
 */
@Suppress("INACCESSIBLE_TYPE")
class ProjectDetailsActivity : BaseMvpActivity<ProjectDetailsContract.Presenter>(),
    ProjectDetailsContract.View, RefreshLayout.SetOnRefresh {
    //请求计划详情body
    val detailsRequestData = DetailsRequestData()

    //请求计划详情一级评论body
    val oneCommentsData = OneCommentsData()

    //轮播图集合
    private var imagePath = ArrayList<ImagerDataBean>()
    val imagerDataBean = ImagerDataBean()

    //一级评论列表数据
    var list = ArrayList<OneCommentsResponse.ContentBean>()

    var commentRecyclerViewAdapter: CommentRecyclerViewAdapter? = null

    override fun returnLayoutId() = R.layout.activity_project_details

    override fun initView() {
        super.initView()
        ProjectDetailsPresenter(this)
        //详情请求
        detailsRequestData.setPlanId(intent.getIntExtra(ID, -1))
        mPresenter?.doProjectDetails(detailsRequestData)
        //一级评论请求
        refreshOneComments.setSetOnRefresh(this)
        refreshOneComments.setEnableRefresh(false)
        oneCommentsData.setPlanId(intent.getIntExtra(ID, -1))
        oneCommentsData.setPage(1)
        oneCommentsData.setSize(10)
        mPresenter?.doOneComments(oneCommentsData)
        commentRecyclerViewAdapter =
            CommentRecyclerViewAdapter(R.layout.comment_item, list, this)
        refreshOneComments.recyclerView.adapter = commentRecyclerViewAdapter
        //途径地列表
        planTrips_recycler.layoutManager = LinearLayoutManager(this)
        //想约的人员列表
        val gridLayoutManager =
            GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        wants_recycler.layoutManager = gridLayoutManager
        val joinsGridLayoutManager =
            GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false)
        joinsGridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        //已报名人员列表
        joins_Recyclerview.layoutManager = joinsGridLayoutManager
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
    }

    override fun onProjectDetailsSuccess(data: DetailsResponse) {
        //轮播图集合
        if (data.planImages != null || data.planImages!!.isNotEmpty()) {
            for (item in data.planImages!!) {
                imagerDataBean.url = item.imageUrl
                imagePath.add(imagerDataBean)
            }
        }
        details_banner.addBannerLifecycleObserver(this) //添加生命周期观察者
            .setAdapter(ImageAdapter(imagePath))
            .indicator = CircleIndicator(this)
        //发布人信息
        if (data.publisherInfo != null) {
            //发布人头像
            Glide.with(this).load(data.publisherInfo?.avatarUrl)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(image_avatar) //标准圆形图片。
            //发布人名称
            tv_nickName.text = data.publisherInfo?.nickName
        }
        //计划详情
        if (data.planDetail != null) {
            //发布时间
            if (data.planDetail?.createTime != null) {
                tv_createTime.text = getCurrentTime(data.planDetail!!.createTime!!)
            }
            //按钮状态
            if (data.planDetail?.planStatus != null) {
                when (data.planDetail!!.planStatus!!) {
                    0 -> {
                        tv_sign_up.text = "无"
                    }
                    1 -> {
                        tv_sign_up.text = "唤醒列表"
                    }
                    2 -> {
                        tv_sign_up.text = "管理"
                    }
                    3 -> {
                        tv_sign_up.text = "小队"
                    }
                    4 -> {
                        tv_sign_up.text = "报名列表"
                    }
                    5 -> {
                        tv_sign_up.text = "编辑"
                    }
                    6 -> {
                        tv_sign_up.text = "解散"
                    }
                    7 -> {
                        tv_sign_up.text = "退出"
                    }
                }
            }
            //名称
            tv_title.text = data.planDetail?.title
            //内容
            tv_content.text = data.planDetail?.content
            //希望人数
            if (data.planDetail?.exceptedNumber != null) {
                tv_exceptedNumber.text = "${data.planDetail?.exceptedNumber}"
            }
            //人均预算
            if (data.planDetail?.perCapitaBudget != null) {
                tv_perCapitaBudget.text = "${data.planDetail?.perCapitaBudget}"
            }
            //费用方式
            if (data.planDetail?.costMethod != null) {
                when (data.planDetail?.costMethod) {
                    1 -> {
                        tv_costMethod.text = "AA"
                    }
                    2 -> {
                        tv_costMethod.text = "男A女免"
                    }
                    3 -> {
                        tv_costMethod.text = "免费参加"
                    }
                }
            }
            //始发地
            tv_placeOfDeparture.text = data.planDetail?.placeOfDeparture
            //目的地
            tv_destination.text = data.planDetail?.destination

        }

        //始发地与我相遇距离
        if (data.userPlanDistance != null) {
            tv_userPlanDistance.text = "距离 ${data.userPlanDistance} km"
        } else {
            tv_userPlanDistance.text = "距离 - km"
        }
        //评论数量
        if (data.commentCount != null) {
            tv_commentCount.text = "评论(${data.commentCount})"
        } else {
            tv_commentCount.text = "评论(${0})"
        }
        //起止时间
        if (data.planDetail?.startTime != null && data.planDetail?.endTime != null) {
            tv_startEndTime.text =
                "${getCurrentTime(data.planDetail!!.startTime!!)} 至 ${getCurrentTime(data.planDetail!!.endTime!!)}"
        }
        //已报名人数
        if (data.joins != null) {
            tv_registered.text = "${data.joins?.size}"
        } else {
            tv_registered.text = "${0}"
        }
        //途径地数量
        if (data.planTrips != null) {
            tv_planTrips.text = "途径地"
            tv_planTrips.append("(${data.planTrips?.size})")
        }
        //途径地名列表
        val planTripsRecyclerAdapter =
            PlanTripsRecyclerAdapter(R.layout.channel_layout, data.planTrips, this)
        planTrips_recycler.adapter = planTripsRecyclerAdapter
        //想加入的人员信息
        var wantsMale: Int = 0
        var wantsFemale: Int = 0
        var wantsSecrecy: Int = 0
        if (data.wants != null) {
            for (item in data.wants!!) {
                when (item.gender) {
                    1 -> {
                        ++wantsMale
                    }
                    2 -> {
                        ++wantsFemale
                    }
                    0 -> {
                        ++wantsSecrecy
                    }
                }
            }
        }
        tv_wants.text = "想约的"
        tv_wants.append("( $wantsMale")
        tv_wants.append(" 男 ")
        tv_wants.append("$wantsFemale")
        tv_wants.append("女 ")
        tv_wants.append("$wantsSecrecy")
        tv_wants.append("性别保密)")
        //头像列表
        val wantsRecyclerViewAdapter =
            WantsRecyclerViewAdapter(R.layout.personnel_information, data.wants, this)
        wants_recycler.adapter = wantsRecyclerViewAdapter
        //已报名人数
        var joinsMale: Int = 0
        var joinsFemale: Int = 0
        var joinsSecrecy: Int = 0
        if (data.joins != null) {
            tv_joinsName.text = "${data.joins?.size}"
            tv_joinsName.append("人已报名")
            for (item in data.joins!!) {
                when (item.gender) {
                    1 -> {
                        ++joinsMale
                    }
                    2 -> {
                        ++joinsFemale
                    }
                    0 -> {
                        ++joinsSecrecy
                    }
                }
            }
            tv_joinsName.append("( $joinsMale")
            tv_joinsName.append(" 男 ")
            tv_joinsName.append("$joinsFemale")
            tv_joinsName.append("女 ")
            tv_joinsName.append("$joinsSecrecy")
            tv_joinsName.append("性别保密)")
            val joinsRecyclerAdapter =
                JoinsRecyclerAdapter(R.layout.personnel_information, data.joins, this)
            joins_Recyclerview.adapter = joinsRecyclerAdapter
        }


    }

    override fun onProjectDetailsError() {

    }

    /*------------一级评论列表-------------*/

    override fun loadMore(pager: Int, size: Int) {
        oneCommentsData.setPage(pager)
        oneCommentsData.setSize(size)
        mPresenter?.doOneComments(oneCommentsData)
    }

    override fun refresh(pager: Int, size: Int) {

    }


    override fun onOneCommentsSuccess(data: OneCommentsResponse?) {
        if (data?.content == null) {
            refreshOneComments.setEnableLoadMore(false)
            return
        }
        if (data.content!!.size == 0) {
            refreshOneComments.setEnableLoadMore(false)
            return
        } else {
            refreshOneComments.setEnableLoadMore(true)
        }
        for (item in data.content!!) {
            list.add(item)
        }
        commentRecyclerViewAdapter?.notifyDataSetChanged()

    }

    override fun onOneCommentsError() {

    }

    /*--------------------------------*/
    companion object {
        const val ID = "id"
        fun start(context: Context?, id: Int?) {
            val intent = Intent(context, ProjectDetailsActivity::class.java)
            intent.putExtra(ID, id)
            context?.startActivity(intent)
        }
    }


}