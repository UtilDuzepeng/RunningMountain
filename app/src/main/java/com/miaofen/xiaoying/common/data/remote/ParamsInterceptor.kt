package com.miaofen.xiaoying.common.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 项目名称：com.miaofen.xiaoying.common.data.remote
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class ParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        val builder = request.newBuilder()
            .addHeader("client", "Android")
//        val token = Global.getToken()//?.token
//        Logger.e("okhttp token: $token")
//        if (!TextUtils.isEmpty(token)) {
//            builder.addHeader("token", token!!)
//            .addHeader("token", "odGc95I9iiVX78eK6nShycWM3sfw")
            .addHeader("token", "odGc95L-n2d3uTbUqxJ282ldkCiE")
//            .addHeader("token", "odGc95L-n2d3uTbUqxJ282ldkCiE")
            .addHeader("type", "token")
//        }
        request = builder.build()
        return chain.proceed(request)

    }
}
