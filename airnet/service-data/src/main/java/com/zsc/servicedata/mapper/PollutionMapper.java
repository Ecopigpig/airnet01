package com.zsc.servicedata.mapper;

import com.zsc.servicedata.entity.Pollutant;
import com.zsc.servicedata.entity.PollutionEpisode;
import com.zsc.servicedata.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PollutionMapper {

//    @Select("select * from usertable order by user_id desc")
//    List<PollutionEpisode> getList();
//
//    @Select("select * from usertable order by user_id desc")
//    List<UserEntity> getUserList();
//
//    @Insert("insert into usertable(username,userpassword,age,sex) values(#{username},#{userpassword},#{age},#{sex})")
//    int AddUser(UserEntity user);

    @Select("select * from pollutant where userId = #{userId}")
    List<Pollutant> selectMonitorListByUser(Long userId);
}
