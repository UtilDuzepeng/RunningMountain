package com.miaofen.xiaoying.common.data.bean.response

/**
 * 项目名称：com.addictive.shooting.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/9/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

class CompanyInfo {

    var id: String? = null
    var companyName: String? = null
    var companyType: Int? = null
    var companyTypeDesc: String? = null
    var companyLinkman: String? = null
    var companyPhone: String? = null
    var businessLicenceUrl: String? = null
    var companyEmail: String? = null
    var gpsLocation: String? = null
    var companyDesc: String? = null
    var industryType: String? = null
    var industryTypeDesc: String? = null
    var createTime: String? = null
    var updateTime: String? = null
    var remark: String? = null
    var identityId: Int? = null

    /**
     * 地址信息
     */
    var province: String? = null

    var provinceCode: Int? = null
    var city: String? = null
    var cityCode: Int? = null
    var area: String? = null
    var areaCode: Int? = null
    var street: String? = null
    var streetCode: Int? = null
    var addrDetail: String? = null
    var regionCode: Int? = null
    var region: String? = null

    var longitude: String? = null
    var latitude: String? = null
    var token: String? = null

    /**
     * 处置企业经营许可证
     */
    var businessLicence: String? = null

    /**
     * 处置合同编号
     */
    var contractNumber: String? = null

    /**
     * 产废单位状态
     */
    var companyStatus: Int? = null

    /**
     * 产废单位状态描述
     */
    var companyStatusDesc: String? = null

    /**
     * 是否有环评, 2:没有环评 1:有环评
     */
    var hasEnv: Int? = null

    fun isEnvExist() = hasEnv == 1

    /**
     * 环评编号
     */
    var envNumber: String? = null

    /**
     * 是否靠近水源, 0:不靠近水源 1:靠近水源
     */
    var nearWater: Int? = null

    var declares: List<CompanyDeclareInfo>? = null

    //    /**
    //     * 危废统计信息
    //     */
    ////    private List<CompanyWasteInfo> wasteInfoList;
    //
    //    // todo 待补充
    //    /**
    //     * 危废申报信息
    //     */
    //    private List<CompanyWasteDeclareDto> wasteDeclare;
    //
    //    private List<CompanyCarsDto> carsInfoList;
    //
    //    @Data
    class CompanyDeclareInfo {

        var wasteType: String? = null

        var wasteCode: String? = null

        var customWasteName: String? = null

        var yearDeclare: Int? = null

        var disposeMethod: String? = null

        var relatedCompanyId: String? = null

        var relatedCompanyName: String? = null
    }
}
