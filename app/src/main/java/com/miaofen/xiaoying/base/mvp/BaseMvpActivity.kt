package com.miaofen.xiaoying.base.mvp

import androidx.annotation.Nullable
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.utils.showToast

/**
 * 项目名称：com.miaofen.xiaoying.base.mvp
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

abstract class BaseMvpActivity<P : IPresenter> : BaseActivity(), IView<P> {

    @Nullable
    protected var mPresenter: P? = null

    override fun setPresenter(presenter: P) {
        this.mPresenter = presenter
    }


    override fun showMessage(message: String?) {
        this.runOnUiThread { this.showToast(message!!) }
    }

    override fun showMessage(strId: Int) {
        showMessage(getString(strId))
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter?.unsubscribe()
        }
    }


}