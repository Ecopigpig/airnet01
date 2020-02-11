package com.zsc.servicedata.mapper;

import com.zsc.servicedata.entity.data.Pollutant;
import com.zsc.servicedata.entity.param.PollutionMonitorParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PollutionMapper {

//    @Select("select * from usertable order by user_id desc")
//    List<PollutionEpisode> getList();
//
//    @Select("select * from usertable order by user_id desc")
//    List<UserEntity> getUserList();
//
//    @Insert("insert into usertable(username,userpassword,age,sex) values(#{username},#{userpassword},#{age},#{sex})")
//    int AddUser(UserEntity user);

//    @Select("select * from pollutant where userId = #{userId}")
    List<Pollutant> selectMonitorListByUser(Long userId);

    int insertMonitorPoint(@Param("list") List<PollutionMonitorParam> paramList);

    List<Pollutant> selectAllMonitor();
}
