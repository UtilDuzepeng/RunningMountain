package com.miaofen.xiaoying.base.mvp

import androidx.annotation.Nullable
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.utils.showToast
import com.miaofen.xiaoying.view.LoadingDialog

/**
 * 项目名称：com.miaofen.xiaoying.base.mvp
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

abstract class BaseMvpFragment<P : IPresenter> : BaseFragment(), IView<P> {

    private val mDialog: LoadingDialog by lazy {
        LoadingDialog(context)
    }

    @Nullable
    protected var mPresenter: P? = null

    override fun setPresenter(presenter: P) {
        this.mPresenter = presenter
    }


    override fun showMessage(message: String?) {
        if (activity != null) {
            activity?.runOnUiThread { activity?.showToast(message!!) }
        }
    }

    override fun showMessage(strId: Int) {
        showMessage(getString(strId))
    }

    override fun showLoading() {
        mDialog.show()
    }

    override fun hideLoading() {
        mDialog.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mPresenter != null) {
            mPresenter?.unsubscribe()
        }
    }


}
