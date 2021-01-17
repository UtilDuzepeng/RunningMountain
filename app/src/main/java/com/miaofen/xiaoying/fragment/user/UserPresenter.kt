package com.miaofen.xiaoying.fragment.user

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.response.PersonalResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.fragment.user
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class UserPresenter(view: UserContract.View) : BasePresenter<UserContract.View>(view),
    UserContract.Presenter {

    override fun doPersonalCenter() {
        RemoteRepository
            .onPersonalCenter()
            .applySchedulers()
            .subscribe(object : CommonObserver<PersonalResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: PersonalResponse?) {
                    if (data == null) {
                        return
                    }
                    mRootView.get()?.onPersonalCenterSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onPersonalCenterError()
                }
            })
    }

}