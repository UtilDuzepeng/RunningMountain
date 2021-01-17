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
        fun doNearby(page: Int, size: Int, latitude: Double, longitude: Double)
        fun doCollection(entityId :Int?)
        fun doCancelCollection(entityId :Int?)
    }

    interface View : IView<Presenter> {
        //下拉成功 没有数据
        fun onDownNearbytNullSuccess()

        //下拉成功 有数据
        fun onDownNearbySuccess(data: HomeResponse?)

        //上啦加载 有数据
        fun onNearbySuccess(data: HomeResponse?)

        //上啦加载 没有数据
        fun onNearbyNullSuccess()

        //加载错误
        fun onNearbyError()


        fun onCollectionSuccess(data: String?)

        fun onCollectionError()

        fun onCancelCollectionSuccess(data: String?)

        fun onCancelCollectionError()
    }

}