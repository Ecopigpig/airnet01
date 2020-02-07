package com.zsc.servicedata.service.Impl;

import com.zsc.servicedata.entity.Pollutant;
import com.zsc.servicedata.entity.param.PollutionMonitorParam;
import com.zsc.servicedata.mapper.PollutionMapper;
import com.zsc.servicedata.service.PollutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("pollution")
public class PollutionServiceImpl implements PollutionService {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    private PollutionMapper pollutionMapper;

    @Override
    public List<Pollutant> getMonitorListByUser(Long userId) {
        return pollutionMapper.selectMonitorListByUser(userId);
    }

    @Override
    public int setMonitor(PollutionMonitorParam param) {
        return 0;
    }

    @Override
    public Map<String, List<String>> getMonitorPointInCity(String city) {

        return null;
    }
}
