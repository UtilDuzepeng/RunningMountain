package com.miaofen.xiaoying.activity.travelplan.participate

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.IPostResponse
import com.miaofen.xiaoying.common.data.bean.response.ParticipateResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.travelplan.participate
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/24/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface ParticipateContract {

    interface Presenter : IPresenter {
        fun doParticipate(page :Int,size :Int)
    }

    interface View : IView<Presenter> {
        //下拉没有数据
        fun onParticipateNullSuccess()
        //下拉有数据
        fun onParticipateSuccess(data: ParticipateResponse?)
        //上拉有数据
        fun onParticipatePullSuccess(data: ParticipateResponse?)
        //上拉无数据
        fun onParticipatePullNullSuccess()
        fun onParticipateError()
    }
}