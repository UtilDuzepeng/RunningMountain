package com.miaofen.xiaoying.activity.signup.examine

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.ExamineResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.signup.examine
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface ExamineContract : IPresenter {

    interface Presenter : IPresenter {
        fun doExamine(planId: Int)
        fun onPass(joinId: Int?, planId: Int?)
        fun onRefuse(joinId: Int?, planId: Int?, reason: String?)
    }

    interface View : IView<Presenter> {
        fun onExamineSuccess(data: List<ExamineResponse>?)
        fun onExamineError()

        fun onPassSuccess(data: String?)
        fun onPassError()

        fun onRefuseSuccess(data: String?)
        fun onRefuseError()

    }
}