package com.miaofen.xiaoying.fragment.home.search.plan


import android.view.LayoutInflater
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.request.PlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.PlanResponse
import com.miaofen.xiaoying.fragment.home.search.back.ObserverListener
import com.miaofen.xiaoying.fragment.home.search.back.ObserverManager
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_plan.*


/**
 * 搜索计划
 */
class PlanFragment(var data: String) : BaseMvpFragment<PlanContract.Presenter>(), PlanContract.View,
    RefreshLayout.SetOnRefresh, ObserverListener {

    var mAdapter: PlanRecyclerViewAdapter? = null

    var planRequestData = PlanRequestData()

    var list = ArrayList<PlanResponse.ContentBean>()

    private val loadingDialog: LoadingView by lazy {
        LoadingView(activity).apply {
            setTipMsg("正在加载")
        }
    }

    override fun getLayoutResources() = R.layout.fragment_plan

    override fun initView() {
        super.initView()
//        val empty: View = LayoutInflater.from(activity).inflate(R.layout.search_empty_layout, null, false)
        ObserverManager.getInstance().add(this)
        PlanPresenter(this)
        plan_recycler.setSetOnRefresh(this)
        plan_recycler.setEnableRefresh(false)
        loadingDialog.showSuccess()
        planRequestData.setKeyword(data)
        planRequestData.setPage(1)
        planRequestData.setSize(10)
        mPresenter?.doPlan(planRequestData)
        mAdapter = PlanRecyclerViewAdapter(R.layout.plan_item_layout, list, activity)
        plan_recycler.recyclerView.adapter = mAdapter
        mAdapter?.emptyView = getEmptyView(R.layout.search_empty_layout)
    }

    override fun loadMore(pager: Int, size: Int) {
        loadingDialog.showSuccess()
        planRequestData.setPage(pager)
        planRequestData.setSize(10)
        mPresenter?.doPlan(planRequestData)
        mAdapter?.notifyDataSetChanged()
    }

    override fun refresh(pager: Int, size: Int) {

    }

    override fun onPlanSuccess(data: PlanResponse?) {

        if (data?.content == null) {
            if (plan_recycler != null) {
                plan_recycler.setEnableLoadMore(false)
            }
            return
        }
        if (data.content?.size == 0) {
            if (plan_recycler != null) {
                plan_recycler.setEnableLoadMore(false)
            }
            return
        } else {
            if (plan_recycler != null) {
                plan_recycler.setEnableLoadMore(true)
            }
        }
        for (item in data.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()

    }

    override fun onPlanError() {
        loadingDialog.dismiss()
    }

    override fun observerUpData(content: String?) {
        list.clear()
        planRequestData.setKeyword(content)
        planRequestData.setPage(1)
        planRequestData.setSize(10)
        mPresenter?.doPlan(planRequestData)
        loadingDialog.showSuccess()
    }

}