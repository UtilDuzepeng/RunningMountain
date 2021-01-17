package com.miaofen.xiaoying.activity.signup.refuse

import android.content.Context
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.RefuseResponse
import com.miaofen.xiaoying.utils.getCurrentTime

/**
 * 项目名称：com.miaofen.xiaoying.activity.signup.refuse
 * 类描述：已拒绝适配器
 * 创建人：duzepeng
 * 创建时间：2021/1/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class RefuseRecyclerAdapter(
    layoutResId: Int, @Nullable data: List<RefuseResponse>?, context: Context?
) : BaseQuickAdapter<RefuseResponse?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: RefuseResponse?) {
        //头像
        Glide.with(context!!).load(item?.userInfo?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(helper.getView(R.id.image_headPortrait) as ImageView)

        if (item?.createTime != null) {
            helper.setText(R.id.tv_refuse_date, getCurrentTime(item.createTime!!))//时间
        }
        if (item?.contactWay.isNullOrEmpty()){
            helper.setText(R.id.tv_telephone, item?.contactWay)//电话
        }else{
            helper.setGone(R.id.linear_telephone,false)
            helper.setText(R.id.tv_telephone, "-")
        }
        if (item?.remark != null) {
            helper.setVisible(R.id.tv_autograph, true)
            helper.setText(R.id.tv_autograph, item.remark)//签名
        } else {
            helper.setGone(R.id.tv_autograph, false)
        }

    }

}
