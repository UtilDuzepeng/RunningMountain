package com.miaofen.xiaoying.fragment.user

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.*
import com.miaofen.xiaoying.activity.collection.CollectionListActivity
import com.miaofen.xiaoying.activity.fans.FansActivity
import com.miaofen.xiaoying.activity.follow.FocusOnYourselfActivity
import com.miaofen.xiaoying.activity.materia.MaterialsActivity
import com.miaofen.xiaoying.activity.materia.MaterialsActivity.Companion.start
import com.miaofen.xiaoying.activity.personal.PersonalHomPagerActivity
import com.miaofen.xiaoying.activity.privacy.PrivacySettingsActivity
import com.miaofen.xiaoying.activity.travelplan.TravelPlanActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.PersonalResponse
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * 我的
 */
class UserFragment : BaseMvpFragment<UserContract.Presenter>(), UserContract.View,
    OnRefreshLoadMoreListener {


    private var personalResponse: PersonalResponse? = null

    override fun getLayoutResources() = R.layout.fragment_user

    override fun initView() {
        super.initView()
        UserPresenter(this)
        mPresenter?.doPersonalCenter()
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.doPersonalCenter()
    }

    override fun initData() {
        super.initData()
        mRefreshLayout.setOnRefreshLoadMoreListener(this)
        mRefreshLayout.setEnableLoadMore(false)
        tv_about_us.setOnClickListener { AboutUsActivity.start(activity) }//关于我们
        linear_privacy.setOnClickListener { PrivacySettingsActivity.start(activity) }//隐私设置
        linear_feedback.setOnClickListener { FeedbackActivity.start(activity) }//意见反馈
        linear_authentication.setOnClickListener { VehicleCertificationActivity.start(activity) } //车辆认证
        image_head_portrait.setOnClickListener { MaterialsActivity.start(activity) }//编辑资料
        linear_collection_list.setOnClickListener { CollectionListActivity.start(activity) }//用户收藏列表
        linear_focus_on_yourself.setOnClickListener { FocusOnYourselfActivity.start(activity) }//关注列表
        linear_fans.setOnClickListener { FansActivity.start(activity) }//粉丝列表
        personal_pager.setOnClickListener {
            if (personalResponse != null && personalResponse?.userId != null) {
                PersonalHomPagerActivity.start(activity, personalResponse!!.userId!!)
            }
        }//个人主页
        line_travel_plan.setOnClickListener { TravelPlanActivity.start(activity) }//旅行计划
    }


    override fun onPersonalCenterSuccess(data: PersonalResponse) {
        this.personalResponse = data
        tv_nickName.text = data.nickName
        tv_fansNumber.text = "${data.fansNumber}"
        tv_followsNumber.text = "${data.followsNumber}"
        tv_collectionCount.text = "${data.collectionCount}"
        //标准圆形图片。
        Glide.with(context!!).load(data.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .fallback(R.drawable.zhanweitu)
            .error(R.drawable.error_zhanweitu)
            .into(image_head_portrait)
        if (data.motorcycle != null) {
            if (data.motorcycle!!) {
                tv_authentication.setText(R.string.authentication)
            } else {
                tv_authentication.setText(R.string.not_certified)
            }
        }
        mRefreshLayout.finishRefresh()
    }

    override fun onPersonalCenterError() {
        mRefreshLayout.finishRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPresenter?.doPersonalCenter()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

    }


}