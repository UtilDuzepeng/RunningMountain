package com.miaofen.xiaoying.activity.signup.examine

import android.content.Context
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.ExamineResponse
import com.miaofen.xiaoying.common.data.bean.response.RefuseResponse
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.getCurrentTime

/**
 * 项目名称：com.miaofen.xiaoying.activity.signup.examine
 * 类描述：审核中列表适配器
 * 创建人：duzepeng
 * 创建时间：2021/1/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class ExamineRecyclerAdapter(
    layoutResId: Int, @Nullable data: List<ExamineResponse>?, context: Context?
) : BaseQuickAdapter<ExamineResponse?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: ExamineResponse?) {
        helper.setVisible(R.id.linear_button, true)
        helper.setVisible(R.id.image_rejected, false)
        //头像
        Glide.with(context!!).load(item?.userInfo?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(helper.getView(R.id.image_headPortrait) as ImageView)
        helper.setText(R.id.tv_refuse_name, item?.userInfo?.nickName)//名称
        if (item?.createTime != null) {
            helper.setText(R.id.tv_refuse_date, getCurrentTime(item.createTime!!))//时间
        }
        if (item?.contactWay!!.isNotEmpty()) {
            helper.setText(R.id.tv_telephone, item.contactWay)//电话
        } else {
            helper.setGone(R.id.linear_telephone, false)

        }

        if (item.remark!!.isNotEmpty()) {
            helper.setVisible(R.id.tv_autograph, true)
            helper.setText(R.id.tv_autograph, item.remark)//签名
        } else {
            helper.setGone(R.id.tv_autograph, false)
        }

        helper.setOnClickListener(R.id.tv_adopt) {//确认
            ToastUtils.showToast("${item?.planId}")
            examineRecyclerInput?.onClickAdopt(item?.joinId, item?.planId)
        }
        helper.setOnClickListener(R.id.tv_refuse) {//拒绝
            examineRecyclerInput?.onClickRefuse(item?.joinId, item?.planId)
        }
    }

    interface ExamineRecyclerInput {
        fun onClickAdopt(joinId: Int?, planId: Int?)
        fun onClickRefuse(joinId: Int?, planId: Int?)
    }

    private var examineRecyclerInput: ExamineRecyclerInput? = null

    fun setAdministrationInput(examineRecyclerInput: ExamineRecyclerInput?) {
        this.examineRecyclerInput = examineRecyclerInput
    }


}
