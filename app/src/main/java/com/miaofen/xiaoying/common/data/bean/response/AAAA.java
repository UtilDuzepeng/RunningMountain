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
         * pageNumber : 1
         * pageSize : 10
         * content : [{"motorcycle":"Kawasaki Ninja H2 Carbon","personalSignature":"操啊","avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"机车","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":1,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_8665","selfFollowUser":true,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":3,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_1730","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":4,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_5385","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":5,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_3157","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":6,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_8181","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":7,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_7297","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":8,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_270","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":9,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_7982","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":10,"userFollowSelf":true},{"motorcycle":null,"personalSignature":null,"avatarUrl":"https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132","nickName":"骑士_2332","selfFollowUser":false,"class":"com.paoshanba.user.dubbo.api.dto.UserListVo","userId":11,"userFollowSelf":true}]
         * totalElements : 12
         * empty : false
         */

        private int pageNumber;
        private int pageSize;
        private int totalElements;
        private boolean empty;
        private List<ContentBean> content;

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * motorcycle : Kawasaki Ninja H2 Carbon
             * personalSignature : 操啊
             * avatarUrl : https://thirdwx.qlogo.cn/mmopen/vi_32/YD1RqdHwfuMN748rATaDNnqVUIVONvWQJYicuGR0GxMNetr3SZ20kW3bU4CWjiaIbt00Ria1UYCZY6h27Tf7VQYibg/132
             * nickName : 机车
             * selfFollowUser : false
             * class : com.paoshanba.user.dubbo.api.dto.UserListVo
             * userId : 1
             * userFollowSelf : true
             */

            private String motorcycle;
            private String personalSignature;
            private String avatarUrl;
            private String nickName;
            private boolean selfFollowUser;
            @SerializedName("class")
            private String classX;
            private int userId;
            private boolean userFollowSelf;

            public String getMotorcycle() {
                return motorcycle;
            }

            public void setMotorcycle(String motorcycle) {
                this.motorcycle = motorcycle;
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

            public boolean isSelfFollowUser() {
                return selfFollowUser;
            }

            public void setSelfFollowUser(boolean selfFollowUser) {
                this.selfFollowUser = selfFollowUser;
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

            public boolean isUserFollowSelf() {
                return userFollowSelf;
            }

            public void setUserFollowSelf(boolean userFollowSelf) {
                this.userFollowSelf = userFollowSelf;
            }
        }
    }
}
