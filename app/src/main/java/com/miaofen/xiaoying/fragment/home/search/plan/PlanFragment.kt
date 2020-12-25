package com.miaofen.xiaoying.fragment.home.search.plan

import android.util.Log
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.request.PlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.PlanResponse
import com.miaofen.xiaoying.fragment.home.search.back.ObserverListener
import com.miaofen.xiaoying.fragment.home.search.back.ObserverManager
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.fragment_plan.*


/**
 * 搜索计划
 */
class PlanFragment(var data: String) : BaseMvpFragment<PlanContract.Presenter>(), PlanContract.View,
    RefreshLayout.SetOnRefresh ,ObserverListener{

    var mAdapter: PlanRecyclerViewAdapter? = null

    var planRequestData = PlanRequestData()

    var list = ArrayList<PlanResponse.ContentBean>()

    override fun getLayoutResources() = R.layout.fragment_plan

    override fun initView() {
        super.initView()
        ObserverManager.getInstance().add(this)
        PlanPresenter(this)
        plan_recycler.setSetOnRefresh(this)
        plan_recycler.autoRefresh()
        planRequestData.setKeyword(data)
        planRequestData.setPage(1)
        planRequestData.setSize(10)
        mAdapter = PlanRecyclerViewAdapter(R.layout.plan_item_layout, list, activity)
        plan_recycler.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        planRequestData.setPage(pager)
        planRequestData.setSize(size)
        mPresenter?.doPlan(planRequestData)
        mAdapter?.notifyDataSetChanged()
    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doPlan(planRequestData)
        list.clear()
    }

    override fun onPlanSuccess(data: PlanResponse?) {

        if (data?.content == null) {
            if (plan_recycler != null){
                plan_recycler.setEnableLoadMore(false)
            }
            return
        }
        if (data.content?.size == 0) {
            if (plan_recycler != null){
                plan_recycler.setEnableLoadMore(false)
            }
            return
        } else {
            if (plan_recycler != null){
                plan_recycler.setEnableLoadMore(true)
            }
        }
        for (item in data.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()

    }

    override fun onPlanError() {

    }

    override fun observerUpData(content: String?) {
        planRequestData.setKeyword(content)
        mPresenter?.doPlan(planRequestData)
    }

}