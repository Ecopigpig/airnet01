package com.zsc.servicehi.model;

/**
 * 单个城市的污染情况模型实体
 */
public class PollutionEpisode {
    /**
     * 城市排名
     */
    private String num;
    /**
     * 二氧化硫1小时平均浓度，ug/m3
     */
    private String so2;
    /**
     * 臭氧1小时平均，μg/m3
     */
    private String o3;
    /**
     * 颗粒物（粒径小于等于2.5μm）1小时平均，μg/m3
     */
    private String pm25;
    /**
     * 发布时间
     */
    private String ct;
    /**
     * 首要污染物
     */
    private String primaryPollutant;
    /**
     * 一氧化碳1小时平均，mg/m3
     */
    private String co;
    /**
     * 城市名称
     */
    private String area;
    /**
     * 二氧化氮1小时平均，μg/m3
     */
    private String no2;
    /**
     * 空气质量指数(AQI)，即air quality index，是定量描述空气质量状况的无纲量指数
     */
    private String aqi;
    /**
     * 空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类
     */
    private String quality;
    /**
     * 颗粒物（粒径小于等于10μm）1小时平均，μg/m3
     */
    private String pm10;
    /**
     * 臭氧8小时滑动平均，μg/m3
     */
    private String o3Per8h;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getO3Per8h() {
        return o3Per8h;
    }

    public void setO3Per8h(String o3Per8h) {
        this.o3Per8h = o3Per8h;
    }
}
