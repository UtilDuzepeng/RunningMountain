package com.miaofen.xiaoying.fragment.home.hottest

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.hottest
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface NewestContract {

    interface Presenter : IPresenter {
        fun doNewest(page: Int,size :Int, latitude: Double, longitude: Double)
    }

    interface View : IView<Presenter> {
        //下拉成功 没有数据
        fun onDownNewestNullSuccess()
        //下拉成功 有数据
        fun onDownNewestSuccess(data: HomeResponse?)
        //上啦加载 有数据
        fun onNewestSuccess(data: HomeResponse?)
        //上啦加载 没有数据
        fun onNewestNullSuccess()

        fun onNewestError()
    }
}