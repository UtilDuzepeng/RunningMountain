package com.miaofen.xiaoying.activity.details

import android.content.Context
import androidx.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.details
 * 类描述：途径地适配器
 * 创建人：duzepeng
 * 创建时间：2020/12/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class PlanTripsRecyclerAdapter(
    layoutResId: Int, @Nullable data: List<DetailsResponse.PlanTripsBean>?, context: Context?
) : BaseQuickAdapter<DetailsResponse.PlanTripsBean?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    var dataList: List<DetailsResponse.PlanTripsBean>? = data;
    var totalSize: Int = -1
    var recordSize: Int = -1
    override fun convert(helper: BaseViewHolder, item: DetailsResponse.PlanTripsBean?) {
        ++recordSize
        if (dataList != null) {
            totalSize = dataList?.size!! - 1
        }
        if (totalSize == recordSize) {
            helper.setVisible(R.id.image_planTripsRemark, false)
        }
        helper.setText(R.id.tv_planTripsName, item?.name)
        helper.setText(R.id.tv_planTripsRemark, item?.remark)
    }

}