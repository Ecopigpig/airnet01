package com.zsc.servicedata.controller;

import com.zsc.servicedata.entity.UserEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@RequestMapping("/redis")
@RestController
public class RedisController {
    private static int ExpireTime = 60000;   // redis中存储的过期时间60s


}
