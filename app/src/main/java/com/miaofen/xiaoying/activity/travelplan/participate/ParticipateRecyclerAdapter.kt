package com.miaofen.xiaoying.activity.travelplan.participate

import android.content.Context
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.ParticipateResponse
import com.miaofen.xiaoying.utils.getCurrentTime

/**
 * 项目名称：com.miaofen.xiaoying.activity.travelplan.participate
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2/24/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ParticipateRecyclerAdapter (
    layoutResId: Int, @Nullable data: List<ParticipateResponse.ContentBean?>?, context: Context?
) : BaseQuickAdapter<ParticipateResponse.ContentBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    var startEndTime = StringBuffer()
    override fun convert(helper: BaseViewHolder, item: ParticipateResponse.ContentBean?) {
        helper.setGone(R.id.distance,false)
        helper.setGone(R.id.linear_by_way_of,false)
        //标准圆形图片。
        Glide.with(context!!).load(item?.publisherInfo?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .fallback(R.drawable.zhanweitu)
            .error(R.drawable.error_zhanweitu)
            .into(helper.getView(R.id.colleation_avatarUrl) as ImageView)
        //名称
        helper.setText(R.id.collection_nickName,item?.publisherInfo?.nickName)

        //摩托车名称
        if (item?.publisherInfo?.motorcycle==null){
            helper.setGone(R.id.collection_motorcycle,false)
        }else{
            helper.setText(R.id.collection_motorcycle,item.publisherInfo?.motorcycle)
        }
        //收藏时间
        if (item?.planInfo?.createTime != null){
            helper.setText(R.id.collection_createTime, getCurrentTime(item?.planInfo?.createTime!!))
        }

        //计划名称
        helper.setText(R.id.collection_title,item?.planInfo?.title)

        //计划内容
        helper.setText(R.id.collection_content,item?.planInfo?.content)

        //始发地
        helper.setText(R.id.place_departure, item?.planInfo?.placeOfDeparture)

        //目的地
        helper.setText(R.id.destination, item?.planInfo?.destination)

        //起止时间
        startEndTime.delete(0, startEndTime.length)
        if (item?.planInfo?.startTime != null) {
            startEndTime.append(getCurrentTime(item.planInfo?.startTime!!))
        }
        startEndTime.append(" 至 ")
        if (item?.planInfo?.endTime != null) {
            startEndTime.append(getCurrentTime(item.planInfo?.endTime!!))
        }
        helper.setText(R.id.playtime, startEndTime)

    }

}