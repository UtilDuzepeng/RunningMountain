package com.miaofen.xiaoying.fragment.home.hot

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.hot
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface HotContract {

    interface Presenter : IPresenter {
        fun doHot(page: Int, size: Int, latitude: Double, longitude: Double)
        fun doCollection(entityId :Int?)
        fun doCancelCollection(entityId :Int?)

    }

    interface View : IView<Presenter> {
        //下拉成功 没有数据
        fun onDownHotNullSuccess()

        //下拉成功 有数据
        fun onDownHotSuccess(data: HomeResponse?)

        //上啦加载 有数据
        fun onHotSuccess(data: HomeResponse?)

        //上啦加载 没有数据
        fun onHotNullSuccess()

        fun onHotError()

        fun onCollectionSuccess(data: String?)

        fun onCollectionError()

        fun onCancelCollectionSuccess(data: String?)

        fun onCancelCollectionError()
    }
}