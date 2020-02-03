package com.zsc.servicehi.model.air;

import com.zsc.servicehi.model.pollutant.PollutionEpisode;
import lombok.Data;

/**
 * 城市空气质量
 */
@Data
public class AirQuality {
    /**
     * 基本污染指数模型
     */
    private PollutionEpisode pollutionEpisode;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类
     */
    private String Level;
    /**
     * 经度
     */
    private String longitude;
}
