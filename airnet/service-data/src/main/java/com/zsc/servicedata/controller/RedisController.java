package com.zsc.servicedata.controller;

import com.alibaba.fastjson.JSON;
import com.zsc.servicedata.entity.EntityTest;
import com.zsc.servicedata.entity.UserEntity;
import com.zsc.servicedata.entity.Weather24Hours;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@RequestMapping("/redis")
@RestController
public class RedisController {
    private static int ExpireTime = 60000;   // redis中存储的过期时间60s

    @Autowired
    private RestTemplate template;

    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/setList")
    public Object setIntoRedis(@RequestBody EntityTest entityTest){
        List<Weather24Hours> list = entityTest.getList();
        String key = entityTest.getKey();
        list.forEach(item->{
            redisTemplate.opsForList().rightPush(key,item);
        });
        return list;
    }

    @RequestMapping("/getList")
    public List<Weather24Hours> getFromRedis(@RequestParam String key){
        List<Weather24Hours> resultList = new ArrayList<>();
        Long length = redisTemplate.opsForList().size(key);
        for(Long i =0L;i<length;i++){
            JSON json = (JSON) JSON.toJSON(redisTemplate.opsForList().index(key,i));
            Object javaObject = JSON.toJavaObject(json, Weather24Hours.class);
            Weather24Hours result = new Weather24Hours();
            BeanUtils.copyProperties(javaObject, result);
            resultList.add(result);
        }
        return resultList;
    }
}
