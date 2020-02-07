package com.zsc.servicedata.service.Impl;

import com.zsc.servicedata.entity.UserEntity;
import com.zsc.servicedata.mapper.PollutionMapper;
import com.zsc.servicedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private PollutionMapper userMapper;

    @Override
    public List<UserEntity> getUserList() {
        try {
//            List<UserEntity> users = userMapper.getUserList();
//            return  users;
        }
        catch (Exception e)
        {
            throw e;
        }
        return null;
    }

    @Override
    public String AddUser(UserEntity user) {
        try {
//            int i = userMapper.AddUser(user);
//            return "添加成功" + i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }
        return null;
    }
}
