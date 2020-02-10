package com.zsc.servicedata.service;

import com.zsc.servicedata.entity.data.Pollutant;
import com.zsc.servicedata.entity.param.PollutionMonitorParam;
import org.apache.ibatis.annotations.Param;
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
     * @param paramList
     * @return
     */
    void setMonitor(List<PollutionMonitorParam> paramList)throws Exception;

    /**
     * 通过城市名返回该城市下的污染检测点
     * @param city
     * @return
     */
    Map<String, List<String>> getMonitorPointInCity(String city);
}
