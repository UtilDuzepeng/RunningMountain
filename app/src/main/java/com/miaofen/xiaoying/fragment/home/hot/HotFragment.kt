package com.miaofen.xiaoying.fragment.home.hot


import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.fragment_newest.*


/**
 * 热门
 */
class HotFragment : BaseMvpFragment<HotContract.Presenter>(), HotContract.View,
    RefreshLayout.SetOnRefresh {

    var mAdapter: ShareRecyclerAdapter? = null

    var list = ArrayList<HomeResponse.ContentBean>()

    override fun getLayoutResources() = R.layout.fragment_hot

    override fun initView() {
        super.initView()
        HotPresenter(this)
        hot.setSetOnRefresh(this)
        hot.setEnableRefresh(true)
        hot.autoRefresh()
        mAdapter = ShareRecyclerAdapter(R.layout.newest_recycler_layout, list, activity)
        hot.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doHot(pager, size)
    }

    override fun refresh(pager: Int, size: Int) {
        list.clear()
        mPresenter?.doHot(pager, size)
    }

    override fun onHotSuccess(data: HomeResponse?) {
        if (data?.content == null) {
            hot.setEnableLoadMore(false)
            return
        }
        if (data.content.size == 0) {
            hot.setEnableLoadMore(false)
            return
        } else {
            hot.setEnableLoadMore(true)
        }
        for (item in data.content) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onHotError() {

    }


}