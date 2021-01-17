package com.miaofen.xiaoying.activity.signup.examine

import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpFragment
import com.miaofen.xiaoying.common.data.bean.response.ExamineResponse
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.view.LoadingView
import com.miaofen.xiaoying.view.RefreshLayout
import kotlinx.android.synthetic.main.fragment_examine.*


/**
 * 审核中列表
 */
class ExamineFragment(var planId: Int?) : BaseMvpFragment<ExamineContract.Presenter>(),
    ExamineContract.View, RefreshLayout.SetOnRefresh, ExamineRecyclerAdapter.ExamineRecyclerInput,
    RefuseDialog.OnClickRefuse {

    var list = ArrayList<ExamineResponse>()

    var examineRecyclerAdapter: ExamineRecyclerAdapter? = null

    //拒绝内容弹窗
    private val refuseDialog: RefuseDialog by lazy {
        RefuseDialog(activity)
    }

    //加载动画弹窗
    private val loadingDialog: LoadingView by lazy {
        LoadingView(activity).apply {
            setTipMsg("正在加载")
        }
    }

    override fun getLayoutResources() = R.layout.fragment_examine

    override fun initView() {
        super.initView()
        ExaminePresenter(this)
        examine_recycler.setSetOnRefresh(this)
        examine_recycler.setEnableRefresh(true)
        examine_recycler.autoRefresh()
        examineRecyclerAdapter = ExamineRecyclerAdapter(R.layout.refuse_item, list, activity)
        examineRecyclerAdapter?.setAdministrationInput(this)
        examineRecyclerAdapter?.emptyView = getEmptyView(R.layout.no_data_available_layout)
        examine_recycler.recyclerView.adapter = examineRecyclerAdapter
        refuseDialog.setOnClickRefuse(this)
    }

    override fun onExamineSuccess(data: List<ExamineResponse>?) {
        list.clear()
        for (item in data!!) {
            list.add(item)
        }
        examineRecyclerAdapter?.notifyDataSetChanged()
    }

    override fun onExamineError() {

    }

    override fun loadMore(pager: Int, size: Int) {

    }

    override fun refresh(pager: Int, size: Int) {
        mPresenter?.doExamine(planId!!)
    }

    //通过按钮点击事件
    override fun onClickAdopt(joinId: Int?, planId: Int?) {
        loadingDialog.showLoading()
        mPresenter?.onPass(joinId, planId)
    }

    var refuseJoinId: Int? = -1
    var refusePlanId: Int? = -1

    //拒绝按钮点击事件
    override fun onClickRefuse(joinId: Int?, planId: Int?) {
        refuseJoinId = joinId
        refusePlanId = planId
        refuseDialog.showLoading()
    }

    //确认拒绝弹窗
    override fun onYes(reason: String?) {
        if (reason?.isEmpty()!!){
            ToastUtils.showToast("拒绝内容不能为空")
            return
        }
        loadingDialog.showLoading()
        mPresenter?.onRefuse(refuseJoinId, refusePlanId, reason)
    }

    //取消拒绝弹窗
    override fun onCancel() {
        refuseDialog.dismiss()
    }


    //通过成功回掉
    override fun onPassSuccess(data: String?) {
        //刷新数据
        mPresenter?.doExamine(planId!!)
        //刷新适配器
        examineRecyclerAdapter?.notifyDataSetChanged()
        loadingDialog.dismiss()
    }

    //通过失败回掉
    override fun onPassError() {
        loadingDialog.dismiss()
    }

    //拒绝成功回掉
    override fun onRefuseSuccess(data: String?) {
        //刷新数据
        mPresenter?.doExamine(planId!!)
        //刷新适配器
        examineRecyclerAdapter?.notifyDataSetChanged()
        refuseDialog.dismiss()
        loadingDialog.dismiss()
    }

    //拒绝失败回掉
    override fun onRefuseError() {
        loadingDialog.dismiss()
        refuseDialog.dismiss()
    }


}