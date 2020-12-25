package com.miaofen.xiaoying.fragment.home.search.use

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.PlanRequestData
import com.miaofen.xiaoying.common.data.bean.response.SearchUserResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search.use
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：搜索用户列表
 * 修改时间：
 * 修改备注：
 */

class SearchUserPresenter(view: SearchUserContract.View) :
    BasePresenter<SearchUserContract.View>(view),
    SearchUserContract.Presenter {

    override fun doSearchUser(planRequestData: PlanRequestData) {
        RemoteRepository
            .onSearchUser(planRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<SearchUserResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: SearchUserResponse?) {
//                    if (data == null) {
//                        return
//                    }
                    mRootView.get()?.onSearchUserSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onSearchUserError()
                }
            })

    }

}