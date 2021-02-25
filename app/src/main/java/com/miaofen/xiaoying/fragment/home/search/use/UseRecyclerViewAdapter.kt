package com.miaofen.xiaoying.fragment.home.search.use

import android.content.Context
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.SearchUserResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search
 * 类描述：搜索用户适配器
 * 创建人：duzepeng
 * 创建时间：2020/11/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class UseRecyclerViewAdapter(
    layoutResId: Int, @Nullable data: List<SearchUserResponse.ContentBean>?, context: Context?
) : BaseQuickAdapter<SearchUserResponse.ContentBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: SearchUserResponse.ContentBean?) {
        //头像
        Glide.with(context!!).load(item?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(helper.getView(R.id.use_avatarUrl) as ImageView) //标准圆形图片。
        //名称
        helper.setText(R.id.use_nickName, item?.nickName)
        //摩托车型号
        if (item?.motorcycle != null) {
            helper.setGone(R.id.use_motorcycle,true)
            helper.setText(R.id.use_motorcycle, item.motorcycle)
        } else {
            helper.setGone(R.id.use_motorcycle,false)
        }

        if (item!!.selfFollowUser!! && item.userFollowSelf!!) {//互相关注
            helper.setBackgroundRes(R.id.tv_followUser, R.drawable.grey_circle_back)
            helper.setText(R.id.tv_followUser, "互相关注")
            helper.setOnClickListener(R.id.tv_followUser){
                onUseOnLisBack?.onUseCancelAttention(item.userId)
            }//取消关注
        } else if (item.userFollowSelf!!) {//
            helper.setBackgroundRes(R.id.tv_followUser, R.drawable.pink_brack)
            helper.setText(R.id.tv_followUser, "回关")
            helper.setOnClickListener(R.id.tv_followUser){
                onUseOnLisBack?.onUseFocusFollow(item.userId)
            }//关注
        } else if (item.selfFollowUser!!) {//已关注
            helper.setBackgroundRes(R.id.tv_followUser, R.drawable.grey_circle_back)
            helper.setText(R.id.tv_followUser, "已关注")
            helper.setOnClickListener(R.id.tv_followUser){
                onUseOnLisBack?.onUseCancelAttention(item.userId)
            }//取消关注
        } else {  //未关注 pink_brack
            helper.setBackgroundRes(R.id.tv_followUser, R.drawable.pink_brack)
            helper.setText(R.id.tv_followUser, "关注")
            helper.setOnClickListener(R.id.tv_followUser){
                onUseOnLisBack?.onUseFocusFollow(item.userId)
            }//关注
        }

        //查看个人主页
        helper.setOnClickListener(R.id.search_for_users){
            onUseOnLisBack?.onUseSearchForUsers(item.userId)
        }

    }


    interface OnUseOnListBack {
        fun onUseFocusFollow(followId: Long?)//回关
        fun onUseCancelAttention(followId: Long?)//取消关注
        fun onUseSearchForUsers(userId: Long?)//查看个人详情
    }

    private var onUseOnLisBack: OnUseOnListBack? = null

    fun setOnFanListBack(onUseOnLisBack: OnUseOnListBack?) {
        this.onUseOnLisBack = onUseOnLisBack
    }

}
