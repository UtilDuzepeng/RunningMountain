package com.miaofen.xiaoying.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import kotlinx.android.synthetic.main.activity_privacy_settings.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 隐私设置
 */
class PrivacySettingsActivity : BaseActivity() {

    override fun returnLayoutId() = R.layout.activity_privacy_settings

    override fun initView() {
        super.initView()
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.text = "隐私设置"
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
        swit_exhibition.setOnClickListener {
//            swit_exhibition.setChecked(true);
        } //认证车辆是否展示
        swit_message_notification.setOnClickListener {
            val checked = swit_message_notification.isChecked

        }//是否接收消息通知
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, PrivacySettingsActivity::class.java)
            context?.startActivity(intent)
        }
    }

}