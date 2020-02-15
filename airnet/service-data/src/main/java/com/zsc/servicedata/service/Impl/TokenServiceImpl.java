package com.zsc.servicedata.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zsc.servicedata.entity.data.UserInfo;
import com.zsc.servicedata.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(UserInfo userInfo) {
//        String token="";
//        token= JWT.create().withAudience(userInfo.getId().toString())
//                .sign(Algorithm.HMAC256(userInfo.getPassword()));
//        return token;

        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(userInfo.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(userInfo.getPassword()));
        return token;
    }
}
