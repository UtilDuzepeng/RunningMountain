package com.miaofen.xiaoying.activity.materia

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.response.MaterialsResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.materia
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class MaterialsPresenter (view: MaterialsContract.View) :
    BasePresenter<MaterialsContract.View>(view), MaterialsContract.Presenter{

    override fun doMaterials() {
        RemoteRepository
            .onMaterials()
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

}