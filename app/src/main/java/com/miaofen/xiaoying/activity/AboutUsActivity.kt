package com.miaofen.xiaoying.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 关于我们页面
 */
class AboutUsActivity : BaseActivity() {

    override fun returnLayoutId() = R.layout.activity_about_us

    override fun initView() {
        super.initView()
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.about_us)
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, AboutUsActivity::class.java)
            context?.startActivity(intent)
        }
    }

}