package com.miaofen.xiaoying.fragment.home.hottest


import android.util.Log
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.fragment_newest.*

/**
 * 首页最新列表
 */
class NewestFragment : BaseMvpFragment<NewestContract.Presenter>(), NewestContract.View,
    RefreshLayout.SetOnRefresh {

    var mAdapter: ShareRecyclerAdapter? = null

    var list = ArrayList<HomeResponse.ContentBean>()

    override fun getLayoutResources() = R.layout.fragment_newest

    override fun initView() {
        super.initView()
        NewestPresenter(this)
        newest.setSetOnRefresh(this)
        newest.setEnableRefresh(true)
        newest.autoRefresh()
        mAdapter = ShareRecyclerAdapter(R.layout.newest_recycler_layout, list, activity)
        newest.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doNewest(pager, size)
    }

    override fun refresh(pager: Int, size: Int) {
        list.clear()
        mPresenter?.doNewest(pager, size)
    }

    override fun onNewestSuccess(data: HomeResponse?) {
        if (data?.content == null) {
            return
        }
        if (data.content.size == 0) {
            newest.setEnableLoadMore(false)
            return
        } else {
            newest.setEnableLoadMore(true)
        }
        for (item in data.content) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onNewestError() {

    }

}