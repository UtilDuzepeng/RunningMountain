package com.miaofen.xiaoying

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.miaofen.xiaoying.activity.signup.ImgZoomActivity
import com.miaofen.xiaoying.base.BaseActivity
import com.miaofen.xiaoying.fragment.hair.ReleaseFragment
import com.miaofen.xiaoying.fragment.home.HomeFragment
import com.miaofen.xiaoying.fragment.news.NewsFragment
import com.miaofen.xiaoying.fragment.user.UserFragment
import com.miaofen.xiaoying.utils.*
import com.miaofen.xiaoying.utils.SoftKeyBoardListener.OnSoftKeyBoardChangeListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseActivity() {

    /**
     * 需要进行检测的权限数组
     */
    protected var needPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE
    )

    private val PERMISSON_REQUESTCODE = 0

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private var isNeedCheck = true

    private var locationUtils: LocationUtils? = null

    private var mExitTime: Long = 0 //延时事件

    var toast: Toast? = null //提示

    private var curTabIndex = -1

    private val rbIds =
        intArrayOf(R.id.rb_home, R.id.rb_release, R.id.rb_news, R.id.rb_mine)

    override fun returnLayoutId() = R.layout.activity_main

    override fun initFragment(savedInstanceState: Bundle?) {
        val index: Int = savedInstanceState?.getInt(STATE_CURRENT_TAB_INDEX) ?: DEFAULT_INDEX
        setSoftKeyBoardListener()
        showTab(index)
        rg_root.check(rbIds[index])
        rg_root.setOnCheckedChangeListener { _, checkedId ->
            val pos = rbIds.indexOf(checkedId)
            showTab(pos)
        }
    }

    override fun initView() {
        super.initView()
        if (locationUtils == null) {
            locationUtils = LocationUtils(this)
        }
        locationCallBac()
    }

    override fun onResume() {
        super.onResume()
        if (isNeedCheck) {
            checkPermissions(*needPermissions)
        }
    }


    private fun showTab(index: Int) {
        if (index != curTabIndex) {
            changeFragment(index, curTabIndex)
            curTabIndex = index
        }
    }

    private fun changeFragment(newTabIndex: Int, oldTabIndex: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        var currentFragment: Fragment? = null

        if (oldTabIndex >= 0) {
            currentFragment = supportFragmentManager.findFragmentByTag(genFragmentTag(oldTabIndex))
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        var targetFragment =
            supportFragmentManager.findFragmentByTag(genFragmentTag(newTabIndex))

        if (targetFragment == null) {
            targetFragment = createFragment(newTabIndex)
            transaction.add(R.id.fl_content, targetFragment, genFragmentTag(newTabIndex))
        } else {
            transaction.show(targetFragment)
        }
        transaction.commit()
    }

    private fun genFragmentTag(index: Int) = FRAGMENT_TAG_PREFIX + index

    private fun createFragment(index: Int) = when (index) {
        0 -> HomeFragment()
        1 -> ReleaseFragment()
        2 -> NewsFragment()
        3 -> UserFragment()
        else -> HomeFragment()
    }


    override fun onPause() {
        super.onPause()
        toast?.let { toast!!.cancel() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime <= 3000) {
                finish()
                toast!!.cancel()
            } else {
                mExitTime = System.currentTimeMillis()
                toast = showToast("在按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    companion object {
        const val STATE_CURRENT_TAB_INDEX = "StateCurrentTabIndex"
        private const val FRAGMENT_TAG_PREFIX = "MainActivityFragment_"
        private const val DEFAULT_INDEX = 0

        fun startogin(context: Context?) {
            val intent = Intent(context, MainActivity::class.java)
            context?.startActivity(intent)
        }
    }

    /**
     * 添加软键盘监听
     */
    private fun setSoftKeyBoardListener() {
        var softKeyBoardListener = SoftKeyBoardListener(this)
        //软键盘状态监听
        softKeyBoardListener.setListener(object : OnSoftKeyBoardChangeListener {
            override fun keyBoardShow(height: Int) {
                //软键盘已经显示，做逻辑
                rg_root.visibility = View.GONE
            }
            override fun keyBoardHide(height: Int) {
                //软键盘已经隐藏,做逻辑
                rg_root.visibility = View.VISIBLE
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        Log.e("TAG", "我执行了$requestCode")
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(grantResults)) {      //没有授权
                showMissingPermissionDialog() //显示提示信息
                isNeedCheck = false
            } else {
                locationCallBac()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun locationCallBac() {
        locationUtils!!.setmLocationCallBack { amapLocation ->
            if (amapLocation != null) {
                if (amapLocation.errorCode == 0) {
                    val toJson = amapLocation.toJson(1)
                    CacheUtils.writeJson(this, toJson.toString(), "address", false)
                    Log.e("TAG","我执行拉-《 我是定位 : " +  amapLocation)
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    ToastUtils.showToast("定位失败${amapLocation.errorInfo}")
                }
            }
        }
    }


    /**
     * 检查权限
     *
     * @param
     * @since 2.5.0
     */
    private fun checkPermissions(vararg permissions: String) {
        //获取权限列表
        val needRequestPermissonList =
            findDeniedPermissions(permissions as Array<String>)
        if (null != needRequestPermissonList
            && needRequestPermissonList.size > 0
        ) {
            //list.toarray将集合转化为数组
            ActivityCompat.requestPermissions(
                this, needRequestPermissonList.toTypedArray(), PERMISSON_REQUESTCODE
            )
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private fun findDeniedPermissions(permissions: Array<String>): List<String>? {
        val needRequestPermissonList: MutableList<String> =
            ArrayList()
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (perm in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    perm
                ) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm
                )
            ) {
                needRequestPermissonList.add(perm)
            }
        }
        return needRequestPermissonList
    }


    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private fun verifyPermissions(grantResults: IntArray): Boolean {
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private fun showMissingPermissionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.notifyTitle)
        builder.setMessage(R.string.notifyMsg)

        // 拒绝, 退出应用
        builder.setNegativeButton(
            R.string.cancel
        ) { dialog, which -> finish() }
        builder.setPositiveButton(
            R.string.setting
        ) { dialog, which -> startAppSettings() }
        builder.setCancelable(false)
        builder.show()
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private fun startAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        )
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        locationUtils?.destory();//销毁定位客户端，同时销毁本地定位服务。
    }
}