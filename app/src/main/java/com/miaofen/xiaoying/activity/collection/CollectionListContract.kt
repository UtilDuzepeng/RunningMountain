package com.miaofen.xiaoying.activity.collection

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.CollectionListResponse
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.collection
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface CollectionListContract {

    interface Presenter : IPresenter {
        fun doCollectionList(page: Int, size: Int)
    }

    interface View : IView<Presenter> {

        //下拉成功 没有数据
        fun onCollectionListNullSuccess()

        //下拉成功 有数据
        fun onCollectionListSuccess(data: CollectionListResponse?)

        //上啦加载 有数据
        fun onCollectionSuccess(data: CollectionListResponse?)

        //上啦加载 没有数据
        fun onCollectionNullSuccess()

        fun onCollectionListError()
    }
}