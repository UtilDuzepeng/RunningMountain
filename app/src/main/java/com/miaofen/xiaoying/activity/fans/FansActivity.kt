package com.miaofen.xiaoying.activity.fans

import android.content.Context
import android.content.Intent
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.response.FansResponse
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.activity_fans.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 粉丝列表
 */
class FansActivity : BaseMvpActivity<FansContract.Presenter>(), FansContract.View,
    RefreshLayout.SetOnRefresh {

    var list = ArrayList<FansResponse.ContentBean?>()

    var mAdapter : FansRecyclerAdapter? = null

    override fun returnLayoutId() = R.layout.activity_fans

    override fun initView() {
        super.initView()
        FansPresenter(this)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.fans)
        refresh_fans.autoRefresh()
        refresh_fans.setEnableRefresh(true)
        refresh_fans.setEnableLoadMore(true)
        refresh_fans.setSetOnRefresh(this)
        mAdapter = FansRecyclerAdapter(R.layout.focus_item, list, this)
        refresh_fans.recyclerView.adapter = mAdapter
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
    }

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doFans(pager,size)
    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doFans(pager,size)
    }

    override fun onFansNullSuccess() {
        refresh_fans.setEnableLoadMore(false)//设置不能上啦刷新
    }

    override fun onFansSuccess(data: FansResponse?) {
        refresh_fans.setEnableLoadMore(true)
        list.clear()
        for (item in data?.content!!){
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onFansOnSuccess(data: FansResponse?) {
        refresh_fans.setEnableLoadMore(true)
        for (item in data?.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onFansOnNullSuccess() {
        refresh_fans.setEnableLoadMore(false)
    }

    override fun onFansOnError() {
        refresh_fans.finishRefresh()
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, FansActivity::class.java)
            context?.startActivity(intent)
        }
    }
}