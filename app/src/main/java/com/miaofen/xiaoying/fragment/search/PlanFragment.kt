package com.miaofen.xiaoying.fragment.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.fragment.hot.HotRecyclerViewAdapter
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.fragment_plan.*


/**
 * 搜索计划
 */
class PlanFragment : BaseFragment() , RefreshLayout.SetOnRefresh {

    var mAdapter: PlanRecyclerViewAdapter? = null

    var list = ArrayList<String>()

    override fun getLayoutResources() = R.layout.fragment_plan

    override fun initView() {
        super.initView()
        plan_recycler.setSetOnRefresh(this)
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        mAdapter = PlanRecyclerViewAdapter(R.layout.plan_item_layout, list, activity)
        plan_recycler.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        mAdapter?.notifyDataSetChanged()
    }

    override fun refresh(pager: Int, size: Int) {
        list.clear()
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
    }

}