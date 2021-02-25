package com.miaofen.xiaoying.activity.travelplan.participate


import com.chad.library.adapter.base.BaseQuickAdapter
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.travelplan.ipost.IPostPresenter
import com.miaofen.xiaoying.activity.travelplan.ipost.IPostRecyclerAdapter
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.IPostResponse
import com.miaofen.xiaoying.common.data.bean.response.ParticipateResponse
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_ipost.*
import kotlinx.android.synthetic.main.fragment_participate.*

/**
 * 我参与的旅行计划
 */
class ParticipateFragment : BaseMvpFragment<ParticipateContract.Presenter>(),ParticipateContract.View,
    RefreshLayout.SetOnRefresh {

    var mAdapter : ParticipateRecyclerAdapter? = null

    var list = ArrayList<ParticipateResponse.ContentBean?>()

    private val loadingDialog: LoadingView by lazy {
        LoadingView(activity).apply {
            setTipMsg("正在加载")
        }
    }

    override fun getLayoutResources() = R.layout.fragment_participate

    override fun initView() {
        super.initView()
        loadingDialog.showLoading()
        ParticipatePresenter(this)
        participate_recyclerview.setSetOnRefresh(this)
        participate_recyclerview.setEnableRefresh(true)
        //ipost_recyclerview.autoRefresh()
        mPresenter?.doParticipate(1,10)
        mAdapter = ParticipateRecyclerAdapter(R.layout.collection_list_item, list, activity)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        participate_recyclerview.recyclerView.adapter = mAdapter
    }


    //下拉请求成功无数据
    override fun onParticipateNullSuccess() {
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
        participate_recyclerview.setEnableLoadMore(false)//设置不能上啦刷新
        loadingDialog.dismiss()
    }
    //下拉请求成功有数据
    override fun onParticipateSuccess(data: ParticipateResponse?) {
        list.clear()
        for (item in data?.content!!) {
            list.add(item)
        }
        participate_recyclerview.setEnableLoadMore(true)
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }
    //上拉请求成功有数据
    override fun onParticipatePullSuccess(data: ParticipateResponse?) {
        participate_recyclerview.setEnableLoadMore(true)
        for (item in data?.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }
    //上拉请求成功无数据
    override fun onParticipatePullNullSuccess() {
        participate_recyclerview.setEnableLoadMore(false)
        loadingDialog.dismiss()
    }

    override fun onParticipateError() {
        loadingDialog.dismiss()
    }

    override fun loadMore(pager: Int, size: Int) {
       mPresenter?.doParticipate(pager,size)
    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doParticipate(pager,size)
    }

}