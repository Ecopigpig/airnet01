package com.zsc.servicedata.service;

import com.zsc.servicedata.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserEntity> getUserList();

    String AddUser(UserEntity user);
}
