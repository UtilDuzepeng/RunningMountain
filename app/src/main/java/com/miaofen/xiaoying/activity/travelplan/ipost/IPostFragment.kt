package com.miaofen.xiaoying.activity.travelplan.ipost

import com.chad.library.adapter.base.BaseQuickAdapter
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.IPostResponse
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_ipost.*

/**
 * 我发布的旅行计划
 */
class IPostFragment : BaseMvpFragment<IPostContract.Presenter>(),IPostContract.View ,
    RefreshLayout.SetOnRefresh {

    var mAdapter : IPostRecyclerAdapter? = null

    var list = ArrayList<IPostResponse.ContentBean?>()

    private val loadingDialog: LoadingView by lazy {
        LoadingView(activity).apply {
            setTipMsg("正在加载")
        }
    }

    override fun getLayoutResources() = R.layout.fragment_ipost

    override fun initView() {
        super.initView()
        loadingDialog.showLoading()
        IPostPresenter(this)
        ipost_recyclerview.setSetOnRefresh(this)
        ipost_recyclerview.setEnableRefresh(true)
        //ipost_recyclerview.autoRefresh()
        mPresenter?.doIPost(1,10)
        mAdapter = IPostRecyclerAdapter(R.layout.collection_list_item, list, activity)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        ipost_recyclerview.recyclerView.adapter = mAdapter
    }

    //下拉请求成功无数据
    override fun onIPostNullSuccess() {
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
        ipost_recyclerview.setEnableLoadMore(false)//设置不能上啦刷新
        loadingDialog.dismiss()
    }

    //下拉请求成功有数据
    override fun onIPostSuccess(data: IPostResponse?) {
        list.clear()
        for (item in data?.content!!) {
            list.add(item)
        }
        ipost_recyclerview.setEnableLoadMore(true)
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }

    //上拉请求成功有数据
    override fun onIPostPullSuccess(data: IPostResponse?) {
        ipost_recyclerview.setEnableLoadMore(true)
        for (item in data?.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }

    //上拉请求成功无数据
    override fun onIPostPullNullSuccess() {
        ipost_recyclerview.setEnableLoadMore(false)
        loadingDialog.dismiss()
    }

    //请求失败
    override fun onIPostError() {
        ipost_recyclerview.setEnableLoadMore(false)
        loadingDialog.dismiss()
    }

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doIPost(pager,size)
    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doIPost(pager,size)
    }



}