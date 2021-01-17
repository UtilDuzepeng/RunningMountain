package com.miaofen.xiaoying.fragment.user

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.PersonalResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.user
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface UserContract {

    interface Presenter : IPresenter {
        fun doPersonalCenter()
    }

    interface View : IView<Presenter> {
        fun onPersonalCenterSuccess(data: PersonalResponse)
        fun onPersonalCenterError()
    }
}