package com.miaofen.xiaoying.activity.signature

import android.content.Context
import android.content.Intent
import android.view.View
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.view.LoadingView
import kotlinx.android.synthetic.main.activity_signature.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 修改个性签名
 */
class SignatureActivity : BaseMvpActivity<SignatureContract.Presenter>(), SignatureContract.View {



    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }


    override fun returnLayoutId() = R.layout.activity_signature

    override fun initView() {
        super.initView()
        SignaturePresenter(this)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.modify_signature)
        tit_bar_right.setText(R.string.preservation)
        tit_bar_right.visibility = View.VISIBLE
        ed_personalSignature.setText(intent.getStringExtra(PERSONALSIGNATURE))
        ed_personalSignature.setSelection(ed_personalSignature.length())
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
        tit_bar_right.setOnClickListener {
            loadingDialog.showSuccess()
            mPresenter?.doSignature(ed_personalSignature.text.toString())
            finish()
        }//保存
    }

    companion object {
        const val PERSONALSIGNATURE = "personalSignature"
        fun start(context: Context?, personalSignature: String?) {
            val intent = Intent(context, SignatureActivity::class.java)
            intent.putExtra(PERSONALSIGNATURE, personalSignature)
            context?.startActivity(intent)
        }
    }

    override fun onSignatureSuccess(data: String?) {
        loadingDialog.dismiss()
    }

    override fun onSignaturesError() {
        loadingDialog.dismiss()
    }

}