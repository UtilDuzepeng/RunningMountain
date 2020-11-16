package com.miaofen.xiaoying.fragment


import android.util.Log
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.utils.FloatBtnUtil
import kotlinx.android.synthetic.main.toobar_layout.*


/**
 * 发布
 */
class ReleaseFragment : BaseFragment() {

    override fun getLayoutResources() = R.layout.fragment_release

    override fun initView() {
        super.initView()
        title_bar_title.text = "发布"
        val floatBtnUtil = FloatBtnUtil(this.activity)
//        floatBtnUtil.setFloatView(release_text, linear_title)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("TAG","当前页面 ： " + isVisibleToUser)

    }

}