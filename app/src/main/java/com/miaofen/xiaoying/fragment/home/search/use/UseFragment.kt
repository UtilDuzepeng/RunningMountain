package com.miaofen.xiaoying.fragment.home.search.use


import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.request.PlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.SearchUserResponse
import com.miaofen.xiaoying.fragment.home.search.back.ObserverListener
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_plan.*
import kotlinx.android.synthetic.main.fragment_use.*


/**
 * 搜索用户界面
 * */
class UseFragment(var data: String) : BaseMvpFragment<SearchUserContract.Presenter>(),
    SearchUserContract.View, RefreshLayout.SetOnRefresh , ObserverListener {

    var mAdapter: UseRecyclerViewAdapter? = null

    var list = ArrayList<SearchUserResponse.ContentBean>()

    var planRequestData = PlanRequestData()

    override fun getLayoutResources() = R.layout.fragment_use


    override fun initView() {
        super.initView()
        SearchUserPresenter(this)
        planRequestData.setKeyword(data)
        planRequestData.setPage(1)
        planRequestData.setSize(10)
        use_recycler.setSetOnRefresh(this)
        use_recycler.autoRefresh()
        mAdapter = UseRecyclerViewAdapter(R.layout.use_item_layout, list, activity)
        use_recycler.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        planRequestData.setPage(pager)
        planRequestData.setSize(size)
        mPresenter?.doSearchUser(planRequestData)
        mAdapter?.notifyDataSetChanged()
    }

    override fun refresh(pager: Int, size: Int) {
        list.clear()
        mPresenter?.doSearchUser(planRequestData)
    }

    override fun onSearchUserSuccess(data: SearchUserResponse?) {
        if (data?.content == null) {
            use_recycler.setEnableLoadMore(false)
            return
        }
        if (data.content?.size == 0) {
            use_recycler.setEnableLoadMore(false)
            return
        } else {
            use_recycler.setEnableLoadMore(true)
        }
        for (item in data.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()

    }

    override fun onSearchUserError() {

    }

    override fun observerUpData(content: String?) {
        planRequestData.setKeyword(content)
        mPresenter?.doSearchUser(planRequestData)
    }

}