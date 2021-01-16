package com.miaofen.xiaoying.fragment.home.nearby


import android.util.Log
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.utils.CacheUtils
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

    //纬度
    var latitude: Double? = null

    //经度
    var longitude: Double? = null


    override fun getLayoutResources() = R.layout.fragment_nearby

    override fun initView() {
        super.initView()
        val readJson = CacheUtils.readJson(context, Constant.JSON_ADDRESS)
        this.latitude = readJson.getDouble("lat")
        this.longitude = readJson.getDouble("lon")
        NearbyPresenter(this)
        nearby.setSetOnRefresh(this)
        nearby.setEnableRefresh(true)
        nearby.autoRefresh()
        mAdapter = ShareRecyclerAdapter(R.layout.newest_recycler_layout, list, activity)
        nearby.recyclerView.adapter = mAdapter
        mAdapter?.openLoadAnimation()
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
    }

    override fun loadMore(pager: Int, size: Int) {
        if (latitude != null && longitude != null) {
            mPresenter?.doNearby(pager, size, latitude!!, longitude!!)
        }
    }

    override fun refresh(pager: Int, size: Int) {
        if (latitude != null && longitude != null) {
            mPresenter?.doNearby(pager, size, latitude!!, longitude!!)
        }
    }

    override fun onDownNearbytNullSuccess() {
        nearby.setEnableLoadMore(false)//设置不能上啦刷新
    }

    override fun onDownNearbySuccess(data: HomeResponse?) {
        list.clear()
        for (item in data?.content!!) {
            list.add(item)
        }
        nearby.setEnableLoadMore(true)
        mAdapter?.notifyDataSetChanged()
    }

    override fun onNearbySuccess(data: HomeResponse?) {
        nearby.setEnableLoadMore(true)
        for (item in data?.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onNearbyNullSuccess() {
        nearby.setEnableLoadMore(false)
    }

    override fun onNearbyError() {
        nearby.setEnableLoadMore(false)
    }

}