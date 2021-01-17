package com.miaofen.xiaoying.activity.gender

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
import kotlinx.android.synthetic.main.activity_gender.*
import kotlinx.android.synthetic.main.activity_materials.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 修改性别
 */
class GenderActivity : BaseMvpActivity<GenderContract.Presenter>(), GenderContract.View {


    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }


    override fun returnLayoutId() = R.layout.activity_gender

    override fun initView() {
        super.initView()
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.gender)
        GenderPresenter(this)
        when (intent.getIntExtra(GENDER,-1)) {
            0 -> {//保密
                image_select_secrecy.setImageResource(R.drawable.tick_icon)
            }
            1 -> {//男
                image_select_male.setImageResource(R.drawable.tick_icon)
            }
            2 -> {//女
                image_select_female.setImageResource(R.drawable.tick_icon)
            }
        }


    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }

        linear_secrecy.setOnClickListener {
            loadingDialog.showSuccess()
            image_select_secrecy.setImageResource(R.drawable.tick_icon)
            image_select_secrecy.visibility = View.VISIBLE
            image_select_male.visibility = View.INVISIBLE
            image_select_female.visibility = View.INVISIBLE
            mPresenter?.doGender(0)
        }//保密
        linear_male.setOnClickListener {
            loadingDialog.showSuccess()
            image_select_male.setImageResource(R.drawable.tick_icon)
            image_select_male.visibility = View.VISIBLE
            image_select_female.visibility = View.INVISIBLE
            image_select_secrecy.visibility = View.INVISIBLE
            mPresenter?.doGender(1)
        }//男
        linear_female.setOnClickListener {
            loadingDialog.showSuccess()
            image_select_female.setImageResource(R.drawable.tick_icon)
            image_select_female.visibility = View.VISIBLE
            image_select_male.visibility = View.INVISIBLE
            image_select_secrecy.visibility = View.INVISIBLE
            mPresenter?.doGender(2)
        }//女

    }

    companion object {
        private const val GENDER = "gender"
        fun start(context: Context?, gender: Int?) {
            val intent = Intent(context, GenderActivity::class.java)
            intent.putExtra(GENDER,gender)
            context?.startActivity(intent)
        }
    }

    override fun onGenderSuccess(data: String?) {
        loadingDialog.dismiss()
    }

    override fun onGenderError() {
        loadingDialog.dismiss()
    }
}