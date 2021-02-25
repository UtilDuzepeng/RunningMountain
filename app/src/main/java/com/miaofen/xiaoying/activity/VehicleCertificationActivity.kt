package com.miaofen.xiaoying.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.fragment.hair.ReleaseRecyclerViewAdapter
import com.miaofen.xiaoying.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_vehicle_certification.*
import kotlinx.android.synthetic.main.toobar_layout.*
import java.io.File
import java.io.FileInputStream

/**
 * 车辆认证
 */
class VehicleCertificationActivity : BaseActivity() {

    private val REQUEST_CODE_SCAN_GALLERY = 100

    private var bitmapList = ArrayList<Bitmap>()

    private var mAdapter: ReleaseRecyclerViewAdapter? = null

    override fun returnLayoutId() = R.layout.activity_vehicle_certification

    override fun initView() {
        super.initView()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        title_bar_back.visibility = View.VISIBLE
        title_bar_title.text = "车辆认证"
        vehicle_recyclerview.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        mAdapter = ReleaseRecyclerViewAdapter(R.layout.release_imager_layout, bitmapList, this)
        val inflate = layoutInflater.inflate(R.layout.upload_icon_layout, null)
        mAdapter?.addFooterView(inflate)
        vehicle_recyclerview.adapter = mAdapter
        inflate.findViewById<ImageView>(R.id.image_upload).setOnClickListener { startQrCode() }
    }

    override fun initData() {
        super.initData()
        title_bar_back.setOnClickListener { finish() }
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
                Log.e("TAG", "文件路径：" + file)
                val fis = FileInputStream(file)
                val bitmap = BitmapFactory.decodeStream(fis)
                if (bitmapList.size >= 9) {
                    ToastUtils.showToast("最多只能上传9张～")
                } else {
                    bitmapList.add(bitmap)
                    mAdapter?.notifyDataSetChanged()
                }
            }
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


    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, VehicleCertificationActivity::class.java)
            context?.startActivity(intent)
        }
    }


}