package com.miaofen.xiaoying.activity.details.tube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseBottomDialogFragment
import com.miaofen.xiaoying.common.data.bean.response.DetailsResponse
import com.miaofen.xiaoying.fragment.hair.ReleaseRecyclerViewAdapter
import com.miaofen.xiaoying.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_release.*

/**
 * 项目名称：com.miaofen.xiaoying.activity.details.tube
 * 类描述：管理弹窗
 * 创建人：duzepeng
 * 创建时间：2021/1/9
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class AdministrationDialog(
    var subButtonInfo: List<DetailsResponse.ButtonInfoBean.SubButtonInfoBean>?,
    var planId: Int?
) : BaseBottomDialogFragment(), AdministrationRecyclerAdapter.AdministrationRecyclerInput {

    private var frView: View? = null

    override fun onCreateView(//adminis_recycler_item
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // 去掉默认的标题
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)//adminis_layout
        frView = inflater.inflate(R.layout.adminis_layout, null)

        val recycler = frView?.findViewById<RecyclerView>(R.id.administration_Recycler)
        recycler?.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        var mAdapter = AdministrationRecyclerAdapter(
            R.layout.adminis_recycler_item, subButtonInfo, activity, planId
        )
        mAdapter.setAdministrationInput(this)
        recycler?.adapter = mAdapter
        frView?.findViewById<ImageView>(R.id.image_administration)?.setOnClickListener {
            administrationInput?.closeAdministrationDialog()
        }
        return frView
    }

    interface AdministrationInput {
        fun closeAdministrationDialog()
        fun administrationTeam()
        fun administrationSignUp()
        fun administrationEdit()
        fun administrationDissolution(planId: Int)
    }

    private var administrationInput: AdministrationInput? = null

    fun setAdministrationInput(administrationInput: AdministrationInput?) {
        this.administrationInput = administrationInput
    }

    override fun onClickAdministrationTeam() {
        administrationInput?.administrationTeam()
    }

    override fun onClickAdministrationSignUp() {
        administrationInput?.administrationSignUp()
    }

    override fun onClickAdministrationEdit() {
        administrationInput?.administrationEdit()
    }

    override fun onClickAdministrationDissolution(planId: Int) {
        administrationInput?.administrationDissolution(planId)
    }


}