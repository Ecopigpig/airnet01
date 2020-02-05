package com.zsc.servicedata.service.Impl;

import com.zsc.servicedata.entity.UserEntity;
import com.zsc.servicedata.mapper.PollutionMapper;
import com.zsc.servicedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private PollutionMapper userMapper;

    @Override
    public List<UserEntity> getUserList() {
        try {
            List<UserEntity> users = userMapper.getUserList();
            return  users;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
