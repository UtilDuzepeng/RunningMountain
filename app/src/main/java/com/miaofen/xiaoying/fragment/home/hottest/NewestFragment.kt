package com.miaofen.xiaoying.fragment.home.hottest


import android.util.Log
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.utils.CacheUtils
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

    //纬度
    var latitude: Double? = null
    //经度
    var longitude: Double? = null

    override fun getLayoutResources() = R.layout.fragment_newest

    override fun initView() {
        super.initView()
        val readJson = CacheUtils.readJson(context, Constant.JSON_ADDRESS)
        this.latitude  = readJson.getDouble("lat")
        this.longitude  = readJson.getDouble("lon")
        NewestPresenter(this)
        newest.setSetOnRefresh(this)
        newest.setEnableRefresh(true)
        newest.autoRefresh()
        mAdapter = ShareRecyclerAdapter(R.layout.newest_recycler_layout, list, activity)
        mAdapter?.openLoadAnimation()
        newest.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        if (latitude != null && longitude != null) {
            mPresenter?.doNewest(pager, size, latitude!!, longitude!!)
        }
    }

    override fun refresh(pager: Int, size: Int) {
        if (latitude != null && longitude != null) {
            mPresenter?.doNewest(pager, size, latitude!!, longitude!!)
        }
    }

    override fun onDownNewestNullSuccess() {
        newest.setEnableLoadMore(false)//设置不能上啦刷新
    }

    override fun onDownNewestSuccess(data: HomeResponse?) {
        list.clear()
        for (item in data?.content!!) {
            list.add(item)
        }
        newest.setEnableLoadMore(true)
        mAdapter?.notifyDataSetChanged()
    }

    override fun onNewestSuccess(data: HomeResponse?) {
        newest.setEnableLoadMore(true)
        for (item in data?.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onNewestNullSuccess() {
        newest.setEnableLoadMore(false)
    }

    override fun onNewestError() {
        newest.setEnableLoadMore(false)
    }

}