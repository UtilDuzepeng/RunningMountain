package com.miaofen.xiaoying.activity.details

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
 * 类描述：想约的人员列表
 * 创建人：duzepeng
 * 创建时间：2020/12/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class WantsRecyclerViewAdapter(
    layoutResId: Int, @Nullable data: List<DetailsResponse.WantsBean>?, context: Context?
) : BaseQuickAdapter<DetailsResponse.WantsBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: DetailsResponse.WantsBean?) {
        //标准圆形图片。
        Glide.with(context!!).load(item?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(helper.getView(R.id.image_avatarUrl) as ImageView)
    }


}