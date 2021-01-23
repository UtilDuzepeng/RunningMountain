package com.miaofen.xiaoying.fragment.home.shareadapter

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import java.util.prefs.PreferencesFactory

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
    var defaultWith: Int = 0
    var defaultHeight: Int = 0
    private var spanCount: Int = 0


    fun setSpanCount(spanCount: Int) {
        this.spanCount = spanCount
    }
    override fun convert(helper: BaseViewHolder, item: HomeResponse.ContentBean.ImagesBean?) {

        var imageView = helper.getView(R.id.image_abbreviation) as ImageView

        if (defaultHeight == 0 || defaultWith == 0){
            defaultWith = imageView.layoutParams.width
            defaultHeight = imageView.layoutParams.height
        }

        if (spanCount == 2){
            imageView.layoutParams.width = (defaultWith*3)/2
            imageView.layoutParams.height = defaultHeight
        }else if (spanCount == 1){
            imageView.layoutParams.width = defaultWith*3
            imageView.layoutParams.height = defaultHeight*2
        }else if (spanCount == 3){
            imageView.layoutParams.width = defaultWith
            imageView.layoutParams.height = defaultHeight
        }

        Glide.with(context!!).load(item?.imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners( 20)))
            .fallback(R.drawable.zhanweitu)
            .error(R.drawable.error_zhanweitu)
            .into(imageView) //标准圆形图片。
    }
}