package com.zsc.servicedata.controller;

import com.zsc.servicedata.entity.UserEntity;
import com.zsc.servicedata.service.Impl.UserServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private UserServiceImpl userServer;

    @RequestMapping("/getuserlist")
    public List<UserEntity> getUserList()
    {
        List<UserEntity> list = userServer.getUserList();
        list.forEach(item->{
            System.out.println(item.getUsername()+","+item.getSex());
        });
        return userServer.getUserList();
    }

}
