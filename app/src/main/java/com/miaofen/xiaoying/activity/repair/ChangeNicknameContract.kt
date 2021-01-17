package com.miaofen.xiaoying.activity.repair

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView

/**
 * 项目名称：com.miaofen.xiaoying.activity.repair
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface ChangeNicknameContract {

    interface Presenter : IPresenter {
        fun doMaterials(nickName: String)
    }

    interface View : IView<Presenter> {
        fun onMaterialsSuccess(data: String?)
        fun onMaterialsError()
    }

}