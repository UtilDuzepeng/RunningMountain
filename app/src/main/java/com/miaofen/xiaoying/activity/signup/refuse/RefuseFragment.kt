package com.miaofen.xiaoying.activity.signup.refuse

import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.RefuseResponse
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_refuse.*


/**
 * 已拒绝列表
 */
class RefuseFragment(var planId: Int?) : BaseMvpFragment<RefuseContract.Presenter>(),
    RefuseContract.View, RefreshLayout.SetOnRefresh {

    var list = ArrayList<RefuseResponse>()

    var refuseRecyclerAdapter: RefuseRecyclerAdapter? = null

    override fun getLayoutResources() = R.layout.fragment_refuse

    override fun initView() {
        super.initView()
        RefusePresenter(this)
        refuse_recycler.setSetOnRefresh(this)
        refuse_recycler.setEnableRefresh(true)
        refuse_recycler.autoRefresh()
        refuseRecyclerAdapter = RefuseRecyclerAdapter(R.layout.refuse_item,list,activity)
        refuse_recycler.recyclerView.adapter = refuseRecyclerAdapter
    }

    override fun onRefuseSuccess(data: List<RefuseResponse>?) {
        list.clear()
        for (item in data!!){
            list.add(item)
        }
        refuseRecyclerAdapter?.notifyDataSetChanged()
    }

    override fun onRefuseError() {

    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doRefuse(planId!!)
    }

    override fun loadMore(pager: Int, size: Int) {

    }



}