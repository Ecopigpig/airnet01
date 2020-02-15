package com.zsc.servicedata;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceDataApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test01() {
        stringRedisTemplate.opsForValue().set("k1", "v1");
    }

    @Test
    public void test02(){
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("拿到{}",k1);
    }

    @Test
    public void test05(){
//        UserEntity user = new UserEntity();
////        user.setId(5L);
////        user.setName("王五");
//        redisTemplate.opsForValue().set("user1",user);
    }
    //该test完成了把list存储到redis,再重新获取出来
    @Test
    public void test06() {
//        List<UserEntity> list = new ArrayList<>();
//        UserEntity entity = new UserEntity();
////        entity.setId(11111L);
////        entity.setName("AAAAAA");
//        UserEntity entity1 = new UserEntity();
////        entity1.setId(22222L);
////        entity1.setName("bbbbbb");
//        list.add(entity);
//        list.add(entity1);
//        list.forEach(item->{
//            redisTemplate.opsForList().rightPush("list",item);
//        });
//        for(int i =0;i<list.size();i++){
//            JSON json = (JSON) JSON.toJSON(redisTemplate.opsForList().index("list",i));
//            UserEntity javaObject = JSON.toJavaObject(json, UserEntity.class);
//            System.out.println(javaObject);
//        }
    }
}
