package com.miaofen.xiaoying.activity.materia

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.gender.GenderActivity
import com.miaofen.xiaoying.activity.location.PositionActivity
import com.miaofen.xiaoying.activity.repair.ChangeNicknameActivity
import com.miaofen.xiaoying.activity.signature.SignatureActivity
import com.miaofen.xiaoying.base.mvp.BaseMvpActivity
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.common.data.bean.response.MaterialsResponse
import com.miaofen.xiaoying.view.LoadingView
import kotlinx.android.synthetic.main.activity_materials.*
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.toobar_layout.*
import okhttp3.internal.addHeaderLenient
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 * 编辑资料
 */
class MaterialsActivity : BaseMvpActivity<MaterialsContract.Presenter>(),
    MaterialsContract.View {


    private val REQUEST_CODE_SCAN_GALLERY = 100

    private var pvTime : TimePickerView?= null

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

        //点击头像
        mater_avatarUrl.setOnClickListener {
            startQrCode()
        }
        //点击地区
        mater_province.setOnClickListener {
            PositionActivity.startActivityForResult(this)
        }

        //点击生日
        mater_birthdayTime.setOnClickListener {
            initTimePicker()
            pvTime?.show()
        }
    }

    override fun onMaterialsSuccess(data: MaterialsResponse?) {
        //标准圆形图片。
//        Glide.with(this).load(data?.avatarUrl)
//            .apply(RequestOptions.bitmapTransform(CircleCrop()))
//            .fallback(R.drawable.zhanweitu)
//            .error(R.drawable.error_zhanweitu)
//            .into(mater_avatarUrl)
        displayImageView(this,mater_avatarUrl,data?.avatarUrl,R.drawable.zhanweitu)
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

    private fun startQrCode() {
        // 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 申请权限
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                Constant.REQ_PERM_EXTERNAL_STORAGE
            )
            return
        }
        //打开手机中的相册
        val innerIntent = Intent(Intent.ACTION_GET_CONTENT) //"android.intent.action.GET_CONTENT"
        innerIntent.type = "image/*"
        startActivityForResult(innerIntent, REQUEST_CODE_SCAN_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //相册
        if (resultCode === Activity.RESULT_OK) {
            var imageUri: Uri = data!!.data
            //处理uri,7.0以后的fileProvider 把URI 以content provider 方式 对外提供的解析方法
            val file: File = getFileFromUri(imageUri, this)!!
            if (file.exists()) {
                loadingDialog.showLoading()
                mPresenter?.onFileUpload(file, "USER_AVATAR")
            }
        } else if (requestCode == 0 && resultCode == 1) {
            val bundle: Bundle? = data?.extras
            var region: String? = null
            var province: String? = null
            if (bundle != null) region = bundle.getString("region")
            if (bundle != null) province = bundle.getString("province")
            loadingDialog.showLoading()
            mPresenter?.onRevisionArea(province!!,region!!)
        }

    }

    private fun getFileFromUri(uri: Uri, context: Context): File? {
        return if (uri == null) {
            null
        } else when (uri.scheme) {
            "content" -> getFileFromContentUri(uri, context)
            "file" -> File(uri.path)
            else -> null
        }
    }

    /**
     * 通过内容解析中查询uri中的文件路径
     */
    private fun getFileFromContentUri(contentUri: Uri?, context: Context): File? {
        if (contentUri == null) {
            return null
        }
        var file: File? = null
        val filePath: String
        val filePathColumn =
            arrayOf(MediaStore.MediaColumns.DATA)
        val contentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(
            contentUri, filePathColumn, null,
            null, null
        )
        if (cursor != null) {
            cursor.moveToFirst()
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]))
            cursor.close()
            if (!TextUtils.isEmpty(filePath)) {
                file = File(filePath)
            }
        }
        return file
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {

            Constant.REQ_PERM_EXTERNAL_STORAGE ->                 // 文件读写权限申请
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode()
                } else {
                    // 被禁止授权
                    Toast.makeText(this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_LONG).show()
                }
        }
    }

    //初始化时间选择器
    private  fun initTimePicker() {
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        startDate[1900, 1] = 1 //起始时间
        val endDate = Calendar.getInstance()
        endDate[2099, 12] = 31 //结束时间
        pvTime = TimePickerBuilder(this,
            OnTimeSelectListener { date, v -> //选中事件回调
                val time = date.time
                loadingDialog.showLoading()
                mPresenter?.onModifyBirthday(getTimes(date)!!)
            }) //年月日时分秒 的显示与否，不设置则默认全部显示
            .setType(booleanArrayOf(true, true, true, false, false, false))
            .setLabel("年", "月", "日", "", "", "")
            .isCenterLabel(true)
            .setDividerColor(Color.DKGRAY)
            .setDate(selectedDate)
            .setRangDate(startDate, endDate)
            .setDecorView(null)
            .build()
    }

    //格式化时间
    private fun getTimes(date: Date): String? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }

    //上传头像成功
    override fun onFileUploadSuccess(data: String?) {
        mPresenter?.onChangeYourAvatar(data!!)
        loadingDialog.dismiss()
    }

    //上传头像失败
    override fun onFileUploadError() {
        loadingDialog.dismiss()
    }

    //修改头像成功
    override fun onChangeYourAvatarSuccess(data: String?) {
        mPresenter?.doMaterials()
        loadingDialog.dismiss()
    }

    //修改头像失败
    override fun onChangeYourAvatarError() {
        loadingDialog.dismiss()
    }
    //修改地址成功
    override fun onRevisionAreaSuccess(data: Boolean?) {
        mPresenter?.doMaterials()
        loadingDialog.dismiss()
    }
    //修改地址失败
    override fun onRevisionAreaError() {
        loadingDialog.dismiss()
    }
    //修改生日成功
    override fun onModifyBirthdaySuccess(data: Boolean?) {
        mPresenter?.doMaterials()
        loadingDialog.dismiss()
    }
    //修改生日失败
    override fun onModifyBirthdayError() {
        loadingDialog.dismiss()
    }

    fun displayImageView(
        context: Context?, imageView: ImageView, url: String?, defaultResourceId: Int
    ) {
        if (context is Activity && !context.isFinishing) { //判断条件，防止Activity已经finish了，ImageView仍然还在加载图片
            Glide.with(context)
                .load(buildGlideUrl(url))
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .fallback(R.drawable.zhanweitu)
                .error(R.drawable.error_zhanweitu)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }

    private fun buildGlideUrl(url: String?): GlideUrl? {
        return if (TextUtils.isEmpty(url)) {
            null
        } else {
            GlideUrl(url,
                LazyHeaders.Builder()
                    .addHeader("Referer", "https://www.motortravel.cn/android.html").build()
            )
        }
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, MaterialsActivity::class.java)
            context?.startActivity(intent)
        }
    }


}