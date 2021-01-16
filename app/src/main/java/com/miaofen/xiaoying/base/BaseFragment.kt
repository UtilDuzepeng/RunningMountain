package com.miaofen.xiaoying.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.miaofen.xiaoying.R

/**
 * 项目名称：com.miaofen.xiaoying.base
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

abstract class BaseFragment : Fragment() {

    var isFirst: Boolean = false //第一个fragment
    var rooView: View? = null
    var isFragmentVisiable: Boolean = false //fragment 可见


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (rooView == null) {
            rooView =
                inflater.inflate(getLayoutResources(), container, false)//R.layout.fragment_base
        }
        return rooView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        setRadioButton()
        refresh()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isFragmentVisiable = true
        }

        if (rooView == null) {//布局
            return
        }

        //可见 并且没有加载
        if (!isFirst && isFragmentVisiable) {
            onFragmentVisiableChange(true)
            return
        }

        //由可见 --》不可见 已经加载过
        if (isFragmentVisiable) {
            onFragmentVisiableChange(false)
            isFragmentVisiable = false
        }

    }

    open protected fun onFragmentVisiableChange(b: Boolean) {}

    //返回布局
    abstract fun getLayoutResources(): Int

    open fun initView() {}

    open fun initData() {}

    open fun setRadioButton() {}

    open fun refresh() {}

    fun getEmptyView(@LayoutRes resource: Int?): View {
        return LayoutInflater.from(activity).inflate(resource!!, null, false)
    }


}