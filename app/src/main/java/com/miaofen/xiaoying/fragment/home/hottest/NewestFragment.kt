package com.miaofen.xiaoying.fragment.home.hottest


import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.utils.CacheUtils
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_newest.*

/**
 * 首页最新列表
 */
class NewestFragment : BaseMvpFragment<NewestContract.Presenter>(), NewestContract.View,
    RefreshLayout.SetOnRefresh, ShareRecyclerAdapter.OnShareClick {

    var mAdapter: ShareRecyclerAdapter? = null

    var list = ArrayList<HomeResponse.ContentBean>()

    //纬度
    var latitude: Double? = null
    //经度
    var longitude: Double? = null

    private val loadingDialog: LoadingView by lazy {
        LoadingView(activity).apply {
            setTipMsg("正在加载")
        }
    }

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
        newest.recyclerView.adapter = mAdapter
        mAdapter?.openLoadAnimation()
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
        mAdapter?.setOnShareClick(this)
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


    //收藏
    override fun onCollection(entityId: Int?) {
        loadingDialog.showSuccess()
        mPresenter?.doCollection(entityId)
    }

    //收藏成功
    override fun onCollectionSuccess(data: String?) {
        if (latitude != null && longitude != null) {
            mPresenter?.doNewest(1, 10, latitude!!, longitude!!)
        }
        loadingDialog.dismiss()
    }

    //收藏失败
    override fun onCollectionError() {
        loadingDialog.dismiss()
    }

    //取消收藏
    override fun onCancelCollection(entityId: Int?) {
        loadingDialog.showSuccess()
        mPresenter?.doCancelCollection(entityId)
    }

    //取消收藏旅行计划成功
    override fun onCancelCollectionSuccess(data: String?) {
        if (latitude != null && longitude != null) {
            mPresenter?.doNewest(1, 10, latitude!!, longitude!!)
        }
        loadingDialog.dismiss()
    }
    //取消收藏旅行计划失败
    override fun onCancelCollectionError() {
        loadingDialog.dismiss()
    }


}