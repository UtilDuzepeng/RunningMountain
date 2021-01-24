package com.miaofen.xiaoying.activity.collection

import android.content.Context
import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.response.CollectionListResponse
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.activity_collection_list.*
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 用户收藏列表
 */
class CollectionListActivity : BaseMvpActivity<CollectionListContract.Presenter>(),
    CollectionListContract.View, RefreshLayout.SetOnRefresh {

    var list = ArrayList<CollectionListResponse.ContentBean?>()

    var mAdapter : CollectionListRecyclerAdapter? = null

    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    override fun returnLayoutId() = R.layout.activity_collection_list

    override fun initView() {
        super.initView()
        loadingDialog.showSuccess()
        CollectionListPresnter(this)
        mPresenter?.doCollectionList(1, 10)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.collection)
//        refresh_collection.autoRefresh()
        refresh_collection.setEnableRefresh(true)
        refresh_collection.setEnableLoadMore(true)
        refresh_collection.setSetOnRefresh(this)
        mAdapter = CollectionListRecyclerAdapter(R.layout.collection_list_item, list, this)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        refresh_collection.recyclerView.adapter = mAdapter

    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, CollectionListActivity::class.java)
            context?.startActivity(intent)
        }
    }

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doCollectionList(pager, size)
    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doCollectionList(pager, size)
    }

    override fun onCollectionListNullSuccess() {
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
        refresh_collection.setEnableLoadMore(false)//设置不能上啦刷新
        loadingDialog.dismiss()
    }

    override fun onCollectionListSuccess(data: CollectionListResponse?) {
        list.clear()
        for (item in data?.content!!){
            list.add(item)
        }
        refresh_collection.setEnableLoadMore(true)
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }

    override fun onCollectionSuccess(data: CollectionListResponse?) {
        refresh_collection.setEnableLoadMore(true)
        for (item in data?.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onCollectionNullSuccess() {
        refresh_collection.setEnableLoadMore(false)
    }

    override fun onCollectionListError() {
        refresh_collection.setEnableLoadMore(false)
        loadingDialog.dismiss()
    }

}