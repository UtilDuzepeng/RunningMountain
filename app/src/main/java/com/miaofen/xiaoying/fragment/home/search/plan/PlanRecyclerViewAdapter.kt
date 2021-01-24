package com.miaofen.xiaoying.fragment.home.search.plan

import android.content.Context
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.PlanResponse

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search
 * 类描述：搜索计划列表适配器
 * 创建人：duzepeng
 * 创建时间：2020/11/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class PlanRecyclerViewAdapter(
    layoutResId: Int, @Nullable data: List<PlanResponse.ContentBean>?, context: Context?
) : BaseQuickAdapter<PlanResponse.ContentBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context

    override fun convert(helper: BaseViewHolder, item: PlanResponse.ContentBean?) {
        //头像
        Glide.with(context!!).load(item?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(helper.getView(R.id.plan_avatarUrl) as ImageView) //标准圆形图片。
        //名称
        helper.setText(R.id.plan_nickName, item?.nickName)
        //摩托车名称
        if (item?.motorcycle != null) {
            helper.setText(R.id.plan_motorcycle, item.motorcycle)
        } else {
            helper.setVisible(R.id.plan_motorcycle, false)
        }
        //计划名称
        helper.setText(R.id.plan_title, item?.title)
        //计划内容
        helper.setText(R.id.plan_content, item?.content)

        //条目点击事件
        helper.setOnClickListener(R.id.plan_item_search){
            onPlanBack?.onProjectDetails(item?.id)
        }
    }

    interface OnPlanBack {
        fun onProjectDetails(id: Int?)
    }

    private var onPlanBack: OnPlanBack? = null

    fun setOnPlanBack(onPlanBack: OnPlanBack?) {
        this.onPlanBack = onPlanBack
    }

}
