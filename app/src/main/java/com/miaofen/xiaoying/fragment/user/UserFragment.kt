package com.miaofen.xiaoying.fragment.user

import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.*
import com.miaofen.xiaoying.activity.personal.PersonalHomPagerActivity
import com.miaofen.xiaoying.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * 我的
 */
class UserFragment : BaseFragment() {

    override fun getLayoutResources() = R.layout.fragment_user

    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
        tv_about_us.setOnClickListener { AboutUsActivity.start(activity) }//关于我们
        linear_privacy.setOnClickListener { PrivacySettingsActivity.start(activity) }//隐私设置
        linear_feedback.setOnClickListener { FeedbackActivity.start(activity) }//意见反馈
        linear_authentication.setOnClickListener { VehicleCertificationActivity.start(activity) } //车辆认证
        image_head_portrait.setOnClickListener { MaterialsActivity.start(activity) }//编辑资料
        personal_pager.setOnClickListener { PersonalHomPagerActivity.start(activity) }//个人主页
    }

}