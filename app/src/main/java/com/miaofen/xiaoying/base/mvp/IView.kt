package com.miaofen.xiaoying.base.mvp

/**
 * 项目名称：com.miaofen.xiaoying.base.mvp
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface IView<P> {

    fun showLoading() {}

    fun hideLoading() {}

    fun showMessage(message: String?)
    fun showMessage(strId: Int)

    fun setPresenter(presenter: P)
}