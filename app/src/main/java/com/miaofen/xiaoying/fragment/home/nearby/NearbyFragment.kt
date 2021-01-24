package com.miaofen.xiaoying.fragment.home.nearby


import com.chad.library.adapter.base.BaseQuickAdapter
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.fragment.home.shareadapter.ShareRecyclerAdapter
import com.miaofen.xiaoying.utils.CacheUtils
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_nearby.*

/**
 * 附近
 */
class NearbyFragment : BaseMvpFragment<NearbyContract.Presenter>(), NearbyContract.View,
    RefreshLayout.SetOnRefresh , ShareRecyclerAdapter.OnShareClick {

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
        mAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        nearby.recyclerView.adapter = mAdapter
        mAdapter?.setOnShareClick(this)
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
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
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



    //收藏
    override fun onCollection(entityId: Int?) {
        loadingDialog.showSuccess()
        mPresenter?.doCollection(entityId)
    }

    //收藏成功
    override fun onCollectionSuccess(data: String?) {
        if (latitude != null && longitude != null) {
            mPresenter?.doNearby(1, 10, latitude!!, longitude!!)
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
            mPresenter?.doNearby(1, 10, latitude!!, longitude!!)
        }
        loadingDialog.dismiss()
    }
    //取消收藏旅行计划失败
    override fun onCancelCollectionError() {
        loadingDialog.dismiss()
    }



}