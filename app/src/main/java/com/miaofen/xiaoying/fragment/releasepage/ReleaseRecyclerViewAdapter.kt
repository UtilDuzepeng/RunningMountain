package com.miaofen.xiaoying.fragment.releasepage

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R

/**
 * 项目名称：com.miaofen.xiaoying.fragment.releasepage
 * 类描述：发布图片适配器
 * 创建人：duzepeng
 * 创建时间：2020/11/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ReleaseRecyclerViewAdapter(
    layoutResId: Int, @Nullable data: List<Bitmap>?, context: Context?
) : BaseQuickAdapter<Bitmap?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context

    override fun convert(helper: BaseViewHolder, item: Bitmap?) {
        Glide.with(context!!).load(item).into(helper.getView(R.id.release_image))
    }

}
