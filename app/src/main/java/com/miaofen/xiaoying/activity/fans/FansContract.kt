package com.miaofen.xiaoying.activity.fans

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.FansResponse
import com.miaofen.xiaoying.common.data.bean.response.FocusOnResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.fans
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface FansContract {

    interface Presenter : IPresenter {
        fun doFans(page: Int, size: Int)
    }

    interface View : IView<Presenter> {

        //下拉成功 没有数据
        fun onFansNullSuccess()

        //下拉成功 有数据
        fun onFansSuccess(data: FansResponse?)

        //上啦加载 有数据
        fun onFansOnSuccess(data: FansResponse?)

        //上啦加载 没有数据
        fun onFansOnNullSuccess()

        fun onFansOnError()
    }
}