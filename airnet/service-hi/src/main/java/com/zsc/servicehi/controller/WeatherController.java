package com.zsc.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsc.servicehi.model.result.ResponseResult;
import com.zsc.servicehi.model.transfer.EntityTest;
import com.zsc.servicehi.model.weather.Weather24Hours;
import com.zsc.servicehi.model.weather.WeatherIn15Days;
import com.zsc.servicehi.utils.GetWeatherData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weather")
@EnableCaching
public class WeatherController {

    @Autowired
    private RestTemplate template;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    //这个链接能拿到数据
    @RequestMapping("/get24HourData")
    public List<Weather24Hours> get24HourData(@RequestParam String city){
        GetWeatherData getWeatherData = new GetWeatherData();
        List<Weather24Hours> weather24HoursList = getWeatherData.get24HourWeather(city);
        return weather24HoursList;
    }

    @RequestMapping("/get24Hour")
    public ResponseResult get24Hour(@RequestParam String city){
        GetWeatherData getWeatherData = new GetWeatherData();
        List<Weather24Hours> weather24HoursList = new ArrayList<>();
        List<Weather24Hours> object = template.getForObject("http://127.0.0.1:8708/redis/getList?key="+city, List.class);
        if(object.size()==0){
            //没有缓存就存进去
            weather24HoursList = getWeatherData.get24HourWeather(city);
            EntityTest entityTest = new EntityTest();
            entityTest.setKey(city);
            entityTest.setList(weather24HoursList);
            template.postForObject("http://127.0.0.1:8708/redis/setList",entityTest,List.class);
        }else{
            //有缓存就取出来
            weather24HoursList.addAll(object);
        }
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        if(weather24HoursList !=null){
            result.setMsg(true);
            result.setTotal(Long.valueOf(weather24HoursList.size()));
        }
        result.setData(weather24HoursList);
        return result;
    }

    @RequestMapping("/getIn15Days")
    public ResponseResult getIn15Days(@RequestParam String city){
        GetWeatherData getWeatherData = new GetWeatherData();
        List<WeatherIn15Days> weatherIn15DaysList = getWeatherData.getWeatherIn15Days(city);
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        if(weatherIn15DaysList !=null){
            result.setMsg(true);
        }
        result.setData(weatherIn15DaysList);
        return result;
    }
}
