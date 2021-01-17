package com.miaofen.xiaoying.activity.materia

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.gender.GenderActivity
import com.miaofen.xiaoying.activity.repair.ChangeNicknameActivity
import com.miaofen.xiaoying.activity.signature.SignatureActivity
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.common.data.bean.response.MaterialsResponse
import com.miaofen.xiaoying.utils.ToastUtils
import com.miaofen.xiaoying.utils.getCurrentTime
import com.miaofen.xiaoying.view.LoadingView
import kotlinx.android.synthetic.main.activity_materials.*
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.toobar_layout.*

/**
 * 编辑资料
 */
class MaterialsActivity : BaseMvpActivity<MaterialsContract.Presenter>(), MaterialsContract.View {

    private val loadingDialog: LoadingView by lazy {
        LoadingView(this).apply {
            setTipMsg("正在加载")
        }
    }

    private var gender: Int = -1

    override fun returnLayoutId() = R.layout.activity_materials


    override fun initView() {
        super.initView()
        loadingDialog.showSuccess()
        MaterialsPresenter(this)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.setText(R.string.editing_materials)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.doMaterials()
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
        mater_nickName.setOnClickListener {
            ChangeNicknameActivity.start(this)
        }//昵称
        mater_gender.setOnClickListener {
            GenderActivity.start(this, gender)
        }//性别
        tv_personalSignature.setOnClickListener {
            SignatureActivity.start(this, tv_personalSignature.text.toString())
        }//修改个性签名
    }

    override fun onMaterialsSuccess(data: MaterialsResponse?) {
        //标准圆形图片。
        Glide.with(this).load(data?.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(mater_avatarUrl)
        mater_nickName.text = data?.nickName
        mater_birthdayTime.text = data?.birthdayTime
        if (data?.gender != null) {
            this.gender = data.gender!!
            when (data.gender) {
                0 -> {//保密
                    mater_gender.setText(R.string.secrecy)
                }
                1 -> {//男
                    mater_gender.setText(R.string.male)
                }
                2 -> {//女
                    mater_gender.setText(R.string.female)
                }
            }
        }
        mater_province.text = data?.province
        tv_personalSignature.text = data?.personalSignature
        loadingDialog.dismiss()
    }

    override fun onMaterialsError() {
        loadingDialog.dismiss()
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, MaterialsActivity::class.java)
            context?.startActivity(intent)
        }
    }


}