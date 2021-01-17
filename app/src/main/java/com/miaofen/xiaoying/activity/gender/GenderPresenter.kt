package com.miaofen.xiaoying.activity.gender

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.GenderRequestData
import com.miaofen.xiaoying.common.data.bean.response.MaterialsResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.gender
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class GenderPresenter(view: GenderContract.View) :
    BasePresenter<GenderContract.View>(view), GenderContract.Presenter {

    var genderRequestData = GenderRequestData()
    override fun doGender(gender: Int?) {
        genderRequestData.setGender(gender!!)
        RemoteRepository
            .onGender(genderRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onGenderSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onGenderError()
                }
            })
    }

}