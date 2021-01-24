package com.miaofen.xiaoying.fragment.home.search.use


import com.chad.library.adapter.base.BaseQuickAdapter
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.PersonalHomPagerActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.request.PlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.SearchUserResponse
import com.miaofen.xiaoying.fragment.home.search.back.ObserverListener
import com.miaofen.xiaoying.fragment.home.search.back.ObserverManager
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_use.*


/**
 * 搜索用户界面
 * */
class UseFragment(var dataName: String) : BaseMvpFragment<SearchUserContract.Presenter>(),
    SearchUserContract.View, RefreshLayout.SetOnRefresh, ObserverListener,
    UseRecyclerViewAdapter.OnUseOnListBack {

    var mAdapter: UseRecyclerViewAdapter? = null

    var list = ArrayList<SearchUserResponse.ContentBean>()

    var planRequestData = PlanRequestData()

    private val loadingDialog: LoadingView by lazy {
        LoadingView(activity).apply {
            setTipMsg("正在加载")
        }
    }

    override fun getLayoutResources() = R.layout.fragment_use

    override fun initView() {
        super.initView()
//        val empty: View = LayoutInflater.from(activity).inflate(R.layout.search_empty_layout, null, false)
        ObserverManager.getInstance().add(this)
        SearchUserPresenter(this)
        loadingDialog.showSuccess()
        planRequestData.setKeyword(dataName)
        planRequestData.setPage(1)
        planRequestData.setSize(10)
        use_recycler.setSetOnRefresh(this)
        use_recycler.setEnableRefresh(false)
        mPresenter?.doSearchUser(planRequestData)
        mAdapter = UseRecyclerViewAdapter(R.layout.use_item_layout, list, activity)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mAdapter?.setOnFanListBack(this)
        use_recycler.recyclerView.adapter = mAdapter
    }

    override fun loadMore(pager: Int, size: Int) {
        loadingDialog.showSuccess()
        planRequestData.setPage(pager)
        planRequestData.setSize(10)
        mPresenter?.doSearchUser(planRequestData)
        mAdapter?.notifyDataSetChanged()
    }

    override fun refresh(pager: Int, size: Int) {

    }

    override fun onSearchUserSuccess(data: SearchUserResponse?) {
        if (data?.content == null) {
            mAdapter?.emptyView = getEmptyView(R.layout.search_empty_layout)
            use_recycler?.setEnableLoadMore(false)
            return
        }
        if (data.content?.size == 0) {
            mAdapter?.emptyView = getEmptyView(R.layout.search_empty_layout)
            use_recycler?.setEnableLoadMore(false)
            return
        } else {
            use_recycler?.setEnableLoadMore(true)
        }
        for (item in data.content!!) {
            list.add(item)
        }
        mAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }

    override fun onSearchUserError() {
        loadingDialog.dismiss()
    }



    override fun observerUpData(content: String?) {
        if (content != ""){
            this.dataName = content!!
        }
        loadingDialog.showSuccess()
        list.clear()
        planRequestData.setKeyword(content)
        planRequestData.setPage(1)
        planRequestData.setSize(10)
        mPresenter?.doSearchUser(planRequestData)
    }

    /*--------------关注----------------*/
    override fun onUseFocusFollow(followId: Long?) {
        loadingDialog.showSuccess()
        mPresenter?.doFocusOnUsers(followId)
    }

    override fun onFocusOnUsersSuccess(data: Boolean) {
        list.clear()
        planRequestData.setKeyword(dataName)
        planRequestData.setPage(1)
        planRequestData.setSize(10)
        mPresenter?.doSearchUser(planRequestData)
        loadingDialog.dismiss()
    }

    override fun onFocusOnUsersError() {
        loadingDialog.dismiss()
    }

    /*--------------取消关注----------------*/
    override fun onUseCancelAttention(followId: Long?) {
        loadingDialog.showSuccess()
        mPresenter?.doCancelAttentio(followId)
    }

    override fun onCancelAttentioSuccess(data: Boolean) {
        list.clear()
        planRequestData.setKeyword(dataName)
        planRequestData.setPage(1)
        planRequestData.setSize(10)
        mPresenter?.doSearchUser(planRequestData)
        loadingDialog.dismiss()
    }

    override fun onCancelAttentioError() {
        loadingDialog.dismiss()
    }

    /*-------------查看个人详情---------------*/
    override fun onUseSearchForUsers() {
        PersonalHomPagerActivity.start(activity)
    }

}