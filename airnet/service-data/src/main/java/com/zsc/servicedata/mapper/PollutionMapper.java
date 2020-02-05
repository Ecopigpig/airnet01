package com.zsc.servicedata.mapper;

import com.zsc.servicedata.entity.PollutionEpisode;
import com.zsc.servicedata.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PollutionMapper {

    @Select("select * from usertable order by user_id desc")
    List<PollutionEpisode> getList();

    @Select("select * from usertable order by user_id desc")
    List<UserEntity> getUserList();
}
