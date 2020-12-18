package com.miaofen.xiaoying.fragment.home.nearby

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.nearby
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface NearbyContract {

    interface Presenter : IPresenter {
        fun doNearby(page: Int,size :Int)
    }

    interface View : IView<Presenter> {
        fun onNearbySuccess(data: HomeResponse?)
        fun onNearbyError()
    }

}