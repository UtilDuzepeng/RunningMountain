package com.miaofen.xiaoying.fragment.search


import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_use.*


/**
 * 搜索用户界面
 * */
class UseFragment : BaseFragment(), RefreshLayout.SetOnRefresh {

    var mAdapter: UseRecyclerViewAdapter? = null

    var list = ArrayList<String>()

    override fun getLayoutResources() = R.layout.fragment_use


    override fun initView() {
        super.initView()
        use_recycler.setSetOnRefresh(this)
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        mAdapter = UseRecyclerViewAdapter(R.layout.use_item_layout, list, activity)
        use_recycler.recyclerView.adapter = mAdapter
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