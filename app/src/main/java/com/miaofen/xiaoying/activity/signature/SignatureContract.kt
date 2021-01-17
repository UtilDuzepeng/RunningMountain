package com.miaofen.xiaoying.activity.signature

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView

/**
 * 项目名称：com.miaofen.xiaoying.activity.signature
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface SignatureContract {

    interface Presenter : IPresenter {
        fun doSignature(personalSignature :String)
    }

    interface View : IView<Presenter> {
        fun onSignatureSuccess(data: String?)
        fun onSignaturesError()
    }
}