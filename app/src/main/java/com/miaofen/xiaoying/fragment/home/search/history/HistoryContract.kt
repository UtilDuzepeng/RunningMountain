package com.miaofen.xiaoying.fragment.home.search.history

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.BannerResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search.history
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface HistoryContract {
    interface Presenter : IPresenter {
        fun doHistory()
        fun onClearRecord()
    }

    interface View : IView<Presenter> {
        fun onHistorySuccess(data: List<String>)
        fun onHistoryError()
        fun onClearRecordSuccess(data: String?)
        fun onClearRecordError()
    }
}