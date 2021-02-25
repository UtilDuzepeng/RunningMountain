package com.miaofen.xiaoying.activity.location

import androidx.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.ChooseLocationResponse
import com.miaofen.xiaoying.utils.ToastUtils

/**
 * 项目名称：com.miaofen.xiaoying.activity.location
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/2/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class PositionRecyclerViewAdapter(
    layoutResId: Int, @Nullable data: List<ChooseLocationResponse>?
) : BaseQuickAdapter<ChooseLocationResponse?, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: ChooseLocationResponse?) {

        helper.setText(R.id.select_item, item?.areaName)

        helper.setVisible(R.id.tv_spot, true)
        //点击事件
        helper.setOnClickListener(R.id.select_item) {
            onPositionRecyclerItem?.onClickItemPositionRecycler(item!!,helper.layoutPosition)
        }

    }

    interface OnPositionRecyclerItem {
        fun onClickItemPositionRecycler(item: ChooseLocationResponse, position: Int)
    }

    private var onPositionRecyclerItem: OnPositionRecyclerItem? = null

    fun setOnPositionRecyclerItem(onPositionRecyclerItem: OnPositionRecyclerItem?) {
        this.onPositionRecyclerItem = onPositionRecyclerItem
    }
}
