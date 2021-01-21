package com.miaofen.xiaoying.activity.details

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.SignUpActivity
import com.miaofen.xiaoying.activity.details.adapter.CommentRecyclerViewAdapter
import com.miaofen.xiaoying.activity.details.adapter.JoinsRecyclerAdapter
import com.miaofen.xiaoying.activity.details.adapter.PlanTripsRecyclerAdapter
import com.miaofen.xiaoying.activity.details.adapter.WantsRecyclerViewAdapter
import com.miaofen.xiaoying.activity.details.commdia.CommentDialog
import com.miaofen.xiaoying.activity.details.replycomm.ReplyDialog
import com.miaofen.xiaoying.activity.details.tube.AdministrationDialog
import com.miaofen.xiaoying.activity.signup.SignUpListActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.request.DeleteCommentRequestData
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse
import com.miaofen.xiaoying.fragment.ImageAdapter
import com.miaofen.xiaoying.utils.*
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_project_details.*
import kotlinx.android.synthetic.main.details_head_layout.*


/**
 * 旅行计划详情  commentDialog
 */
@Suppress("INACCESSIBLE_TYPE")
class ProjectDetailsActivity : BaseMvpActivity<ProjectDetailsContract.Presenter>(),
    ProjectDetailsContract.View, RefreshLayout.SetOnRefresh,
    CommentRecyclerViewAdapter.DeleteComment, CommentDialog.ShowInput,
    AdministrationDialog.AdministrationInput, ReplyDialog.OnClickReply {

    var bottomDialogFr: CommentDialog? = null//评论底部弹窗

    var replyDialog: ReplyDialog? = null//一级回复列表弹窗

    var administrationDialog: AdministrationDialog? = null//列表底部弹窗

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
        planId = intent.getIntExtra(ID, -1)
        loadingDialog.showLoading()
        bottomDialogFr = CommentDialog(this,0, planId)
        bottomDialogFr?.setShowInput(this)
        refreshOneComments.setSetOnRefresh(this)
        refreshOneComments.setEnableRefresh(false)
        ProjectDetailsPresenter(this)
        //一级评论适配器
        commentRecyclerViewAdapter =
            CommentRecyclerViewAdapter(R.layout.comment_item, list, this)
        refreshOneComments.recyclerView.adapter = commentRecyclerViewAdapter
        commentRecyclerViewAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
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
//        //详情请求
//        mPresenter?.doProjectDetails(planId)
//        //一级评论请求
//        mPresenter?.doOneComments(planId, 1, 10)
    }

    override fun onClick() {
        super.onClick()
        title_bar_back.setOnClickListener { finish() }

        //点击评论
        tv_commentContent.setOnClickListener {
            bottomDialogFr?.show(supportFragmentManager, "DF")
        }

    }

    override fun onResume() {
        super.onResume()
        //详情请求
        mPresenter?.doProjectDetails(planId)
        //一级评论请求
        mPresenter?.doOneComments(planId, 1, 10)
        loadingDialog.showLoading()
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
    override fun onPlanDetailButtonInfo(
        buttonInfo: DetailsResponse.ButtonInfoBean?, planDetailBean: DetailsResponse.PlanDetailBean?
    ) {
        tv_sign_up.text = buttonInfo?.buttonName
        if (buttonInfo?.buttonAction != null && buttonInfo.buttonAction == 1) {//报名
            tv_sign_up.setOnClickListener {
                //报名
                SignUpActivity.start(this,planDetailBean?.planId)
            }
        } else if (buttonInfo?.buttonAction != null && buttonInfo.buttonAction == 2) {//唤醒列表
            tv_sign_up.setOnClickListener {
                if (tv_sign_up.text.toString() == "管理") {
                    if (administrationDialog == null) {
                        administrationDialog = AdministrationDialog(buttonInfo.subButtonInfo)
                        administrationDialog?.setAdministrationInput(this)
                    }
                    administrationDialog?.show(supportFragmentManager, "DF")
                } else if (tv_sign_up.text.toString() == "退出") {
                    ToastUtils.showToast("退出")
                }

            }
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
//        mPresenter?.doOneComments(planId, pager, size)
    }

    //一级评论列表下拉成功 没有数据
    override fun onDownOneCommentsNullSuccess() {
        refreshOneComments.setEnableLoadMore(false)
    }

    //一级评论列表下拉成功 有数据
    override fun onDownOneCommentsSuccess(data: OneCommentsResponse?) {
        list.clear()
        for (item in data?.content!!) {
            list.add(item)
        }
        commentRecyclerViewAdapter?.notifyDataSetChanged()
    }

    //一级评论列表上啦加载 有数据
    override fun onOneCommentsSuccess(data: OneCommentsResponse?) {
        for (item in data?.content!!) {
            list.add(item)
        }
        commentRecyclerViewAdapter?.notifyDataSetChanged()
    }

    //上啦加载 没有数据
    override fun onOneCommentsNullSuccess() {
        refreshOneComments.setEnableLoadMore(false)
    }

    //加载错误
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
    override fun onDelete(commentId: Long?) {
        loadingDialog.showLoading()
        mPresenter?.doDeleteComment(commentId)
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
    override fun onOnClickFabulous(commentId: Long?) {
        loadingDialog.showLoading()
        mPresenter?.doFabulous(commentId)
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

    override fun onUnStar(commentId: Long?) {
        loadingDialog.showLoading()
        mPresenter?.doUnStar(commentId)
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

    //点击回复
    override fun onReply(commentId: Long?) {
        Log.e("TAG","评论编号：" + commentId)
        if (replyDialog == null) {
            replyDialog = ReplyDialog(commentId,planId,this)
            replyDialog?.setOnClickReply(this)
        }

        replyDialog?.show(supportFragmentManager, "DF")
    }


    //开启软件盘
    override fun editTextShowInput(editText: EditText?) {
        showInput(editText!!)
    }

    //关闭软键盘
    override fun editTextHintInput() {
        loadingDialog.setTipMsg("正在加载")
        loadingDialog.showFail()
    }

    //发表评论成功 刷新列表
    override fun loadAnimation(editText: EditText?) {
        hideInput(editText, this)
        bottomDialogFr?.dismiss()
        //详情请求
        mPresenter?.doProjectDetails(planId)
        //一级评论请求
        mPresenter?.doOneComments(planId, 1, 10)
        loadingDialog.dismiss()
    }


    //发表评论失败toase
    override fun hideAnimation() {
        loadingDialog.dismiss()
    }

    //关闭评论弹窗
    override fun closeCommentDialog(editText: EditText?) {
        hideInput(editText, this)
        bottomDialogFr?.dismiss()
    }

    /*--------管理弹窗------------*/
    //管理弹窗关闭
    override fun closeAdministrationDialog() {
        administrationDialog?.dismiss()
    }

    //小队
    override fun administrationTeam() {
        ToastUtils.showToast("小队")
    }

    //报名列表
    override fun administrationSignUp() {
        SignUpListActivity.start(this, planId)
        administrationDialog?.dismiss()
    }

    //编辑
    override fun administrationEdit() {
        ToastUtils.showToast("编辑")
    }

    //解散
    override fun administrationDissolution() {
        ToastUtils.showToast("解散")
    }

    /*---------查看1级计划评论回复列表--------------*/
    override fun onNumberReplies() {
        replyDialog?.dismiss()
        replyDialog = null
    }


}