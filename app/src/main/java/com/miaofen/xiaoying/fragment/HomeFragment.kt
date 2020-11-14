package com.miaofen.xiaoying.fragment


import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.main_backdrop
import kotlinx.android.synthetic.main.home_toolbar_layout.*

/**
 * 首页
 */
class HomeFragment : BaseFragment() {


    override fun getLayoutResources()=R.layout.fragment_home

    override fun initView() {
        super.initView()
        setTitleToCollapsingToolbarLayout()
        setItem()
    }
    /**
     * 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private fun setTitleToCollapsingToolbarLayout() {
        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset <= -main_backdrop.getHeight() / 2) {
                toolbar.visibility = View.VISIBLE
            } else {
                toolbar.visibility = View.INVISIBLE
            }
        })
    }

    private fun setItem() {
        val list = listOf<String>("最新", "热门","附近")
        business_tablayout.getTabAt(0)?.text = list[0]
        business_tablayout.getTabAt(1)?.text = list[1]
        business_tablayout.getTabAt(2)?.text = list[3]
    }


}