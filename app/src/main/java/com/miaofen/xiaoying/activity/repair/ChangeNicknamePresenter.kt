package com.miaofen.xiaoying.activity.repair

import com.miaofen.xiaoying.activity.materia.MaterialsContract
import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.ChangeNicknameRequestData
import com.miaofen.xiaoying.common.data.bean.response.MaterialsResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.repair
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ChangeNicknamePresenter  (view: ChangeNicknameContract.View) :
    BasePresenter<ChangeNicknameContract.View>(view), ChangeNicknameContract.Presenter{


    var changeNicknameRequestData = ChangeNicknameRequestData()
    override fun doMaterials(nickName: String) {
        if (nickName.isEmpty()){
           ToastUtils.showToast("修改昵称不能为空")
            return
        }
        changeNicknameRequestData.setNickName(nickName)
        RemoteRepository
            .onChangeNickname(changeNicknameRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {
                    mRootView.get()?.onMaterialsSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onMaterialsError()
                }
            })
    }

}