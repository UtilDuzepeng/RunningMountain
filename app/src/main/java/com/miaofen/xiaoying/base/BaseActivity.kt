package com.miaofen.xiaoying.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.miaofen.xiaoying.R
import com.miaofen.xiaoying.utils.ActivityManager

/**
 * 项目名称：com.miaofen.xiaoying.base
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(returnLayoutId())
        ActivityManager.getInstance().addActivity(this)
        hideStatusBar()//隐藏状态栏
        initView()
        initData()
        onClick()
        initFragment(savedInstanceState)
    }

    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            val decorView = window.decorView
            decorView.setOnApplyWindowInsetsListener { v, insets ->
                val defaultInsets = v.onApplyWindowInsets(insets)
                defaultInsets.replaceSystemWindowInsets(
                    defaultInsets.systemWindowInsetLeft, 0,
                    defaultInsets.systemWindowInsetRight, defaultInsets.systemWindowInsetBottom
                )
            }
            ViewCompat.requestApplyInsets(decorView)
            //将状态栏设成透明，如不想透明可设置其他颜色
//            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
//           if (boolean){
//               window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)
//           }else{
               window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
//           }
            getWindow().decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //实现状态栏图标和文字颜色为暗色
        }
    }

//    //重写字体缩放比例 api<25
//    override fun getResources(): Resources {
//        val res = super.getResources()
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
//            val config = res.configuration
//            config.fontScale = SharedPrefHelper.getFontSize()//设置正常字体大小的倍数
//            res.updateConfiguration(config, res.displayMetrics)
//        }
//        return res
//    }
//
//    //重写字体缩放比例  api>25
//    override fun attachBaseContext(newBase: Context?) {
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
//            val res = newBase?.getResources()
//            val config = res?.configuration
//            config?.fontScale = SharedPrefHelper.getFontSize()//设置正常字体大小的倍数
//            val newContext = newBase?.createConfigurationContext(config!!)
//            super.attachBaseContext(newContext)
//        } else {
//            super.attachBaseContext(newBase)
//        }
//    }


    override fun onResume() {
        refreshData()
        super.onResume()
    }

    override fun onDestroy() {
        destruction()
        ActivityManager.getInstance().removeActivity(this)
        super.onDestroy()
    }

    open fun initFragment(savedInstanceState: Bundle?) {}//首次加载Fragment

    open fun onClickFragment(
        radioButton: RadioButton,
        displayFragment: Fragment,
        vararg hideFragment: Fragment
    ) {
    } //显示隐藏

    abstract fun returnLayoutId(): Int //返回布局

    open fun refreshData() {}//刷新数据

    open fun destruction() {}//销毁

    open fun initView() {}//实例化view

    open fun initData() {}//赋值

    open fun onClick(){}//点击事件


}