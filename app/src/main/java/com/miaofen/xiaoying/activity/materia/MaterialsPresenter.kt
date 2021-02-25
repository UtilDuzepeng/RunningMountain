package com.miaofen.xiaoying.activity.materia

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.*
import com.miaofen.xiaoying.common.data.bean.response.MaterialsResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.BitmapUtil
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/**
 * 项目名称：com.miaofen.xiaoying.activity.materia
 * 类描述：用户资料详情
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class MaterialsPresenter (view: MaterialsContract.View) :
    BasePresenter<MaterialsContract.View>(view), MaterialsContract.Presenter{

    private val materialsRequestData =  MaterialsRequestData()
    override fun doMaterials() {
        RemoteRepository
            .onMaterials(materialsRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<MaterialsResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: MaterialsResponse?) {
                    mRootView.get()?.onMaterialsSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onMaterialsError()
                }
            })
    }

    //上传头像
    private val fileUploadRequestData = FileUploadRequestData()
    override fun onFileUpload(file: File, type: String) {
        fileUploadRequestData.setFile(file)
        fileUploadRequestData.setType(type)
        fileUploadRequestData.setSign()
        val compressfile = File(BitmapUtil.compressImage(file.toString()))
        val requestFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), compressfile)
        val body: MultipartBody.Part =
            MultipartBody.Part.createFormData("file", file.name, requestFile)
        val type: MultipartBody.Part =
            MultipartBody.Part.createFormData("type", fileUploadRequestData.type)
        val sign: MultipartBody.Part =
            MultipartBody.Part.createFormData("sign", fileUploadRequestData.sign)

        RemoteRepository
            .onFileUpload(body,type,sign)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onFileUploadSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onFileUploadError()
                }
            })
    }

    //修改头像
    private val changeYourAvatarRequestData = ChangeYourAvatarRequestData()
    override fun onChangeYourAvatar(avatarUrl: String) {
        changeYourAvatarRequestData.setAvatarUrl(avatarUrl)
        RemoteRepository
            .onChangeYourAvatar(changeYourAvatarRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onChangeYourAvatarSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onChangeYourAvatarError()
                }
            })
    }

    //修改地址
    private val revisionAreaRequestData = RevisionAreaRequestData()
    override fun onRevisionArea(province: String, city: String) {
        revisionAreaRequestData.setProvince(province)
        revisionAreaRequestData.setCity(city)
        RemoteRepository
            .onRevisionArea(revisionAreaRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<Boolean>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: Boolean?) {
                    mRootView.get()?.onRevisionAreaSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onRevisionAreaError()
                }
            })

    }
    //修改生日
    private val modifyBirthdayRequestData = ModifyBirthdayRequestData()
    override fun onModifyBirthday(birthdayTime: String) {
        modifyBirthdayRequestData.setBirthdayTime(birthdayTime)
        RemoteRepository
            .onModifyBirthday(modifyBirthdayRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<Boolean>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: Boolean?) {
                    mRootView.get()?.onModifyBirthdaySuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onModifyBirthdayError()
                }
            })
    }

}