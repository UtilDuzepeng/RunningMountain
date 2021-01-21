package com.miaofen.xiaoying.activity.details.replycomm

import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.SecondaryReplyResponse
import com.miaofen.xiaoying.utils.getCurrentTime

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.replycomm
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ReplyRecyclerAdapter(
    layoutResId: Int,
    @Nullable data: List<SecondaryReplyResponse.SubPlanCommentListBean?>?,
    context: Context?
) : BaseQuickAdapter<SecondaryReplyResponse.SubPlanCommentListBean?, BaseViewHolder>(
    layoutResId, data
) {
    private var context: Context? = context
    var topCommentId: Long = -1

    var hashMap = HashMap<Long, SecondaryReplyResponse.SubPlanCommentListBean?>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun convert(
        helper: BaseViewHolder, item: SecondaryReplyResponse.SubPlanCommentListBean?
    ) {
        if (item?.parentCommentId == topCommentId) {
            helper.setGone(R.id.tv_reply_upper_strata, false)
            //标准圆形图片。
            Glide.with(context!!).load(item.userInfo?.avatarUrl)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(helper.getView(R.id.reply_item_avatarUrl) as ImageView)
            helper.setText(R.id.tv_replu_item_con, item.content)
            helper.setText(R.id.tv_reply_item_name, item.userInfo?.nickName)
            helper.setText(R.id.tv_reply_number, "${item.starCount}")
            helper.setText(R.id.tv_reply_time, "${getCurrentTime(item.createTime!!)}")

            if (item.star!!) {
                helper.setImageDrawable(
                    R.id.reply_image_item, context?.getDrawable(R.drawable.dianzan_icon)
                )
                //取消点赞点击事件
                helper.setOnClickListener(R.id.reply_image_item){
                    secondaryReply?.onClickUnStar(item.commentId)
                }

            } else {
                helper.setImageDrawable(
                    R.id.reply_image_item, context?.getDrawable(R.drawable.dianzan_line)
                )

                //点赞点击事件
                helper.setOnClickListener(R.id.reply_image_item){
                    secondaryReply?.onClickFabulous(item.commentId)
                }

            }

            if (item.canDelete!!) {
                helper.setVisible(R.id.tv_reply_delete_comments, true)
            } else {
                helper.setVisible(R.id.tv_reply_delete_comments, false)
            }
        } else {
            //标准圆形图片。
            Glide.with(context!!).load(item?.userInfo?.avatarUrl)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(helper.getView(R.id.reply_item_avatarUrl) as ImageView)
            helper.setText(R.id.tv_replu_item_con, item?.content)
            helper.setText(R.id.tv_reply_item_name, item?.userInfo?.nickName)
            helper.setText(R.id.tv_reply_number, "${item?.starCount}")
            helper.setText(R.id.tv_reply_time, "${getCurrentTime(item?.createTime!!)}")

            if (item.canDelete!!) {
                helper.setVisible(R.id.tv_reply_delete_comments, true)
            } else {
                helper.setVisible(R.id.tv_reply_delete_comments, false)
            }

            if (item.star!!) {
                helper.setImageDrawable(
                    R.id.reply_image_item, context?.getDrawable(R.drawable.dianzan_icon)
                )
                //取消点赞点击事件
                helper.setOnClickListener(R.id.reply_image_item){
                    secondaryReply?.onClickUnStar(item?.commentId)
                }

            } else {
                helper.setImageDrawable(
                    R.id.reply_image_item, context?.getDrawable(R.drawable.dianzan_line)
                )

                //点赞点击事件
                helper.setOnClickListener(R.id.reply_image_item){
                    secondaryReply?.onClickFabulous(item?.commentId)
                }

            }

            if (item.content == "") {
                helper.setText(R.id.tv_reply_upper_strata, "[该内容已经删除]")
            } else {
                val index = hashMap[item.parentCommentId]
                helper.setText(
                    R.id.tv_reply_upper_strata, "@${index?.userInfo?.nickName}:${index?.content}"
                )
            }

        }


        //回复点击事件
        helper.setOnClickListener(R.id.tv_reply_second_level) {
            secondaryReply?.onReplySecondLevel(item.commentId!!)
        }

        //删除点击事件
        helper.setOnClickListener(R.id.tv_reply_delete_comments){
            secondaryReply?.onReplyDeleteComments(item.commentId!!)
        }


    }


    interface SecondaryReply {
        fun onReplySecondLevel(commentId: Long)//二级回复
        fun onReplyDeleteComments(commentId: Long)//删除
        fun onClickFabulous(commentId: Long?)//点赞
        fun onClickUnStar(commentId: Long?)//取消点赞
    }

    private var secondaryReply: SecondaryReply? = null

    fun setSecondaryReply(secondaryReply: SecondaryReply?) {
        this.secondaryReply = secondaryReply
    }

}