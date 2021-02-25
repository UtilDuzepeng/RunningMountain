package com.miaofen.xiaoying.activity.follow

import android.content.Context
import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.personal.PersonalHomPagerActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.response.FocusOnResponse
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.activity_focus_on_yourself.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 关注列表
 */
class FocusOnYourselfActivity : BaseMvpActivity<FocusOnYourselfContract.Presenter>(),
    FocusOnYourselfContract.View, RefreshLayout.SetOnRefresh,
    FocusOnRecyclerAdapter.OnFocusOnListBack {

    var mAdapter: FocusOnRecyclerAdapter? = null

    var list = ArrayList<FocusOnResponse.ContentBean?>()

    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    override fun returnLayoutId() = R.layout.activity_focus_on_yourself

    override fun initView() {
        super.initView()
        loadingDialog.showSuccess()
        FocusOnYourselfPresenter(this)
        //第一次网络请求
        mPresenter?.doFocusOnYourself(1, 10)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.follow)
//        focus_refresh.autoRefresh()
        focus_refresh.setEnableRefresh(true)
        focus_refresh.setEnableLoadMore(true)
        focus_refresh.setSetOnRefresh(this)
        mAdapter = FocusOnRecyclerAdapter(R.layout.focus_item, list, this)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mAdapter?.setOnFanListBack(this)
        focus_refresh.recyclerView.adapter = mAdapter

    }

    override fun onResume() {
        super.onResume()
        mPresenter?.doFocusOnYourself(1, 10)
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
    }

    override fun loadMore(pager: Int, size: Int) {
        mPresenter?.doFocusOnYourself(pager, size)
    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doFocusOnYourself(pager, size)
    }

    //下拉成功 没有数据
    override fun onFocusOnYourselfNullSuccess() {
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
        focus_refresh.setEnableLoadMore(false)//设置不能上啦刷新
        loadingDialog.dismiss()
    }

    //下拉成功 有数据
    override fun onFocusOnYourselfSuccess(data: FocusOnResponse?) {
        list.clear()
        for (item in data?.content!!) {
            list.add(item)
        }
        focus_refresh.setEnableLoadMore(true)//设置不能上啦刷新
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
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
        loadingDialog.dismiss()
    }

    /*------------关注------------*/
    override fun onFocusFollow(followId: Long?) {
        loadingDialog.showSuccess()
        mPresenter?.doFocusOnUsers(followId)
    }

    override fun onFocusOnUsersSuccess(data: Boolean) {
        mPresenter?.doFocusOnYourself(1, 10)
        loadingDialog.dismiss()
    }

    override fun onFocusOnUsersError() {
        loadingDialog.dismiss()
    }

    /*----------取消关注--------------*/
    override fun onCancelAttention(followId: Long?) {
        loadingDialog.showSuccess()
        mPresenter?.doCancelAttentio(followId)
    }

    /*-------------对外基本资料----------------*/
    override fun onBasicInformation(userId: Long?) {
        if (userId != null) {
            PersonalHomPagerActivity.start(this,userId)
        }
    }

    override fun onCancelAttentioSuccess(data: Boolean) {
        mPresenter?.doFocusOnYourself(1, 10)
        loadingDialog.dismiss()
    }

    override fun onCancelAttentioError() {
        loadingDialog.dismiss()
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, FocusOnYourselfActivity::class.java)
            context?.startActivity(intent)
        }
    }

}