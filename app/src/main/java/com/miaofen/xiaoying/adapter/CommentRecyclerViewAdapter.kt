package com.miaofen.xiaoying.adapter

import android.content.Context
import androidx.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 项目名称：com.miaofen.xiaoying.adapter
 * 类描述：首页详情评论适配器
 * 创建人：duzepeng
 * 创建时间：2020/11/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class CommentRecyclerViewAdapter (
    layoutResId: Int, @Nullable data: List<String>?, context: Context?
) : BaseQuickAdapter<String?, BaseViewHolder>(layoutResId, data) {


    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: String?) {

    }

}
