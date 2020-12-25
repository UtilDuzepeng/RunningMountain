package com.miaofen.xiaoying.activity.details

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.details.adapter.CommentRecyclerViewAdapter
import com.miaofen.xiaoying.activity.details.adapter.JoinsRecyclerAdapter
import com.miaofen.xiaoying.activity.details.adapter.PlanTripsRecyclerAdapter
import com.miaofen.xiaoying.activity.details.adapter.WantsRecyclerViewAdapter
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.request.DeleteCommentRequestData
import com.miaofen.xiaoying.common.data.bean.request.FabulousRequestData
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse
import com.miaofen.xiaoying.fragment.ImageAdapter
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.getCurrentTime
import com.miaofen.xiaoying.view.LoadingDialog
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_project_details.*
import kotlinx.android.synthetic.main.details_head_layout.*
import kotlinx.android.synthetic.main.toobar_layout.title_bar_back


/**
 * 旅行计划详情
 */
@Suppress("INACCESSIBLE_TYPE")
class ProjectDetailsActivity : BaseMvpActivity<ProjectDetailsContract.Presenter>(),
    ProjectDetailsContract.View, RefreshLayout.SetOnRefresh,
    CommentRecyclerViewAdapter.DeleteComment {

    //一级评论列表数据
    var list = ArrayList<OneCommentsResponse.ContentBean>()

    var planId: Int? = -1

    var commentRecyclerViewAdapter: CommentRecyclerViewAdapter? = null

    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    override fun returnLayoutId() = R.layout.activity_project_details

    override fun initView() {
        super.initView()
        loadingDialog.showLoading()
        refreshOneComments.setSetOnRefresh(this)
        refreshOneComments.setEnableRefresh(false)
        ProjectDetailsPresenter(this)
        planId = intent.getIntExtra(ID, -1)
        //一级评论适配器
        commentRecyclerViewAdapter =
            CommentRecyclerViewAdapter(R.layout.comment_item, list, this)
        refreshOneComments.recyclerView.adapter = commentRecyclerViewAdapter
        commentRecyclerViewAdapter?.setDeleteComment(this)
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
        //详情请求
        mPresenter?.doProjectDetails(planId)
        //一级评论请求
        mPresenter?.doOneComments(planId, 1, 10)
    }

    override fun onClick() {
        super.onClick()
        title_bar_back.setOnClickListener { finish() }

    }

    //轮播图
    override fun onPlanImages(planImages: ArrayList<ImagerDataBean>?) {
        details_banner.addBannerLifecycleObserver(this) //添加生命周期观察者
            .setAdapter(ImageAdapter(planImages))
            .indicator = CircleIndicator(this)
    }

    //发布人信息
    override fun onPublisherInfo(publisherInfo: DetailsResponse.PublisherInfoBean?) {
        //发布人头像
        Glide.with(this).load(publisherInfo?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(image_avatar) //标准圆形图片。
        //发布人名称
        tv_nickName.text = publisherInfo?.nickName
    }

    //计划详情 发布时间
    override fun onPlanDetailCreateTime(createTime: Long?) {
        tv_createTime.text = getCurrentTime(createTime!!)
    }

    //计划详情 按钮状态
    override fun onPlanDetailButtonInfo(buttonInfo: DetailsResponse.ButtonInfoBean?) {
        tv_sign_up.text = buttonInfo?.buttonName
        if (buttonInfo?.buttonAction != null && buttonInfo.buttonAction == 1) {
            //唤醒列表
        }
    }

    //计划详情 标题
    override fun onPlanDetailTitle(title: String?) {
        tv_title.text = title
    }

    //计划详情 内容
    override fun onPlanDetailContent(content: String?) {
        tv_content.text = content
    }

    //计划详情 希望人数
    override fun onPlanDetailExceptedNumber(exceptedNumber: Int?) {
        tv_exceptedNumber.text = "$exceptedNumber"
    }

    //计划详情 人均预算
    override fun onPlanDetailPerCapitaBudget(perCapitaBudget: Int?) {
        tv_perCapitaBudget.text = "$perCapitaBudget"
    }

    //费用方式
    override fun onPlanDetailCostMethod(costMethod: String?) {
        tv_costMethod.text = costMethod
    }

    //始发地
    override fun onPlanDetailPlaceOfDeparture(placeOfDeparture: String?) {
        tv_placeOfDeparture.text = placeOfDeparture
    }

    //目的地
    override fun onPlanDetailDestination(destination: String?) {
        tv_destination.text = destination
    }

    //始发地与我相遇距离
    override fun onUserPlanDistance(userPlanDistance: String?, type: Boolean) {
        if (!type) {
            tv_userPlanDistance.visibility = View.INVISIBLE
        }
        tv_userPlanDistance.text = userPlanDistance
    }

    //评论数量
    override fun onCommentCount(commentCount: String?) {
        tv_commentCount.text = commentCount
    }

    //起止时间
    override fun onStartTimeEndTime(time: String) {
        tv_startEndTime.text = time
    }

    //已报名人数
    override fun onJoins(joinsSize: String) {
        tv_registered.text = joinsSize
    }

    //途径地数量 途径地名列表
    override fun onPlanTrips(
        tvPlanTrips: String, dataPlanTrips: List<DetailsResponse.PlanTripsBean>?
    ) {
        tv_planTrips.visibility = View.VISIBLE
        planTrips_recycler.visibility = View.VISIBLE
        planTrips_view.visibility = View.VISIBLE
        tv_planTrips.text = tvPlanTrips
        val planTripsRecyclerAdapter = PlanTripsRecyclerAdapter(
            R.layout.channel_layout, dataPlanTrips, this
        )
        planTrips_recycler.adapter = planTripsRecyclerAdapter
    }

    //暂无途径地
    override fun onHindPlanTrips() {
        tv_planTrips.visibility = View.GONE
        planTrips_recycler.visibility = View.GONE
        planTrips_view.visibility = View.GONE
    }

    //想加入的人员信息  头像列表
    override fun onWants(tvWants: String, wants: List<DetailsResponse.WantsBean>?) {
        tv_wants.visibility = View.VISIBLE
        wants_recycler.visibility = View.VISIBLE
        wants_view.visibility = View.VISIBLE
        tv_wants.text = tvWants
        val wantsRecyclerViewAdapter =
            WantsRecyclerViewAdapter(R.layout.personnel_information, wants, this)
        wants_recycler.adapter = wantsRecyclerViewAdapter
    }

    //暂无想加入的人员
    override fun onHindWants() {
        tv_wants.visibility = View.GONE
        wants_recycler.visibility = View.GONE
        wants_view.visibility = View.GONE
    }

    //已报名人数
    override fun onJoinsData(tvJoinsName: String, joins: List<DetailsResponse.JoinsBean>?) {
        tv_joinsName.visibility = View.VISIBLE
        joins_Recyclerview.visibility = View.VISIBLE
        joins_view.visibility = View.VISIBLE
        tv_joinsName.text = tvJoinsName
        val joinsRecyclerAdapter =
            JoinsRecyclerAdapter(R.layout.personnel_information, joins, this)
        joins_Recyclerview.adapter = joinsRecyclerAdapter
    }

    //暂无报名人数
    override fun onHindJoinsData() {
        tv_joinsName.visibility = View.GONE
        joins_Recyclerview.visibility = View.GONE
        joins_view.visibility = View.GONE

    }


    //旅行计划详情  //请求成功
    override fun onProjectDetailsSuccess(data: DetailsResponse) {
        loadingDialog.showSuccess()
        loadingDialog.dismiss()
    }

    //请求失败
    override fun onProjectDetailsError() {
        loadingDialog.showFail()
        loadingDialog.dismiss()
    }


    /*------------一级评论列表-------------*/

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doOneComments(planId, pager, size)
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

    /*———————————————————————删除评论—————————————————————————*/
    private val deleteCommentRequestData = DeleteCommentRequestData()

    override fun onDelete(commentId: Long?) {
        loadingDialog.showLoading()
        deleteCommentRequestData.setCommentId(commentId)
        mPresenter?.doDeleteComment(deleteCommentRequestData)
    }


    override fun onDeleteCommentSuccess(data: String?) {
        mPresenter?.doOneComments(planId, 1, 10)
        commentRecyclerViewAdapter?.notifyDataSetChanged()
        loadingDialog.showSuccess()
        loadingDialog.dismiss()

    }

    override fun onDeleteCommentError() {
        loadingDialog.showFail()
        loadingDialog.dismiss()
    }

    /*——————————————————点赞评论——————————————————————*/
    private val fabulousRequestData = FabulousRequestData()
    override fun onOnClickFabulous(commentId: Long?) {
        loadingDialog.showLoading()
        fabulousRequestData.setCommentId(commentId)
        mPresenter?.doFabulous(fabulousRequestData)
    }

    override fun onFabulousSuccess(data: String?) {
        mPresenter?.doOneComments(planId, 1, 10)
        commentRecyclerViewAdapter?.notifyDataSetChanged()
        loadingDialog.showSuccess()
        loadingDialog.dismiss()
    }

    override fun onFabulousError() {
        loadingDialog.showFail()
        loadingDialog.dismiss()
    }

    /*————————————————————取消点赞———————————————————*/

    override fun onUnStar() {
        loadingDialog.showLoading()
        mPresenter?.doUnStar()
    }

    override fun onUnStarSuccess(data: String?) {
        mPresenter?.doOneComments(planId, 1, 10)
        commentRecyclerViewAdapter?.notifyDataSetChanged()
        loadingDialog.showSuccess()
        loadingDialog.dismiss()
    }

    override fun onUnStarError() {
        loadingDialog.showFail()
        loadingDialog.dismiss()
    }

}