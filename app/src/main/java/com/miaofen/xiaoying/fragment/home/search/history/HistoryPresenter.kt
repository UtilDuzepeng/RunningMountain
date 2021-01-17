package com.miaofen.xiaoying.fragment.home.search.history

import com.miaofen.xiaoying.base.mvp.BasePresenter
import com.miaofen.xiaoying.common.data.remote.CommonObserver
import com.miaofen.xiaoying.common.data.remote.RemoteRepository
import com.miaofen.xiaoying.utils.applySchedulers
import io.reactivex.disposables.Disposable

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search.history
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class HistoryPresenter(view: HistoryContract.View): BasePresenter<HistoryContract.View>(view),
    HistoryContract.Presenter {
    //搜索历史
    override fun doHistory() {
        RemoteRepository
            .onHistory()
            .applySchedulers()
            .subscribe(object : CommonObserver<ArrayList<String>>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: ArrayList<String>?) {

                    if (data == null) {
                        return
                    }
                    mRootView.get()?.onHistorySuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onHistoryError()
                }
            })

    }

    override fun onClearRecord() {
        RemoteRepository
            .onClearRecord()
            .applySchedulers()
            .subscribe(object : CommonObserver<String>() {
                override fun onSubscribe(d: Disposable?) {
                    addDispose(d)
                }

                override fun success(data: String?) {

                    mRootView.get()?.onClearRecordSuccess(data)
                }

                override fun failure(e: Throwable?, errMsg: String?) {
                    super.failure(e, errMsg)
                    mRootView.get()?.onClearRecordError()
                }
            })

    }

}