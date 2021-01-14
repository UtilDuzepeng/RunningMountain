package com.miaofen.xiaoying.fragment.home.shareadapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.details.ProjectDetailsActivity
import com.miaofen.xiaoying.common.data.bean.response.HomeResponse
import com.miaofen.xiaoying.utils.getCurrentTime
import com.miaofen.xiaoying.view.CurrencyLayout
import com.miaofen.xiaoying.view.FlowViewGroup
import java.math.BigDecimal


/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.share
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/3
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ShareRecyclerAdapter(
    layoutResId: Int, @Nullable data: List<HomeResponse.ContentBean>?, context: Context?
) : BaseQuickAdapter<HomeResponse.ContentBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    var tripsName = StringBuffer()
    var startEndTime = StringBuffer()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun convert(helper: BaseViewHolder, item: HomeResponse.ContentBean?) {
        val currencyLayout = helper.getView<CurrencyLayout>(R.id.item_CurrencyLayout)
        //发布人头像
        if (context != null) {
            Glide.with(context!!).load(item?.avatarUrl)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(helper.getView(R.id.image_portrait) as ImageView) //标准圆形图片。
        }
        //发布人头像
        helper.setText(R.id.net_name, item?.nickName)
        //发布人座驾
        if (item?.motorcycle == null) {
            helper.setGone(R.id.motorcycle_type, false)
        } else {
            helper.setGone(R.id.motorcycle_type, true)
            helper.setText(R.id.motorcycle_type, item.motorcycle)
        }
        //发布时间
//        if (item?.createTime != null) {
//            helper.setText(R.id.data_time, getCurrentTime(item.createTime).toString())
            helper.setText(R.id.data_time, item?.pastTime)
//        }
        //发布标题
        helper.setText(R.id.subject, item?.title)
        //发布内容
        helper.setText(R.id.what_to_publish, item?.content)
        //始发地
        helper.setText(R.id.place_departure, item?.placeOfDeparture)
        //与我相距
        if (item?.userPlanDistance != null) {
            var bd = BigDecimal(item.userPlanDistance.toString())
            val scale = bd.setScale(2, BigDecimal.ROUND_HALF_UP)

            helper.setText(R.id.distance, "距离 $scale km")
        }
        //目的地
        helper.setText(R.id.destination, item?.destination)
        //途径地
        tripsName.delete(0, tripsName.length)
        if (item?.trips != null && item.trips.size > 1) {
            for (name in item.trips) {
                tripsName.append(name.name)
                tripsName.append("、")

            }
            tripsName.delete(tripsName.length - 1, tripsName.length)
            helper.setGone(R.id.linear_by_way_of,true)
            helper.setText(R.id.route, tripsName)
        } else {
            helper.setGone(R.id.linear_by_way_of,false)
        }

        //起止时间
        startEndTime.delete(0, startEndTime.length)
        if (item?.startTime != null) {
            startEndTime.append(getCurrentTime(item.startTime))
        }
        startEndTime.append(" 至 ")
        if (item?.endTime != null) {
            startEndTime.append(getCurrentTime(item.endTime))
        }
        helper.setText(R.id.playtime, startEndTime)
        //收藏数量 collectionNumber
        if (item?.collectionNumber != null) {
            helper.setText(R.id.tv_collection, item.collectionNumber.toString())
        }
        //评论数量
        if (item?.commentCount != null) {
            helper.setText(R.id.tv_commentCount, item.commentCount.toString())
        }
        //图片
        currencyLayout.setRecyclerViewData(item?.images)

        val view = helper.getView<FlowViewGroup>(R.id.label_floeviewgroup)
        view.removeAllViews()
        if (item?.tags != null && item.tags.isNotEmpty()) {
            for (name in item.tags) {
                val textView = LayoutInflater.from(context)
                    .inflate(R.layout.label_recyclerview_layout, view, false) as TextView
                textView.text = name.tagName
                textView.setOnClickListener(View.OnClickListener {
                    // et_search.setText((v as TextView).text)
                })
                view.addView(textView)
            }
        }
//        else {
//            val textView = LayoutInflater.from(context)
//                .inflate(R.layout.label_recyclerview_layout, view, false) as TextView
//            textView.text = "暂无标签"
//            view.addView(textView)
//        }

        //是否收藏 collection
        if (item?.collection != null) {
            if (item.collection) {
                helper.setImageDrawable(
                    R.id.image_collection,
                    context?.getDrawable(R.drawable.shoucang_huang_icon)
                )
            } else {
                helper.setImageDrawable(
                    R.id.image_collection,
                    context?.getDrawable(R.drawable.shoucang_icon)
                )
            }
        }
        /**
         * 查看列表详情
         */
        currencyLayout.itemOnClick.setOnClickListener {
            ProjectDetailsActivity.start(context, item?.id)
//            ProjectDetailsActivity.start(context, 11)
        }

    }

}
