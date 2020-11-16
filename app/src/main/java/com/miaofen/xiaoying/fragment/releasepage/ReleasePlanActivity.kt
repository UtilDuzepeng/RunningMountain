package com.miaofen.xiaoying.fragment.releasepage

import android.content.Context
import android.content.Intent
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity

/**
 * 发布计划页面
 */
class ReleasePlanActivity : BaseActivity() {

    override fun returnLayoutId() = R.layout.activity_release_plan


    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, ReleasePlanActivity::class.java)
            context?.startActivity(intent)
        }
    }

}