package com.miaofen.xiaoying.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

fun Context.showToast(message: Int): Toast {
    var toast: Toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
    return toast
}

fun Context.showToast(message: String): Toast {
    var toast: Toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
    return toast
}

fun EditText.showTextAndMoveSelection(text: String) {
    setText(text)
    setSelection(text.length)
}

fun EditText.showIfNotEmpty(text: String?) {
    if (!TextUtils.isEmpty(text)) {
        showTextAndMoveSelection(text!!)
    }
}

//校验手机号
fun isTelPhoneNumber(phone: String?): Boolean {
    if (phone != null && phone.length == 11) {
        val pattern = Pattern.compile("^1[0-9][0-9]\\d{8}$")
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }
    return false
}

fun getCurrentTime(value: Long): String? {
    val format = SimpleDateFormat("MM-dd HH:mm")
    val time = format.format(Date(value * 1000L))
//        Log.d("xxx-------->", "转换后时间: $time")
    return time
}


inline fun <reified T : Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

// Ret 订阅
fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

//关闭app 重启启动
fun restartApplication(context: Context) {
    val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
    intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    context.startActivity(intent)
    //杀掉以前进程
    android.os.Process.killProcess(android.os.Process.myPid())
}

/**
 * 检测是否安装支付宝
 * @param context
 * @return
 */
fun isAliPayInstalled(context: Context): Boolean {
    val uri = Uri.parse("alipays://platformapi/startApp")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    val componentName = intent.resolveActivity(context.packageManager)
    return componentName != null
}

/**
 * 检测是否安装微信
 * @param context
 * @return
 */
fun isWeixinAvilible(context: Context): Boolean {
    val packageManager = context.packageManager// 获取packagemanager
    val pinfo = packageManager.getInstalledPackages(0)// 获取所有已安装程序的包信息
    if (pinfo != null) {
        for (i in pinfo.indices) {
            val pn = pinfo[i].packageName
            if (pn == "com.tencent.mm") {
                return true
            }
        }
    }
    return false
}

/**
 * 显示键盘
 *
 * @param et 输入焦点
 */
fun showInput(et: EditText, context: Context) {
    et.requestFocus()
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * 隐藏键盘
 */
fun hideInput(context: Context, activity: Activity?) {
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val v: View = activity?.window!!.peekDecorView()
    if (null != v) {
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
    }
}

