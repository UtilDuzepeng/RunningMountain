package com.miaofen.xiaoying.activity.details.adapter

import android.content.Context
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.details
 * 类描述：已报名人员列表
 * 创建人：duzepeng
 * 创建时间：2020/12/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class JoinsRecyclerAdapter (
    layoutResId: Int, @Nullable data: List<DetailsResponse.JoinsBean>?, context: Context?
) : BaseQuickAdapter<DetailsResponse.JoinsBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: DetailsResponse.JoinsBean?) {
        //标准圆形图片。
        Glide.with(context!!).load(item?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(helper.getView(R.id.image_avatarUrl) as ImageView)
    }


}