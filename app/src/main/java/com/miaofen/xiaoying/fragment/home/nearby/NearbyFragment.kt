package com.miaofen.xiaoying.fragment.home.nearby


import android.util.Log
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.fragment_nearby.*
import kotlinx.android.synthetic.main.fragment_newest.*

/**
 * 附近
 */
class NearbyFragment : BaseMvpFragment<NearbyContract.Presenter>(), NearbyContract.View,
    RefreshLayout.SetOnRefresh {

    var mAdapter: ShareRecyclerAdapter? = null

    var list = ArrayList<HomeResponse.ContentBean>()

    override fun getLayoutResources() = R.layout.fragment_nearby

    override fun initView() {
        super.initView()
        NearbyPresenter(this)
        nearby.setSetOnRefresh(this)
        nearby.setEnableRefresh(true)
        nearby.autoRefresh()
        mAdapter = ShareRecyclerAdapter(R.layout.newest_recycler_layout, list, activity)
        nearby.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doNearby(pager, size)
    }

    override fun refresh(pager: Int, size: Int) {
        list.clear()
        mPresenter?.doNearby(pager, size)
    }

    override fun onNearbySuccess(data: HomeResponse?) {
        if (data?.content == null) {
            return
        }
        if (data.content.size == 0) {
            nearby.setEnableLoadMore(false)
            return
        } else {
            nearby.setEnableLoadMore(true)
        }
        for (item in data.content) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onNearbyError() {

    }
}