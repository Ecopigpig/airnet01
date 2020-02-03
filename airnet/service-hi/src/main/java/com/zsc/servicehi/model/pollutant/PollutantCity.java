package com.zsc.servicehi.model.pollutant;

import lombok.Data;

import java.util.List;

/**
 * 城市污染情况
 */
@Data
public class PollutantCity {
    /**
     * 城市污染排名
     */
    private String num;
    /**
     * 基本污染指数模型
     */
    private PollutionEpisode pollutionEpisode;
    /**
     * 城市污染监测点污染情况list
     */
    private List<PollutionSite> pollutionSiteList;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public PollutionEpisode getPollutionEpisode() {
        return pollutionEpisode;
    }

    public void setPollutionEpisode(PollutionEpisode pollutionEpisode) {
        this.pollutionEpisode = pollutionEpisode;
    }

    public List<PollutionSite> getPollutionSiteList() {
        return pollutionSiteList;
    }

    public void setPollutionSiteList(List<PollutionSite> pollutionSiteList) {
        this.pollutionSiteList = pollutionSiteList;
    }
}
