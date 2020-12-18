package com.miaofen.xiaoying.fragment.hair

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R

/**
 * 项目名称：com.miaofen.xiaoying.fragment.haircloth
 * 类描述：发布页面适配器
 * 创建人：duzepeng
 * 创建时间：2020/11/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ReleaseRecyclerViewAdapter (
    layoutResId: Int, @Nullable data: List<Bitmap>?, context: Context?
) : BaseQuickAdapter<Bitmap?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: Bitmap?) {
        helper.setImageBitmap(R.id.release_image,item)
    }
}
