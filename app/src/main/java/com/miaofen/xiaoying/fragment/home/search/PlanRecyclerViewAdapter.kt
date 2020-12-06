package com.miaofen.xiaoying.fragment.home.search

import android.content.Context
import androidx.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search
 * 类描述：搜索计划列表适配器
 * 创建人：duzepeng
 * 创建时间：2020/11/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class PlanRecyclerViewAdapter  (
    layoutResId: Int, @Nullable data: List<String>?, context: Context?
) : BaseQuickAdapter<String?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: String?) {

    }

}
