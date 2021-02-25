package com.miaofen.xiaoying.activity.privacy

import android.content.Context
import android.content.Intent
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.response.ConfigurationDetailsResponse
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.view.LoadingView
import kotlinx.android.synthetic.main.activity_privacy_settings.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 隐私设置
 */
class PrivacySettingsActivity : BaseMvpActivity<PrivacySettingsContract.Presenter>(),
    PrivacySettingsContract.View {

    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    override fun returnLayoutId() = R.layout.activity_privacy_settings

    override fun initView() {
        super.initView()
        loadingDialog.showLoading()
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.text = "隐私设置"
        PrivacySettingsPresenter(this)
        mPresenter?.doConfigurationDetails()
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
        //是否展示计划
        swit_exhibition.setmOnCheckedChangeListener {
            ToastUtils.showToast("是否展示计划" +swit_exhibition.isChecked )
            loadingDialog.showLoading()
            if (swit_exhibition.isChecked) {
                mPresenter?.doExhibitionPlan(false)
            } else {
                mPresenter?.doExhibitionPlan(true)
            }
        }
        //是否展示资料
        swit_data.setmOnCheckedChangeListener {
            ToastUtils.showToast("是否展示资料" +swit_data.isChecked )
            loadingDialog.showLoading()
            if (swit_data.isChecked) {
                mPresenter?.doExhibitionMaterials(false)
            } else {
                mPresenter?.doExhibitionMaterials(true)
            }
        }
        //是否接收消息通知
        swit_message_notification.setmOnCheckedChangeListener {
            ToastUtils.showToast("是否接收消息通知" +swit_message_notification.isChecked )
            loadingDialog.showLoading()
            if (swit_message_notification.isChecked) {
                mPresenter?.doReceiveMessages(false)
            } else {
                mPresenter?.doReceiveMessages(true)
            }
        }
    }

    //配置详情成功
    override fun onConfigurationDetailsSuccess(data: ConfigurationDetailsResponse?) {
        //接收推送消息
        swit_message_notification.isChecked = data!!.receiveNotificationMessage
        //展示个人资料
        swit_data.isChecked = data.showPersonalInformation
        //展示旅行计划
        swit_exhibition.isChecked = data.showTravelPlan
        loadingDialog.dismiss()
    }

    //配置详情失败
    override fun onConfigurationDetailsError() {
        loadingDialog.dismiss()
    }


    //是否展示计划成功
    override fun onExhibitionPlanSuccess(data: Boolean?) {
        mPresenter?.doConfigurationDetails()
    }

    //是否展示计划失败
    override fun onExhibitionPlanError() {
        loadingDialog.dismiss()
    }

    //是否展示资料成功
    override fun onExhibitionMaterialsSuccess(data: Boolean?) {
        mPresenter?.doConfigurationDetails()
    }

    //是否展示资料失败
    override fun onExhibitionMaterialsError() {
        loadingDialog.dismiss()
    }

    //是否接收消息成功
    override fun onReceiveMessagesSuccess(data: Boolean?) {
        mPresenter?.doConfigurationDetails()
    }

    //是否接收消息失败
    override fun onReceiveMessagesError() {
        loadingDialog.dismiss()
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, PrivacySettingsActivity::class.java)
            context?.startActivity(intent)
        }
    }


}