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


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * subPlanCommentList : [{"userInfo":{"motorcycle":null,"gender":2,"personalSignature":"不知道写啥了","avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"we are going","class":"com.paoshanba.common.dto.UserAvatarInfoVo","userId":2},"starCount":0,"star":false,"createTime":1610638623000,"commentId":1610638623044,"parentCommentId":1610638564591,"canDelete":true,"follow":null,"class":"com.paoshanba.travelplan.dubbo.api.vo.dto.TravelPlanCommentDto","content":"1"},{"userInfo":{"motorcycle":"Kawasaki Ninja H2 Carbon","gender":1,"personalSignature":"操啊","avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"机车","class":"com.paoshanba.common.dto.UserAvatarInfoVo","userId":1},"starCount":0,"star":false,"createTime":1610846365000,"commentId":1610846365266,"parentCommentId":1610638623044,"canDelete":false,"follow":null,"class":"com.paoshanba.travelplan.dubbo.api.vo.dto.TravelPlanCommentDto","content":""},{"userInfo":{"motorcycle":"Kawasaki Ninja H2 Carbon","gender":1,"personalSignature":"操啊","avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"机车","class":"com.paoshanba.common.dto.UserAvatarInfoVo","userId":1},"starCount":0,"star":false,"createTime":1610846386000,"commentId":1610846385894,"parentCommentId":1610846365266,"canDelete":false,"follow":null,"class":"com.paoshanba.travelplan.dubbo.api.vo.dto.TravelPlanCommentDto","content":"3"}]
         * topPlanComment : {"userInfo":{"motorcycle":null,"gender":2,"personalSignature":"不知道写啥了","avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"we are going","class":"com.paoshanba.common.dto.UserAvatarInfoVo","userId":2},"starCount":1,"star":true,"createTime":1610638565000,"commentId":1610638564591,"parentCommentId":0,"canDelete":true,"follow":1,"class":"com.paoshanba.travelplan.dubbo.api.vo.dto.TravelPlanCommentDto","content":"怎么样？"}
         */

        private TopPlanCommentBean topPlanComment;
        private List<SubPlanCommentListBean> subPlanCommentList;

        public TopPlanCommentBean getTopPlanComment() {
            return topPlanComment;
        }

        public void setTopPlanComment(TopPlanCommentBean topPlanComment) {
            this.topPlanComment = topPlanComment;
        }

        public List<SubPlanCommentListBean> getSubPlanCommentList() {
            return subPlanCommentList;
        }

        public void setSubPlanCommentList(List<SubPlanCommentListBean> subPlanCommentList) {
            this.subPlanCommentList = subPlanCommentList;
        }

        public static class TopPlanCommentBean {
            /**
             * userInfo : {"motorcycle":null,"gender":2,"personalSignature":"不知道写啥了","avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"we are going","class":"com.paoshanba.common.dto.UserAvatarInfoVo","userId":2}
             * starCount : 1
             * star : true
             * createTime : 1610638565000
             * commentId : 1610638564591
             * parentCommentId : 0
             * canDelete : true
             * follow : 1
             * class : com.paoshanba.travelplan.dubbo.api.vo.dto.TravelPlanCommentDto
             * content : 怎么样？
             */

            private UserInfoBean userInfo;
            private int starCount;
            private boolean star;
            private long createTime;
            private long commentId;
            private int parentCommentId;
            private boolean canDelete;
            private int follow;
            @SerializedName("class")
            private String classX;
            private String content;

            public UserInfoBean getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBean userInfo) {
                this.userInfo = userInfo;
            }

            public int getStarCount() {
                return starCount;
            }

            public void setStarCount(int starCount) {
                this.starCount = starCount;
            }

            public boolean isStar() {
                return star;
            }

            public void setStar(boolean star) {
                this.star = star;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getCommentId() {
                return commentId;
            }

            public void setCommentId(long commentId) {
                this.commentId = commentId;
            }

            public int getParentCommentId() {
                return parentCommentId;
            }

            public void setParentCommentId(int parentCommentId) {
                this.parentCommentId = parentCommentId;
            }

            public boolean isCanDelete() {
                return canDelete;
            }

            public void setCanDelete(boolean canDelete) {
                this.canDelete = canDelete;
            }

            public int getFollow() {
                return follow;
            }

            public void setFollow(int follow) {
                this.follow = follow;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public static class UserInfoBean {
                /**
                 * motorcycle : null
                 * gender : 2
                 * personalSignature : 不知道写啥了
                 * avatarUrl : https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132
                 * nickName : we are going
                 * class : com.paoshanba.common.dto.UserAvatarInfoVo
                 * userId : 2
                 */

                private Object motorcycle;
                private int gender;
                private String personalSignature;
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

                public String getPersonalSignature() {
                    return personalSignature;
                }

                public void setPersonalSignature(String personalSignature) {
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

        public static class SubPlanCommentListBean {
            /**
             * userInfo : {"motorcycle":null,"gender":2,"personalSignature":"不知道写啥了","avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"we are going","class":"com.paoshanba.common.dto.UserAvatarInfoVo","userId":2}
             * starCount : 0
             * star : false
             * createTime : 1610638623000
             * commentId : 1610638623044
             * parentCommentId : 1610638564591
             * canDelete : true
             * follow : null
             * class : com.paoshanba.travelplan.dubbo.api.vo.dto.TravelPlanCommentDto
             * content : 1
             */

            private UserInfoBeanX userInfo;
            private int starCount;
            private boolean star;
            private long createTime;
            private long commentId;
            private long parentCommentId;
            private boolean canDelete;
            private Object follow;
            @SerializedName("class")
            private String classX;
            private String content;

            public UserInfoBeanX getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBeanX userInfo) {
                this.userInfo = userInfo;
            }

            public int getStarCount() {
                return starCount;
            }

            public void setStarCount(int starCount) {
                this.starCount = starCount;
            }

            public boolean isStar() {
                return star;
            }

            public void setStar(boolean star) {
                this.star = star;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getCommentId() {
                return commentId;
            }

            public void setCommentId(long commentId) {
                this.commentId = commentId;
            }

            public long getParentCommentId() {
                return parentCommentId;
            }

            public void setParentCommentId(long parentCommentId) {
                this.parentCommentId = parentCommentId;
            }

            public boolean isCanDelete() {
                return canDelete;
            }

            public void setCanDelete(boolean canDelete) {
                this.canDelete = canDelete;
            }

            public Object getFollow() {
                return follow;
            }

            public void setFollow(Object follow) {
                this.follow = follow;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public static class UserInfoBeanX {
                /**
                 * motorcycle : null
                 * gender : 2
                 * personalSignature : 不知道写啥了
                 * avatarUrl : https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132
                 * nickName : we are going
                 * class : com.paoshanba.common.dto.UserAvatarInfoVo
                 * userId : 2
                 */

                private Object motorcycle;
                private int gender;
                private String personalSignature;
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

                public String getPersonalSignature() {
                    return personalSignature;
                }

                public void setPersonalSignature(String personalSignature) {
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
}
