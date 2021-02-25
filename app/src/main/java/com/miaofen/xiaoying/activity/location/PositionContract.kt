package com.miaofen.xiaoying.activity.location

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.PositionResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.location
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/2/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface PositionContract {

    interface Presenter : IPresenter {
        fun doPosition()
        fun doAccessArea(areaCode: Int)
    }

    interface View : IView<Presenter> {
        fun onPositionSuccess(data: List<PositionResponse>?)
        fun onPositionError()

        fun onAccessAreaSuccess(data: List<PositionResponse>?)
        fun onAccessAreaSuccess()
        fun onAccessAreaError()
    }

}