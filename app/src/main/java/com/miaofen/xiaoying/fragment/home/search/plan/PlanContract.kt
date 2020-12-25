package com.miaofen.xiaoying.fragment.home.search.plan

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.request.PlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.BannerResponse
import com.miaofen.xiaoying.common.data.bean.response.PlanResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search.plan
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface PlanContract {
    interface Presenter : IPresenter {
        fun doPlan(planRequestData: PlanRequestData)
    }

    interface View : IView<Presenter> {
        fun onPlanSuccess(data: PlanResponse?)
        fun onPlanError()
    }

}