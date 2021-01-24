package com.miaofen.xiaoying.common.data.remote.api

import com.miaofen.xiaoying.common.data.bean.CommonResponse
import com.miaofen.xiaoying.common.data.bean.request.*
import com.miaofen.xiaoying.common.data.bean.response.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 项目名称：com.miaofen.xiaoying.common.data.remote.api
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2021/1/16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface UserApi {

    /**
     * 个人资料
     */
    @POST("weixin/user/center")
    fun onPersonalCenter(@Body personalCenterRequestData : PersonalCenterRequestData): Observable<CommonResponse<PersonalResponse>>

    /**
     * 用户资料详情
     */
    @POST("weixin/user/profile")
    fun onMaterials(@Body materialsRequestData :MaterialsRequestData): Observable<CommonResponse<MaterialsResponse>>

    /**
     * 用户修改昵称
     */
    @POST("weixin/user/edit/nickName")
    fun onChangeNickname(@Body changeNicknameRequestData: ChangeNicknameRequestData): Observable<CommonResponse<String>>

    /**
     * 修改性别
     */
    @POST("weixin/user/edit/gender")
    fun onGender(@Body genderRequestData: GenderRequestData): Observable<CommonResponse<String>>


    /**
     * 修改个性签名
     */
    @POST("weixin/user/edit/signature")
    fun onSignature(@Body signatureRequestData : SignatureRequestData): Observable<CommonResponse<String>>

    /**
     * 用户收藏列表
     */
    @POST("weixin/user/collect/list")
    fun onCollectionList(@Body collectionListRequestData : CollectionRequestData): Observable<CommonResponse<CollectionListResponse>>

    /**
     * 用户关注列表
     */
    @POST("weixin/user/follows/list")
    fun onFocusOn(@Body collectionListRequestData : CollectionRequestData): Observable<CommonResponse<FocusOnResponse>>

    /**
     * 用户粉丝列表
     */
    @POST("weixin/user/fans/list")
    fun onFans(@Body collectionListRequestData : CollectionRequestData): Observable<CommonResponse<FansResponse>>

}