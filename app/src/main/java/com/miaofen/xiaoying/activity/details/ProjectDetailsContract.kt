package com.miaofen.xiaoying.activity.details

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.request.DetailsRequestData
import com.miaofen.xiaoying.common.data.bean.request.OneCommentsData
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.details
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface ProjectDetailsContract {
    interface Presenter : IPresenter {
        fun doProjectDetails(detailsRequestData: DetailsRequestData?)
        fun doOneComments(oneCommentsData: OneCommentsData?)
    }

    interface View : IView<Presenter> {
        fun onProjectDetailsSuccess(data: DetailsResponse)
        fun onProjectDetailsError()
        fun onOneCommentsSuccess(data: OneCommentsResponse?)
        fun onOneCommentsError()
    }
}