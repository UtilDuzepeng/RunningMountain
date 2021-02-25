package com.miaofen.xiaoying.common.data.remote.api

import com.miaofen.xiaoying.common.data.bean.CommonResponse
import com.miaofen.xiaoying.common.data.bean.request.*
import com.miaofen.xiaoying.common.data.bean.response.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.io.File

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
    fun onPersonalCenter(@Body personalCenterRequestData: PersonalCenterRequestData): Observable<CommonResponse<PersonalResponse>>

    /**
     * 用户资料详情
     */
    @POST("weixin/user/profile")
    fun onMaterials(@Body materialsRequestData: MaterialsRequestData): Observable<CommonResponse<MaterialsResponse>>

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
    fun onSignature(@Body signatureRequestData: SignatureRequestData): Observable<CommonResponse<String>>

    /**
     * 用户收藏列表
     */
    @POST("weixin/user/collect/list")
    fun onCollectionList(@Body collectionListRequestData: CollectionRequestData): Observable<CommonResponse<CollectionListResponse>>

    /**
     * 用户关注列表
     */
    @POST("weixin/user/follows/list")
    fun onFocusOn(@Body collectionListRequestData: CollectionRequestData): Observable<CommonResponse<FocusOnResponse>>

    /**
     * 用户粉丝列表
     */
    @POST("weixin/user/fans/list")
    fun onFans(@Body collectionListRequestData: CollectionRequestData): Observable<CommonResponse<FansResponse>>

    /**
     * 用户对外基本资料
     */
    @POST("weixin/user/outProfile")
    fun onBasicInformation(@Body basicInformationRequestData: BasicInformationRequestData): Observable<CommonResponse<PersonalHomPagerResponse>>

    /**
     * 用户对外详细资料
     */
    @POST("weixin/user/outProfile/detail")
    fun onDetails(@Body detailedDataRequestData: DetailedDataRequestData): Observable<CommonResponse<DetailedResponse>>

    /**
     * 用户对外旅行计划
     */
    @POST("weixin/user/outProfile/plan")
    fun onExternalDisplay(@Body externalDisplayRequestData: ExternalDisplayRequestData): Observable<CommonResponse<ExternalDisplayResponse>>

    /**
     * 文件上传
     */
    @Multipart
    @POST("common/file/upload")
    fun onFileUpload(@Part file :MultipartBody.Part, @Part type :MultipartBody.Part, @Part sign :MultipartBody.Part): Observable<CommonResponse<String>>

    /**
     * 修改头像
     */
    @POST("weixin/user/edit/avatar")
    fun onChangeYourAvatar(@Body changeYourAvatarRequestData :ChangeYourAvatarRequestData):Observable<CommonResponse<String>>

    /**
     * 修改地区
     */
    @POST("weixin/user/edit/area")
    fun onRevisionArea(@Body revisionAreaRequestData :RevisionAreaRequestData):Observable<CommonResponse<Boolean>>

    /**
     * 修改生日
     */
    @POST("weixin/user/edit/birthday")
    fun onModifyBirthday(@Body modifyBirthdayRequestData :ModifyBirthdayRequestData):Observable<CommonResponse<Boolean>>

    /**
     * 配置详情
     */
    @POST("weixin/user/privacy/info")
    fun onConfigurationDetails(@Body configurationDetails :ConfigurationDetails):Observable<CommonResponse<ConfigurationDetailsResponse>>

    /**
     * 用户隐私配置--是否展示计划
     */
    @POST("weixin/user/privacy/setting/plan")
    fun onExhibitionPlan(@Body exhibitionPlanRequestData :ExhibitionPlanRequestData):Observable<CommonResponse<Boolean>>

    /**
     * 用户隐私配置--是否展示资料
     */
    @POST("weixin/user/privacy/setting/personal")
    fun onExhibitionMaterials(@Body exhibitionMaterialsRequestData :ExhibitionMaterialsRequestData):Observable<CommonResponse<Boolean>>

    /**
     * 用户隐私配置--是否接收消息
     */
    @POST("weixin/user/privacy/setting/notify")
    fun onReceiveMessages(@Body receiveMessagesRequestData: ReceiveMessagesRequestData): Observable<CommonResponse<Boolean>>

    /**
     * 我发布的旅行计划
     */
    @POST("weixin/plan/myself")
    fun onIPost(@Body iPostRequestData :IPostRequestData):Observable<CommonResponse<IPostResponse>>

    /**
     * 我加入的旅行计划
     */
    @POST("weixin/plan/selfJoin")
    fun onParticipate(@Body participateRequestData :ParticipateRequestData):Observable<CommonResponse<ParticipateResponse>>



}