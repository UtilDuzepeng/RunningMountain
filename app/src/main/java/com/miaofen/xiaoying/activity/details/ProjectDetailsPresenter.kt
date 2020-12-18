package com.miaofen.xiaoying.activity.details


import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.bean.request.DetailsRequestData
import com.miaofen.xiaoying.common.data.bean.request.OneCommentsData
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.activity.details
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ProjectDetailsPresenter(view: ProjectDetailsContract.View) :
    BasePresenter<ProjectDetailsContract.View>(view), ProjectDetailsContract.Presenter {

    override fun doProjectDetails(detailsRequestData: DetailsRequestData?) {
        RemoteRepository
            .details(detailsRequestData)
            .applySchedulers()
            .subscribe(object : CommonObserver<DetailsResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: DetailsResponse?) {
                    if (data == null) {
                        return
                    }
                    mRootView.get()?.onProjectDetailsSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onProjectDetailsError()
                }
            })
    }

    //一级评论列表
    override fun doOneComments(oneCommentsData: OneCommentsData?) {
        RemoteRepository
            .oneComments(oneCommentsData)
            .applySchedulers()
            .subscribe(object : CommonObserver<OneCommentsResponse>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: OneCommentsResponse?) {
//                    if (data == null) {
//                        return
//                    }
                    mRootView.get()?.onOneCommentsSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onOneCommentsError()
                }
            })

    }

}