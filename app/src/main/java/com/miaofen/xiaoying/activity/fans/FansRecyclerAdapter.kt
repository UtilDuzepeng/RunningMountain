package com.miaofen.xiaoying.activity.fans

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
import com.miaofen.xiaoying.common.data.bean.response.FansResponse
import com.miaofen.xiaoying.common.data.bean.response.FocusOnResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.fans
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class FansRecyclerAdapter(
    layoutResId: Int, @Nullable data: List<FansResponse.ContentBean?>?, context: Context?
) : BaseQuickAdapter<FansResponse.ContentBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun convert(helper: BaseViewHolder, item: FansResponse.ContentBean?) {
        //标准圆形图片。
        Glide.with(context!!).load(item?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .fallback(R.drawable.zhanweitu)
            .error(R.drawable.error_zhanweitu)
            .into(helper.getView(R.id.focus_avatarUrl) as ImageView)

        if (item?.motorcycle == null) {
            helper.setGone(R.id.focus_motorcycle, false)
        } else {
            helper.setGone(R.id.focus_motorcycle, true)
            helper.setText(R.id.focus_motorcycle, item.motorcycle)
        }
        helper.setText(R.id.focus_nickName, item?.nickName)

//        selfFollowUser   我关注别人
//        userFollowSelf  用户关注我
        val focusFollow = helper.getView<TextView>(R.id.focus_follow)
        if (item?.selfFollowUser!! && item.userFollowSelf!!) {
            focusFollow.setBackgroundResource(R.drawable.grey_circle_back)
            helper.setText(R.id.focus_follow, "互相关注")
            helper.setOnClickListener(R.id.focus_follow) {//取消关注
                onFanListBack?.onCancelAttention(item.userId)
            }
        } else if (item.selfFollowUser!!) {
            focusFollow.setBackgroundResource(R.drawable.grey_circle_back)
            helper.setText(R.id.focus_follow, "已关注")
            helper.setOnClickListener(R.id.focus_follow) {//取消关注
                onFanListBack?.onCancelAttention(item.userId)
            }
        } else if (item.userFollowSelf!!) {
            focusFollow.setBackgroundResource(R.drawable.pink_circle_back)
            helper.setText(R.id.focus_follow, "回关")
            helper.setOnClickListener(R.id.focus_follow) {//点击关注
                onFanListBack?.onFocusFollow(item.userId)
            }
        }

        //设置性别
        when (item.gender) {
            0 -> {
                helper.setGone(R.id.image_gender, false)
            }
            1 -> {
                helper.setGone(R.id.image_gender, true)
                helper.setImageDrawable(
                    R.id.image_gender, context!!.getDrawable(R.drawable.nan_icon)
                )
            }
            2 -> {
                helper.setGone(R.id.image_gender, true)
                helper.setImageDrawable(
                    R.id.image_gender, context!!.getDrawable(R.drawable.nv_icon)
                )
            }
        }

        //条目点击
        helper.setOnClickListener(R.id.linear_follow_item) {
            onFanListBack?.onBasicInformation(item?.userId)
        }

    }

    interface OnFanListBack {
        fun onFocusFollow(followId: Long?)//回关
        fun onCancelAttention(followId: Long?)//取消关注
        fun onBasicInformation(userId: Long?)//对外展示基本资料
    }

    private var onFanListBack: OnFanListBack? = null

    fun setOnFanListBack(onFanListBack: OnFanListBack?) {
        this.onFanListBack = onFanListBack
    }


}