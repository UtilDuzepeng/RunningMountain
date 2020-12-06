package com.miaofen.xiaoying.fragment.home.shareadapter

import android.content.Context
import androidx.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.ProjectDetailsActivity
import com.miaofen.xiaoying.view.CurrencyLayout

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
    layoutResId: Int, @Nullable data: List<String>?, context: Context?
) : BaseQuickAdapter<String?, BaseViewHolder>(layoutResId, data) {

    var context: Context? = context
    override fun convert(helper: BaseViewHolder, item: String?) {

        val currencyLayout = helper.getView<CurrencyLayout>(R.id.item_CurrencyLayout)

        currencyLayout.itemOnClick.setOnClickListener { ProjectDetailsActivity.start(context) }


//        helper.setOnClickListener(R.id.item_CurrencyLayout) {
//            ProjectDetailsActivity.start(context)
//        }
    }

}
