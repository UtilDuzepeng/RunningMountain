package com.miaofen.xiaoying.fragment.home

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView

/**
 * 项目名称：com.miaofen.xiaoying.fragment.homefragment
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface RotationChartContract {

    interface Presenter : IPresenter {
        fun doRotationChart()
    }

    interface View : IView<Presenter> {
        fun onRotationChartSuccess(data: String)
        fun onRotationChartError()
    }
}