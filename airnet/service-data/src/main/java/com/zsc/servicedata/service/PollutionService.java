package com.zsc.servicedata.service;

import com.zsc.servicedata.entity.Pollutant;
import com.zsc.servicedata.entity.param.PollutionMonitorParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PollutionService {

    /**
     * 根据用户ID获取其检测点
     * @param userId
     * @return
     */
    List<Pollutant> getMonitorListByUser(Long userId);

    /**
     * 设置监测点
     * @param param
     * @return
     */
    int setMonitor(PollutionMonitorParam param);

    /**
     * 通过城市名返回该城市下的污染检测点
     * @param city
     * @return
     */
    Map<String, List<String>> getMonitorPointInCity(String city);
}
