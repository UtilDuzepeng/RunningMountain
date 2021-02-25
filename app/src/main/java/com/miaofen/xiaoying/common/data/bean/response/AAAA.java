package com.miaofen.xiaoying.common.data.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.response
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class AAAA {


    /**
     * code : 200
     * message : 访问成功!
     * data : [{"spell":"B","areaCode":110000,"level":0,"areaName":"北京市","parentCode":0,"lon":116.4136103013,"class":"com.paoshanba.common.dto.RegionDto","lat":39.9110666857},{"spell":"T","areaCode":120000,"level":0,"areaName":"天津市","parentCode":0,"lon":117.190182,"class":"com.paoshanba.common.dto.RegionDto","lat":39.125596},{"spell":"H","areaCode":130000,"level":0,"areaName":"河北省","parentCode":0,"lon":114.4698103761,"class":"com.paoshanba.common.dto.RegionDto","lat":38.0360054744},{"spell":"S","areaCode":140000,"level":0,"areaName":"山西省","parentCode":0,"lon":112.549248,"class":"com.paoshanba.common.dto.RegionDto","lat":37.857014},{"spell":"N","areaCode":150000,"level":0,"areaName":"内蒙古自治区","parentCode":0,"lon":111.670801,"class":"com.paoshanba.common.dto.RegionDto","lat":40.818311},{"spell":"L","areaCode":210000,"level":0,"areaName":"辽宁省","parentCode":0,"lon":123.429096,"class":"com.paoshanba.common.dto.RegionDto","lat":41.796767},{"spell":"J","areaCode":220000,"level":0,"areaName":"吉林省","parentCode":0,"lon":125.3245,"class":"com.paoshanba.common.dto.RegionDto","lat":43.886841},{"spell":"H","areaCode":230000,"level":0,"areaName":"黑龙江省","parentCode":0,"lon":126.642464,"class":"com.paoshanba.common.dto.RegionDto","lat":45.756967},{"spell":"S","areaCode":310000,"level":0,"areaName":"上海市","parentCode":0,"lon":121.472644,"class":"com.paoshanba.common.dto.RegionDto","lat":31.231706},{"spell":"J","areaCode":320000,"level":0,"areaName":"江苏省","parentCode":0,"lon":118.767413,"class":"com.paoshanba.common.dto.RegionDto","lat":32.041544},{"spell":"Z","areaCode":330000,"level":0,"areaName":"浙江省","parentCode":0,"lon":120.153576,"class":"com.paoshanba.common.dto.RegionDto","lat":30.287459},{"spell":"A","areaCode":340000,"level":0,"areaName":"安徽省","parentCode":0,"lon":117.283042,"class":"com.paoshanba.common.dto.RegionDto","lat":31.86119},{"spell":"F","areaCode":350000,"level":0,"areaName":"福建省","parentCode":0,"lon":119.306239,"class":"com.paoshanba.common.dto.RegionDto","lat":26.075302},{"spell":"J","areaCode":360000,"level":0,"areaName":"江西省","parentCode":0,"lon":115.892151,"class":"com.paoshanba.common.dto.RegionDto","lat":28.676493},{"spell":"S","areaCode":370000,"level":0,"areaName":"山东省","parentCode":0,"lon":117.000923,"class":"com.paoshanba.common.dto.RegionDto","lat":36.675807},{"spell":"H","areaCode":410000,"level":0,"areaName":"河南省","parentCode":0,"lon":113.665412,"class":"com.paoshanba.common.dto.RegionDto","lat":34.757975},{"spell":"H","areaCode":420000,"level":0,"areaName":"湖北省","parentCode":0,"lon":114.298572,"class":"com.paoshanba.common.dto.RegionDto","lat":30.584355},{"spell":"H","areaCode":430000,"level":0,"areaName":"湖南省","parentCode":0,"lon":112.982279,"class":"com.paoshanba.common.dto.RegionDto","lat":28.19409},{"spell":"G","areaCode":440000,"level":0,"areaName":"广东省","parentCode":0,"lon":113.280637,"class":"com.paoshanba.common.dto.RegionDto","lat":23.125178},{"spell":"G","areaCode":450000,"level":0,"areaName":"广西壮族自治区","parentCode":0,"lon":108.320004,"class":"com.paoshanba.common.dto.RegionDto","lat":22.82402}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * spell : B
         * areaCode : 110000
         * level : 0
         * areaName : 北京市
         * parentCode : 0
         * lon : 116.4136103013
         * class : com.paoshanba.common.dto.RegionDto
         * lat : 39.9110666857
         */

        private String spell;
        private int areaCode;
        private int level;
        private String areaName;
        private int parentCode;
        private double lon;
        @SerializedName("class")
        private String classX;
        private double lat;

        public String getSpell() {
            return spell;
        }

        public void setSpell(String spell) {
            this.spell = spell;
        }

        public int getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(int areaCode) {
            this.areaCode = areaCode;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public int getParentCode() {
            return parentCode;
        }

        public void setParentCode(int parentCode) {
            this.parentCode = parentCode;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
