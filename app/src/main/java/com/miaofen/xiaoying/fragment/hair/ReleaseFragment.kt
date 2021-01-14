package com.miaofen.xiaoying.fragment.hair


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
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.activity.ReleaseDetailsActivity
import com.miaofen.xiaoying.base.BaseFragment
import com.miaofen.xiaoying.comm.Constant
import com.miaofen.xiaoying.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_release.*
import kotlinx.android.synthetic.main.toobar_layout.*
import java.io.File
import java.io.FileInputStream


/**
 * 发布
 */
class ReleaseFragment : BaseFragment() {

    private val REQUEST_CODE_SCAN_GALLERY = 100

    private var bitmapList = ArrayList<Bitmap>()

    private var mAdapter: ReleaseRecyclerViewAdapter? = null

    override fun getLayoutResources() = R.layout.fragment_release

    override fun initView() {
        super.initView()
        title_bar_title.text = "发布"
//        val upload = BitmapFactory.decodeResource(this.context!!.resources, R.drawable.upload_icon)
//        bitmapList.add(0, upload)
        release_recyclerview.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        mAdapter = ReleaseRecyclerViewAdapter(R.layout.release_imager_layout, bitmapList, activity)
        val inflate = layoutInflater.inflate(R.layout.upload_icon_layout, null)
        mAdapter?.addFooterView(inflate)
        release_recyclerview.adapter = mAdapter
        inflate.findViewById<ImageView>(R.id.image_upload).setOnClickListener { startQrCode() }
    }

    override fun initData() {
        super.initData()
        tv_details.setOnClickListener { ReleaseDetailsActivity.start(activity) }//详情
        tv_trip.setOnClickListener {  }//行程
        tv_label.setOnClickListener {  }//标签
        tv_privacy.setOnClickListener {  }//隐私
        tv_release.setOnClickListener {  }//发布

    }

    private fun startQrCode() {
        // 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
        if (ActivityCompat.checkSelfPermission(
                activity!!, Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 申请权限
            ActivityCompat.requestPermissions(
                activity!!, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                Constant.REQ_PERM_EXTERNAL_STORAGE
            )
            return
        }
        //打开手机中的相册
        val innerIntent =
            Intent(Intent.ACTION_PICK) //"android.intent.action.GET_CONTENT"
        innerIntent.type = "image/*"
        startActivityForResult(innerIntent, REQUEST_CODE_SCAN_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //相册
        if (resultCode === Activity.RESULT_OK) {
            var imageUri: Uri = data!!.data
            //处理uri,7.0以后的fileProvider 把URI 以content provider 方式 对外提供的解析方法
            val file: File = getFileFromUri(imageUri, activity!!)!!
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
        var filePath: String
        val filePathColumn =
            arrayOf(MediaStore.MediaColumns.DATA)
        val contentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(
            contentUri, filePathColumn, null,
            null, null
        )
        if (cursor != null) {
            cursor.moveToFirst()
           for (item in filePathColumn){
               Log.e("TAG","图片路径 -》 ：" + item)
               filePath = cursor.getString(cursor.getColumnIndex(item))
               cursor.close()
               if (!TextUtils.isEmpty(filePath)) {
                   file = File(filePath)
               }
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
                    Toast.makeText(activity, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_LONG).show()
                }
        }
    }

}