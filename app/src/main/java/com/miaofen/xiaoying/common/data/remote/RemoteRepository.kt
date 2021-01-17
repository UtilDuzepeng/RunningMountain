package com.miaofen.xiaoying.common.data.remote

import com.miaofen.xiaoying.MyApplication
import com.miaofen.xiaoying.common.data.remote.api.HomeApi
import com.miaofen.xiaoying.common.data.remote.api.LoginApi
import com.miaofen.xiaoying.common.data.remote.api.UserApi

/**
 * 项目名称：com.miaofen.xiaoying.common.data.remote
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

object RemoteRepository :
    LoginApi by MRetrofit.getInstance(MyApplication.mContext).create(LoginApi::class.java),
    HomeApi by MRetrofit.getInstance(MyApplication.mContext).create(HomeApi::class.java),
    UserApi by MRetrofit.getInstance(MyApplication.mContext).create(UserApi::class.java)