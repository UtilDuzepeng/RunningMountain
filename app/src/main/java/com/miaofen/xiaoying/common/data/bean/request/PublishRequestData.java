package com.miaofen.xiaoying.common.data.bean.request;


import java.util.List;

import mlxy.utils.S;

/**
 * 项目名称：com.miaofen.xiaoying.common.data.bean.request
 * 类描述：发布请求实体类
 * 创建人：duzepeng
 * 创建时间：2020/12/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class PublishRequestData {
    private String title;
    private String content;
    private int exceptedNumber;
    private int perCapitaBudget;
    private int costMethod;
    private String placeOfDeparture;
    private Long placeOfDepartureGps;
    private String destination;
    private Long destinationGps;
    private Long startTime;
    private Long endTime;
    private boolean fullCanJoin;
    private boolean joinAudit;
    private List<TagsData> tags;
    private List<ImagesData> images;
    private List<TripsData> trips;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExceptedNumber(int exceptedNumber) {
        this.exceptedNumber = exceptedNumber;
    }

    public void setPerCapitaBudget(int perCapitaBudget) {
        this.perCapitaBudget = perCapitaBudget;
    }

    public void setCostMethod(int costMethod) {
        this.costMethod = costMethod;
    }

    public void setPlaceOfDeparture(String placeOfDeparture) {
        this.placeOfDeparture = placeOfDeparture;
    }

    public void setPlaceOfDepartureGps(Long placeOfDepartureGps) {
        this.placeOfDepartureGps = placeOfDepartureGps;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDestinationGps(Long destinationGps) {
        this.destinationGps = destinationGps;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void setFullCanJoin(boolean fullCanJoin) {
        this.fullCanJoin = fullCanJoin;
    }

    public void setJoinAudit(boolean joinAudit) {
        this.joinAudit = joinAudit;
    }

    public void setTags(List<TagsData> tags) {
        this.tags = tags;
    }

    public void setImages(List<ImagesData> images) {
        this.images = images;
    }

    public void setTrips(List<TripsData> trips) {
        this.trips = trips;
    }

    class TagsData {
        private String tagName;

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }

    class ImagesData {
        private String imageUrl;

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    class TripsData {
        private String name;
        private String remark;
        private Long gps;
        private int sort;

        public void setName(String name) {
            this.name = name;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setGps(Long gps) {
            this.gps = gps;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }

}
