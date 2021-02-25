package com.miaofen.xiaoying.common.data.remote

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.miaofen.xiaoying.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

/**
 * 项目名称：com.miaofen.xiaoying.common.data.remote
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class MRetrofit private constructor(context: Context) {

    private val retrofit: Retrofit

    init {
        //获取cookie
//        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
        //okhttp创建了
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())//添加网络拦截器
//            .addInterceptor(CacheInterceptor(context))//添加拦截器
            .addInterceptor(ParamsInterceptor())//拦截器
//            .addNetworkInterceptor(
//                CacheInterceptor(
//                    context
//                )
//            )//添加网络拦截器
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//连接超时
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//读写超时
//            .cookieJar(cookieJar)
            .build()

        //retrofit创建
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())//添加 GSON解析器
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加 RXjava
            .baseUrl(BuildConfig.HTML_HOST)//请求网址
            .build()
    }

    private fun getLoggingInterceptor() =
        if (BuildConfig.LOG_DEBUG) {
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }


    companion object {
        private const val DEFAULT_TIMEOUT: Long = 20 //连接超时
        var instance: MRetrofit? = null
        fun getInstance(context: Context): MRetrofit {
            if (instance == null) {
                synchronized(MRetrofit::class) {
                    if (instance == null) {
                        instance =
                            MRetrofit(context)
                    }
                }
            }
            return instance!!
        }
    }


    fun <T> create(service: Class<T>?): T {
        if (service == null) {
            throw RuntimeException("Api服务为空 Api service is null")
        }
        return retrofit.create(service)
    }


}