package com.miaofen.xiaoying.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 报名页面
 */
class SignUpActivity : BaseActivity() {

    override fun returnLayoutId() = R.layout.activity_sign_up

    override fun initView() {
        super.initView()
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.text = "报名"
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, SignUpActivity::class.java)
            context?.startActivity(intent)
        }
    }


}