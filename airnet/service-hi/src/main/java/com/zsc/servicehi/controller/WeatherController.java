package com.zsc.servicehi.controller;

import com.zsc.servicehi.model.result.ResponseResult;
import com.zsc.servicehi.model.weather.Weather24Hours;
import com.zsc.servicehi.model.weather.WeatherIn15Days;
import com.zsc.servicehi.utils.GetWeatherData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @RequestMapping("/get24Hour")
    public ResponseResult get24Hour(@RequestParam String city){
        GetWeatherData getWeatherData = new GetWeatherData();
        List<Weather24Hours> weather24HoursList = getWeatherData.get24HourWeather(city);
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        if(weather24HoursList !=null){
            result.setMsg(true);
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
