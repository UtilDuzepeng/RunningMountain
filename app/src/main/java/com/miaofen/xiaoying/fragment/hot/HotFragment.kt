package com.miaofen.xiaoying.fragment.hot


import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.fragment.newest.NewestRecyclerViewAdapter
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*


/**
 * 热门
 */
class HotFragment : BaseFragment(), RefreshLayout.SetOnRefresh {

    var mAdapter: HotRecyclerViewAdapter? = null

    var list = ArrayList<String>()

    override fun getLayoutResources() = R.layout.fragment_hot

    override fun initView() {
        super.initView()
        hot.setSetOnRefresh(this)
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        mAdapter = HotRecyclerViewAdapter(R.layout.newest_recycler_layout, list, activity)
        hot.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
    }

    override fun refresh(pager: Int, size: Int) {
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
    }


}