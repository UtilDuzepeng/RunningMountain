package com.miaofen.xiaoying.fragment.home.search.plan

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.PlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.BannerResponse
import com.miaofen.xiaoying.common.data.bean.response.PlanResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.fragment.home.HomeContract
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search.plan
 * 类描述：搜索计划网络请求
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class PlanPresenter(view: PlanContract.View) : BasePresenter<PlanContract.View>(view),
    PlanContract.Presenter {

    override fun doPlan(planRequestData: PlanRequestData) {
        RemoteRepository
            .onPlan(planRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<PlanResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: PlanResponse?) {
//                    if (data == null) {
//                        return
//                    }
                    mRootView.get()?.onPlanSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onPlanError()
                }
            })

    }
}