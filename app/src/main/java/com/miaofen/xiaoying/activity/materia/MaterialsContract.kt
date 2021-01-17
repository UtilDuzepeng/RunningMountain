package com.miaofen.xiaoying.activity.materia

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.MaterialsResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.materia
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface MaterialsContract {

    interface Presenter : IPresenter {
        fun doMaterials()
    }

    interface View : IView<Presenter> {
        fun onMaterialsSuccess(data: MaterialsResponse?)
        fun onMaterialsError()
    }
}