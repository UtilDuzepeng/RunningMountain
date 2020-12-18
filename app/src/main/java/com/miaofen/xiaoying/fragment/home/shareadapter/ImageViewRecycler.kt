package com.miaofen.xiaoying.fragment.home.shareadapter

import android.content.Context
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.shareadapter
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ImageViewRecycler(
    layoutResId: Int, @Nullable data: List<HomeResponse.ContentBean.ImagesBean>?, context: Context?
) : BaseQuickAdapter<HomeResponse.ContentBean.ImagesBean?, BaseViewHolder>(layoutResId, data)  {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: HomeResponse.ContentBean.ImagesBean?) {
       //zhanweitu
        Glide.with(context!!).load(item?.imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners( 20)))
            .fallback(R.drawable.zhanweitu)
            .error(R.drawable.error_zhanweitu)
            .into(helper.getView(R.id.image_abbreviation) as ImageView) //标准圆形图片。
    }


}