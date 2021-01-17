package com.miaofen.xiaoying.activity.repair

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.materia.MaterialsActivity
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.view.LoadingView
import kotlinx.android.synthetic.main.activity_change_nickname.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 修改昵称
 */
class ChangeNicknameActivity : BaseMvpActivity<ChangeNicknameContract.Presenter>(),
    ChangeNicknameContract.View {


    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    override fun returnLayoutId() = R.layout.activity_change_nickname

    override fun initView() {
        super.initView()
        ChangeNicknamePresenter(this)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.change_nickname)
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
        button_preservation.setOnClickListener {
            loadingDialog.showSuccess()
            mPresenter?.doMaterials(enter_a_nickname.text.toString())
        }
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, ChangeNicknameActivity::class.java)
            context?.startActivity(intent)
        }
    }

    override fun onMaterialsSuccess(data: String?) {
        loadingDialog.dismiss()
        finish()
    }

    override fun onMaterialsError() {
        loadingDialog.dismiss()
    }
}