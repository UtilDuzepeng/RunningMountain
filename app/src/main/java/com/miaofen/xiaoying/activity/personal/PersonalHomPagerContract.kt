package com.miaofen.xiaoying.activity.personal

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.DetailedResponse
import com.miaofen.xiaoying.common.data.bean.response.ExternalDisplayResponse
import com.miaofen.xiaoying.common.data.bean.response.PersonalHomPagerResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.personal
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface PersonalHomPagerContract {

    interface Presenter : IPresenter {
        //用户基本资料
        fun doPersonalHomPager(userId: Long)

        //用户详细资料
        fun doDetailsData(userId: Long)

        //用户对外展示旅行计划
        fun doExternalDisplay(userId: Long, page: Int, size: Int)

        //关注列表
        fun doFocusOnUsers(followId: Long?)
        //取消关注
        fun doCancelAttentio(followId: Long?)

    }

    interface View : IView<Presenter> {
        fun onPersonalHomPagerSuccess(data: PersonalHomPagerResponse?)
        fun onPersonalHomPagerError()

        fun onDetailsDataSuccess(data: DetailedResponse?)
        fun onDetailsDataError()

        fun onExternalDisplaySuccess(data: ExternalDisplayResponse?)
        fun onExternalDisplayError()

        /*------------关注---------------*/
        fun onFocusOnUsersSuccess(data :Boolean)

        fun onFocusOnUsersError()

        /*-------------取消关注---------------*/
        fun onCancelAttentioSuccess(data :Boolean)

        fun onCancelAttentioError()

    }

}