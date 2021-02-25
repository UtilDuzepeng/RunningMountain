package com.miaofen.xiaoying.activity.travelplan.ipost

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.common.data.bean.response.IPostResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.travelplan.ipost
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/24/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface IPostContract {

    interface Presenter : IPresenter {
        fun doIPost(page :Int,size :Int)
    }

    interface View : IView<Presenter> {
        //下拉没有数据
        fun onIPostNullSuccess()
        //下拉有数据
        fun onIPostSuccess(data: IPostResponse?)
        //上拉有数据
        fun onIPostPullSuccess(data: IPostResponse?)
        //上拉无数据
        fun onIPostPullNullSuccess()
        //加载失败
        fun onIPostError()
    }
}