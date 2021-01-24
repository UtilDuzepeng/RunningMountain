package com.miaofen.xiaoying.fragment.home.search.use

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.request.PlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.SearchUserResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search.use
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface SearchUserContract {
    interface Presenter : IPresenter {
        fun doSearchUser(planRequestData: PlanRequestData)
        //关注列表
        fun doFocusOnUsers(followId: Long?)
        //取消关注
        fun doCancelAttentio(followId: Long?)
    }

    interface View : IView<Presenter> {
        fun onSearchUserSuccess(data: SearchUserResponse?)
        fun onSearchUserError()

        /*------------关注---------------*/
        fun onFocusOnUsersSuccess(data :Boolean)

        fun onFocusOnUsersError()

        /*-------------取消关注---------------*/
        fun onCancelAttentioSuccess(data :Boolean)

        fun onCancelAttentioError()
    }
}