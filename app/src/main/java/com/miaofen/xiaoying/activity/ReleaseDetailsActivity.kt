package com.miaofen.xiaoying.activity

import android.content.Context
import android.content.Intent
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity

/**
 * 发布详情
 */
class ReleaseDetailsActivity : BaseActivity() {

    override fun returnLayoutId() = R.layout.activity_release_details

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, ReleaseDetailsActivity::class.java)
            context?.startActivity(intent)
        }
    }
}