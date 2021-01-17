package com.miaofen.xiaoying.fragment.home.hot


import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.utils.CacheUtils
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*


/**
 * 热门
 */
class HotFragment : BaseMvpFragment<HotContract.Presenter>(), HotContract.View,
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
        hot.recyclerView.adapter = mAdapter
        mAdapter?.openLoadAnimation()
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
        mAdapter?.setOnShareClick(this)
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



    //收藏
    override fun onCollection(entityId: Int?) {
        loadingDialog.showSuccess()
        mPresenter?.doCollection(entityId)
    }

    //收藏成功
    override fun onCollectionSuccess(data: String?) {
        if (latitude != null && longitude != null) {
            mPresenter?.doHot(1, 10, latitude!!, longitude!!)
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
            mPresenter?.doHot(1, 10, latitude!!, longitude!!)
        }
        loadingDialog.dismiss()
    }
    //取消收藏旅行计划失败
    override fun onCancelCollectionError() {
        loadingDialog.dismiss()
    }



}