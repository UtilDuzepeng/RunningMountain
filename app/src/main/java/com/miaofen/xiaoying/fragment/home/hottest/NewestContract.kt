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
        fun doNewest(page: Int,size :Int)
    }

    interface View : IView<Presenter> {
        fun onNewestSuccess(data: HomeResponse?)
        fun onNewestError()
    }
}