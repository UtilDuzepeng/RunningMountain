package com.miaofen.xiaoying.fragment.newest


import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_newest.*

/**
 *最新
 */
class NewestFragment : BaseFragment(), RefreshLayout.SetOnRefresh{


    var mAdapter: NewestRecyclerViewAdapter? = null

    var list = ArrayList<String>()
    override fun getLayoutResources() = R.layout.fragment_newest

    override fun initView() {
        super.initView()
        newest.setSetOnRefresh(this)
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        mAdapter = NewestRecyclerViewAdapter(R.layout.newest_recycler_layout, list, activity)
        newest.recyclerView.adapter = mAdapter
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