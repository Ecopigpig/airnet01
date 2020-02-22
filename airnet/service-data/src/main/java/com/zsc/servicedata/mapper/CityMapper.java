package com.zsc.servicedata.mapper;

import model.weather.AreaCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CityMapper {

//    @Insert("insert into citysite () values ()")
    void saveSiteOfCity(@Param("city")String city,@Param("list")List<String> list);

    int insertAreaCode(@Param("list") List<AreaCode> areaCodeList);

    AreaCode selectCodeByAreaName(String city,String area);
}
