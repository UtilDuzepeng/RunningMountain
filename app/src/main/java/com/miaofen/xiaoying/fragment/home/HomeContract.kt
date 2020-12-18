package com.miaofen.xiaoying.fragment.home

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.BannerResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.homefragment
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface HomeContract {

    interface Presenter : IPresenter {
        fun doRotationChart()
        fun recommend()
    }

    interface View : IView<Presenter> {
        fun onRotationChartSuccess(data: List<BannerResponse>)
        fun onRotationChartError()
        fun onRecommendSuccess(data: ArrayList<String>)
        fun onRecommendError()
    }
}