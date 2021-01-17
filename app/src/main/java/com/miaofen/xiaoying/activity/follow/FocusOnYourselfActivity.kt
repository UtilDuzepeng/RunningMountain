package com.miaofen.xiaoying.activity.follow

import android.content.Context
import android.content.Intent
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.response.FocusOnResponse
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.activity_focus_on_yourself.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 关注列表
 */
class FocusOnYourselfActivity : BaseMvpActivity<FocusOnYourselfContract.Presenter>(),
    FocusOnYourselfContract.View, RefreshLayout.SetOnRefresh  {

    var mAdapter : FocusOnRecyclerAdapter? = null

    var list = ArrayList<FocusOnResponse.ContentBean?>()

    override fun returnLayoutId() = R.layout.activity_focus_on_yourself

    override fun initView() {
        super.initView()
        FocusOnYourselfPresenter(this)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.follow)
        focus_refresh.autoRefresh()
        focus_refresh.setEnableRefresh(true)
        focus_refresh.setEnableLoadMore(true)
        focus_refresh.setSetOnRefresh(this)
        mAdapter = FocusOnRecyclerAdapter(R.layout.focus_item, list, this)
        focus_refresh.recyclerView.adapter = mAdapter
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
    }

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doFocusOnYourself(pager,size)
    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doFocusOnYourself(pager,size)
    }

    //下拉成功 没有数据
    override fun onFocusOnYourselfNullSuccess() {
        focus_refresh.setEnableLoadMore(false)//设置不能上啦刷新
    }
    //下拉成功 有数据
    override fun onFocusOnYourselfSuccess(data: FocusOnResponse?) {
        list.clear()
        for (item in data?.content!!){
            list.add(item)
        }
        focus_refresh.setEnableLoadMore(true)//设置不能上啦刷新
        mAdapter?.notifyDataSetChanged()
    }
    //上啦加载 有数据
    override fun onFocusOnSuccess(data: FocusOnResponse?) {
        for (item in data?.content!!) {
            list.add(item)
        }
        focus_refresh.setEnableLoadMore(true)//设置不能上啦刷新
        mAdapter?.notifyDataSetChanged()
    }
    //上啦加载 没有数据
    override fun onFocusOnNullSuccess() {
        focus_refresh.setEnableLoadMore(false)//设置不能上啦刷新
    }

    override fun onFocusOnError() {

    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, FocusOnYourselfActivity::class.java)
            context?.startActivity(intent)
        }
    }

}