package com.miaofen.xiaoying.fragment.home.hot


import android.util.Log
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.search.back.ObserverManager
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.utils.CacheUtils
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*


/**
 * 热门
 */
class HotFragment : BaseMvpFragment<HotContract.Presenter>(), HotContract.View,
    RefreshLayout.SetOnRefresh {

    var mAdapter: ShareRecyclerAdapter? = null

    var list = ArrayList<HomeResponse.ContentBean>()


    //纬度
    var latitude: Double? = null
    //经度
    var longitude: Double? = null

    override fun getLayoutResources() = R.layout.fragment_hot

    override fun initView() {
        super.initView()
        val readJson = CacheUtils.readJson(context, Constant.JSON_ADDRESS)
        this.latitude  = readJson.getDouble("lat")
        this.longitude  = readJson.getDouble("lon")
        HotPresenter(this)
        hot.setSetOnRefresh(this)
        hot.setEnableRefresh(true)
        hot.autoRefresh()
        mAdapter = ShareRecyclerAdapter(R.layout.newest_recycler_layout, list, activity)
        mAdapter?.openLoadAnimation();
        hot.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        if (latitude != null && longitude != null) {
            mPresenter?.doHot(pager, size, latitude!!, longitude!!)
        }
    }

    override fun refresh(pager: Int, size: Int) {
        if (latitude != null && longitude != null) {
            mPresenter?.doHot(pager, size, latitude!!, longitude!!)
        }
    }

    //下拉刷新 无数据
    override fun onDownHotNullSuccess() {
        hot.setEnableLoadMore(false)//设置不能上啦刷新
    }

    //下拉刷新 有数据
    override fun onDownHotSuccess(data: HomeResponse?) {
        list.clear()
        for (item in data?.content!!) {
            list.add(item)
        }
        hot.setEnableLoadMore(true)
        mAdapter?.notifyDataSetChanged()
    }

    //上拉加载 有数据
    override fun onHotSuccess(data: HomeResponse?) {
        hot.setEnableLoadMore(true)
        for (item in data?.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    //上拉加载 无数据
    override fun onHotNullSuccess() {
        hot.setEnableLoadMore(false)
    }

    /**
     * 请求错误
     */
    override fun onHotError() {
        hot.setEnableLoadMore(false)
    }

}