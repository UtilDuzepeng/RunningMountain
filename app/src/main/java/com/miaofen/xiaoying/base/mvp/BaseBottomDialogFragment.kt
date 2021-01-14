package com.miaofen.xiaoying.base.mvp

import android.view.Gravity
import androidx.fragment.app.DialogFragment
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.view.LoadingDialog

/**
 * 项目名称：com.miaofen.xiaoying.base.mvp
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

abstract class BaseBottomDialogFragment : DialogFragment() {

    private val mDialog: LoadingDialog by lazy {
        LoadingDialog(context)
    }

    override fun onStart() {
        super.onStart()
        // 下面这些设置必须在此方法(onStart())中才有效

        // 下面这些设置必须在此方法(onStart())中才有效
        var window = dialog!!.window
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        // 设置动画
        // 设置动画
        window?.setWindowAnimations(R.style.bottomDialog)

        val params = window?.getAttributes()
        params?.gravity = Gravity.BOTTOM
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params?.width = resources.displayMetrics.widthPixels
        window?.setAttributes(params)
        initView()
    }

    open fun initView() {}

//    private fun showMessage(message: String?) {
//        if (activity != null) {
//            activity?.runOnUiThread { activity?.showToast(message!!) }
//        }
//    }
//
//    fun showMessage(strId: Int) {
//        showMessage(getString(strId))
//    }

    fun showLoading() {
        mDialog.show()
    }

    fun hideLoading() {
        mDialog.dismiss()
    }



}