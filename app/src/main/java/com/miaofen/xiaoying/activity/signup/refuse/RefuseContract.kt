package com.miaofen.xiaoying.activity.signup.refuse

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.RefuseResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.signup.refuse
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface RefuseContract : IPresenter {

    interface Presenter : IPresenter {
        fun doRefuse(planId: Int)
    }

    interface View : IView<Presenter> {
        fun onRefuseSuccess(data: List<RefuseResponse>?)
        fun onRefuseError()
    }
}