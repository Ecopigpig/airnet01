package com.zsc.servicedata.service;

import com.zsc.servicedata.entity.UserEntity;
import com.zsc.servicedata.entity.data.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface UserService {
    /**
     * 根据用户ID批量获取用户邮箱地址
     * @param userIdList
     * @return
     */
    Map<Long,String> getAllUserEmail(Set<Long> userIdList);
}
