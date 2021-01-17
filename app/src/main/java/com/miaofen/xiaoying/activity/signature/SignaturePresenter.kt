package com.miaofen.xiaoying.activity.signature

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.SignatureRequestData
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.signature
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class SignaturePresenter(view: SignatureContract.View) :
    BasePresenter<SignatureContract.View>(view), SignatureContract.Presenter {

    var signatureRequestData = SignatureRequestData()
    override fun doSignature(personalSignature: String) {
        if (personalSignature.isEmpty()) {
            ToastUtils.showToast("修改签名不能为空")
            return
        }
        signatureRequestData.setPersonalSignature(personalSignature)
        RemoteRepository
            .onSignature(signatureRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onSignatureSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onSignaturesError()
                }
            })
    }

}