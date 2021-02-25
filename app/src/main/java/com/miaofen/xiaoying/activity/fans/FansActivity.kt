package com.miaofen.xiaoying.activity.fans

import android.content.Context
import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.personal.PersonalHomPagerActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.response.FansResponse
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.activity_fans.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 粉丝列表
 */
class FansActivity : BaseMvpActivity<FansContract.Presenter>(), FansContract.View,
    RefreshLayout.SetOnRefresh, FansRecyclerAdapter.OnFanListBack {

    var list = ArrayList<FansResponse.ContentBean?>()

    var mAdapter : FansRecyclerAdapter? = null

    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    override fun returnLayoutId() = R.layout.activity_fans

    override fun initView() {
        super.initView()
        loadingDialog.showSuccess()
        FansPresenter(this)
        //第一次网络请求
        mPresenter?.doFans(1,10)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.fans)
//        refresh_fans.autoRefresh()
        refresh_fans.setEnableRefresh(true)
        refresh_fans.setEnableLoadMore(true)
        refresh_fans.setSetOnRefresh(this)
        mAdapter = FansRecyclerAdapter(R.layout.focus_item, list, this)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mAdapter?.setOnFanListBack(this)
        refresh_fans.recyclerView.adapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.doFans(1,10)
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
        mAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
        refresh_fans.setEnableLoadMore(false)//设置不能上啦刷新
        loadingDialog.dismiss()
    }

    override fun onFansSuccess(data: FansResponse?) {
        refresh_fans.setEnableLoadMore(true)
        list.clear()
        for (item in data?.content!!){
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
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
        loadingDialog.dismiss()
    }

    /*----------------点击关注-------------------*/
    override fun onFocusFollow(followId: Long?) {
        loadingDialog.showSuccess()
        mPresenter?.doFocusOnUsers(followId)
    }

    //关注成功
    override fun onFocusOnUsersSuccess(data: Boolean) {
        mPresenter?.doFans(1,10)
        loadingDialog.dismiss()
    }

    //关注失败
    override fun onFocusOnUsersError() {
        loadingDialog.dismiss()
    }

    /*--------------取消关注--------------*/
    override fun onCancelAttention(followId: Long?) {
        loadingDialog.showSuccess()
        mPresenter?.doCancelAttentio(followId)
    }

    //取消关注成功
    override fun onCancelAttentioSuccess(data: Boolean) {
        mPresenter?.doFans(1,10)
        loadingDialog.dismiss()
    }

    //取消关注成功
    override fun onCancelAttentioError() {
        loadingDialog.dismiss()
    }

    //对外展示基本资料
    override fun onBasicInformation(userId: Long?) {
        if (userId != null) {
            PersonalHomPagerActivity.start(this,userId)
        }
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, FansActivity::class.java)
            context?.startActivity(intent)
        }
    }



}