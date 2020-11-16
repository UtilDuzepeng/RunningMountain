package com.miaofen.xiaoying.fragment.news

import android.content.Context
import androidx.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 项目名称：com.miaofen.xiaoying.fragment.news
 * 类描述：消息列表适配器
 * 创建人：duzepeng
 * 创建时间：2020/11/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class NewsRecyclerViewAdapter (
    layoutResId: Int, @Nullable data: List<String>?, context: Context?
) : BaseQuickAdapter<String?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: String?) {

    }

}
