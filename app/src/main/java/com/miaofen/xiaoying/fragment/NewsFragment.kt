package com.miaofen.xiaoying.fragment


import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.fragment.newest.NewestRecyclerViewAdapter
import com.miaofen.xiaoying.fragment.news.NewsRecyclerViewAdapter
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_newest.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.toobar_layout.*
import java.util.zip.Inflater


/**
 * 消息
 */
class NewsFragment : BaseFragment(), RefreshLayout.SetOnRefresh {


    var mAdapter: NewsRecyclerViewAdapter? = null

    var list = ArrayList<String>()

    override fun getLayoutResources() = R.layout.fragment_news

    override fun initView() {
        super.initView()
        news_recyclerview.setSetOnRefresh(this)
        title_bar_title.text = "消息"
        mAdapter = NewsRecyclerViewAdapter(R.layout.news_list_item, list, activity)
        val inflate = getLayoutInflater().inflate(R.layout.news_list_item_headl, null)
        mAdapter?.addHeaderView(inflate)
        news_recyclerview.recyclerView.adapter = mAdapter
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