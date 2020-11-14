package com.miaofen.xiaoying.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * 项目名称：com.addictive.shooting.producer.home
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/9/28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class MyAdapter(var mList: List<Fragment>, fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

}