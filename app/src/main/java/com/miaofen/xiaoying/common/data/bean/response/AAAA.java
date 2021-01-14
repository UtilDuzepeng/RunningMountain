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



    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userInfo : {"motorcycle":null,"gender":0,"personalSignature":null,"avatarUrl":"https://avatar.csdnimg.cn/8/1/7/1_weixin_41590779.jpg","nickName":"das","class":"com.paoshanba.common.dto.UserAvatarInfoVo","userId":2}
         * createTime : 1609836229000
         * contactWay : 18611752439
         * planId : 11
         * remark : 快点通过我，小垃圾
         * joinId : 47
         * class : com.paoshanba.travelplan.dubbo.api.vo.out.TravelPlanJoinListVo
         */

        private UserInfoBean userInfo;
        private long createTime;
        private String contactWay;
        private int planId;
        private String remark;
        private int joinId;
        @SerializedName("class")
        private String classX;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public int getPlanId() {
            return planId;
        }

        public void setPlanId(int planId) {
            this.planId = planId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getJoinId() {
            return joinId;
        }

        public void setJoinId(int joinId) {
            this.joinId = joinId;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public static class UserInfoBean {
            /**
             * motorcycle : null
             * gender : 0
             * personalSignature : null
             * avatarUrl : https://avatar.csdnimg.cn/8/1/7/1_weixin_41590779.jpg
             * nickName : das
             * class : com.paoshanba.common.dto.UserAvatarInfoVo
             * userId : 2
             */

            private Object motorcycle;
            private int gender;
            private Object personalSignature;
            private String avatarUrl;
            private String nickName;
            @SerializedName("class")
            private String classX;
            private int userId;

            public Object getMotorcycle() {
                return motorcycle;
            }

            public void setMotorcycle(Object motorcycle) {
                this.motorcycle = motorcycle;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public Object getPersonalSignature() {
                return personalSignature;
            }

            public void setPersonalSignature(Object personalSignature) {
                this.personalSignature = personalSignature;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
