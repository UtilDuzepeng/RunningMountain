package com.miaofen.xiaoying.activity.details.tube

import android.content.Context
import android.os.Build
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.tube
 * 类描述：管理适配器
 * 创建人：duzepeng
 * 创建时间：2021/1/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class AdministrationRecyclerAdapter(
    layoutResId: Int,
    @Nullable data: List<DetailsResponse.ButtonInfoBean.SubButtonInfoBean>?, var context: Context?,
    var planId: Int?
) : BaseQuickAdapter<DetailsResponse.ButtonInfoBean.SubButtonInfoBean?, BaseViewHolder>(
    layoutResId, data
) {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun convert(
        helper: BaseViewHolder, item: DetailsResponse.ButtonInfoBean.SubButtonInfoBean?
    ) {
        helper.setText(R.id.tv_subButtonInfo, item?.buttonName)

        helper.setOnClickListener(R.id.linear_subButtonInfo) {
            when (item!!.buttonAction!!) {
                3 -> {
                    administrationRecyclerInput?.onClickAdministrationTeam()
                }//小队
                4 -> {
                    administrationRecyclerInput?.onClickAdministrationSignUp()
                }//报名列表
                5 -> {
                    administrationRecyclerInput?.onClickAdministrationEdit()
                }//编辑
                6 -> {
                    if (planId != null && planId != -1) {
                        administrationRecyclerInput?.onClickAdministrationDissolution(planId!!)
                    }
                }//解散
            }
        }
        when (item!!.buttonAction!!) {
            3 -> {
                helper.setImageDrawable(
                    R.id.image_subButtonInfo, context?.getDrawable(R.drawable.xiaodui_icon)
                )
            }//小队
            4 -> {
                helper.setImageDrawable(
                    R.id.image_subButtonInfo, context?.getDrawable(R.drawable.baoming_icon)
                )
            }//报名列表
            5 -> {
                helper.setImageDrawable(
                    R.id.image_subButtonInfo, context?.getDrawable(R.drawable.bianji_edit_icon)
                )
            }//编辑
            6 -> {
                helper.setImageDrawable(
                    R.id.image_subButtonInfo, context?.getDrawable(R.drawable.jiesan_icon)
                )
            }//解散
        }
    }


    interface AdministrationRecyclerInput {
        fun onClickAdministrationTeam()
        fun onClickAdministrationSignUp()
        fun onClickAdministrationEdit()
        fun onClickAdministrationDissolution(planId: Int)
    }

    private var administrationRecyclerInput: AdministrationRecyclerInput? = null

    fun setAdministrationInput(administrationRecyclerInput: AdministrationRecyclerInput?) {
        this.administrationRecyclerInput = administrationRecyclerInput
    }

}