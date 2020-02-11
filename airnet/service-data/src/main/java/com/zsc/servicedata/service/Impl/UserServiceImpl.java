package com.zsc.servicedata.service.Impl;

import com.zsc.servicedata.entity.UserEntity;
import com.zsc.servicedata.entity.data.UserInfo;
import com.zsc.servicedata.mapper.PollutionMapper;
import com.zsc.servicedata.mapper.UserMapper;
import com.zsc.servicedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public Map<Long,String> getAllUserEmail(Set<Long> userIdList) {
        Map<Long,UserInfo> map;
        map =  userMapper.selectAllUserEmail(userIdList);
        Map<Long,String> resultMap = new HashMap<>();
        for(Long id :map.keySet()){
            UserInfo userInfo = map.get(id);
            resultMap.put(userInfo.getId(),userInfo.getEmail());
        }
        return resultMap;
    }
}
