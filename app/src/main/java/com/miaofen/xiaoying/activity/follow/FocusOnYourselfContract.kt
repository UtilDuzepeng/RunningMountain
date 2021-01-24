package com.miaofen.xiaoying.activity.follow

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.CollectionListResponse
import com.miaofen.xiaoying.common.data.bean.response.FocusOnResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.follow
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface FocusOnYourselfContract {

    interface Presenter : IPresenter {
        fun doFocusOnYourself(page: Int, size: Int)
        //关注列表
        fun doFocusOnUsers(followId: Long?)
        //取消关注
        fun doCancelAttentio(followId: Long?)
    }

    interface View : IView<Presenter> {

        //下拉成功 没有数据
        fun onFocusOnYourselfNullSuccess()

        //下拉成功 有数据
        fun onFocusOnYourselfSuccess(data: FocusOnResponse?)

        //上啦加载 有数据
        fun onFocusOnSuccess(data: FocusOnResponse?)

        //上啦加载 没有数据
        fun onFocusOnNullSuccess()

        fun onFocusOnError()


        /*------------关注---------------*/
        fun onFocusOnUsersSuccess(data :Boolean)

        fun onFocusOnUsersError()

        /*-------------取消关注---------------*/
        fun onCancelAttentioSuccess(data :Boolean)

        fun onCancelAttentioError()

    }
}