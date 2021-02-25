package com.miaofen.xiaoying.activity.materia

import com.miaofen.xiaoying.base.mvp.IPresenter
import com.miaofen.xiaoying.base.mvp.IView
import com.miaofen.xiaoying.common.data.bean.response.MaterialsResponse
import java.io.File

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
        fun onFileUpload(file: File, type: String)//文件上传
        fun onChangeYourAvatar(avatarUrl: String)//修改头像
        fun onRevisionArea(province: String, city: String)//修改地区
        fun onModifyBirthday(birthdayTime: String)//修改生日
    }

    interface View : IView<Presenter> {
        fun onMaterialsSuccess(data: MaterialsResponse?)
        fun onMaterialsError()
        fun onFileUploadSuccess(data: String?)
        fun onFileUploadError()
        fun onChangeYourAvatarSuccess(data: String?)
        fun onChangeYourAvatarError()
        fun onRevisionAreaSuccess(data: Boolean?)
        fun onRevisionAreaError()
        fun onModifyBirthdaySuccess(data: Boolean?)
        fun onModifyBirthdayError()
    }
}