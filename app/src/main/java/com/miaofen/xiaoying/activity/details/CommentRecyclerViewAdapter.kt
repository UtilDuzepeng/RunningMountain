package com.miaofen.xiaoying.activity.details

import android.content.Context
import android.os.Build
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
import com.miaofen.xiaoying.common.data.bean.response.OneCommentsResponse
import com.miaofen.xiaoying.utils.getCurrentTime

/**
 * 项目名称：com.miaofen.xiaoying.adapter
 * 类描述：首页详情评论适配器
 * 创建人：duzepeng
 * 创建时间：2020/11/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class CommentRecyclerViewAdapter(
    layoutResId: Int, @Nullable data: List<OneCommentsResponse.ContentBean>?, context: Context?
) : BaseQuickAdapter<OneCommentsResponse.ContentBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun convert(helper: BaseViewHolder, item: OneCommentsResponse.ContentBean?) {
        //标准圆形图片。
        Glide.with(context!!).load(item?.userInfo?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(helper.getView(R.id.imager_head) as ImageView)
        //名称
        helper.setText(R.id.tv_name, item?.userInfo?.nickName)
        //点赞数量
        helper.setText(R.id.tv_starCount, item?.starCount.toString())
        //是否点过赞
        if (item?.star != null) {
            if (item.star!!) {
                helper.setImageDrawable(
                    R.id.image_fabulous,
                    context!!.getDrawable(R.drawable.dianzan_icon)
                )
            } else {
                helper.setImageDrawable(
                    R.id.image_fabulous,
                    context!!.getDrawable(R.drawable.dianzan_line)
                )
            }
        }
        //评论内容
        helper.setText(R.id.tv_leaving_message, item?.content)

        //评论时间
        if (item?.createTime != null) {
            val currentTime = getCurrentTime(item.createTime!!)
            helper.setText(R.id.tv_createTime, "$currentTime · ")
        }

        val tv_replyCount = helper.getView<TextView>(R.id.tv_replyCount) as TextView

        //设置回复
        if (item?.replyCount != null) {
            if (item.replyCount!! >= 1) {
                tv_replyCount.text = "${item.replyCount} 回复"
                tv_replyCount.setBackgroundResource(R.drawable.search_background)
            }
        }
        //评论是否可以删除
        if (item?.canDelete != null) {
            if (item.canDelete!!) {
                helper.setVisible(R.id.tv_delete, true)
            } else {
                helper.setVisible(R.id.tv_delete, false)
            }
        }

    }

}
