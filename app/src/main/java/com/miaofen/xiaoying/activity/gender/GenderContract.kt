package com.miaofen.xiaoying.activity.gender

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView

/**
 * 项目名称：com.miaofen.xiaoying.activity.gender
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface GenderContract {

    interface Presenter : IPresenter {
        fun doGender(gender: Int?)
    }

    interface View : IView<Presenter> {
        fun onGenderSuccess(data: String?)
        fun onGenderError()
    }

}