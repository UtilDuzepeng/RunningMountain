package com.miaofen.xiaoying.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import kotlinx.android.synthetic.main.details_head_layout.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 旅行计划详情
 */
class ProjectDetailsActivity : BaseActivity() {

    override fun returnLayoutId() = R.layout.activity_project_details

    override fun initView() {
        super.initView()
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.text = "旅行计划详情"
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
        tv_sign_up.setOnClickListener { SignUpActivity.start(this) }//报名

    }


    companion object {
         fun start(context: Context?) {
            val intent = Intent(context, ProjectDetailsActivity::class.java)
            context?.startActivity(intent)
        }
    }

}