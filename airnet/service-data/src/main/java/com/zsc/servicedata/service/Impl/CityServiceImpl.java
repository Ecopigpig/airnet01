package com.zsc.servicedata.service.Impl;

import com.zsc.servicedata.mapper.CityMapper;
import com.zsc.servicedata.service.CityService;
import model.weather.AreaCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public int insertAreaCode(List<AreaCode> areaCodeList) {
        return cityMapper.insertAreaCode(areaCodeList);
    }

    @Override
    public String selectCodeByAreaName(String city,String area) {
        AreaCode areaCode = cityMapper.selectCodeByAreaName(city,area);
        if(areaCode==null){
            return null;
        }
        else{
            if(areaCode.getPostalCode()==null){
                return areaCode.getAreaCode();
            }else{
                return areaCode.getPostalCode();
            }
        }
    }

}
