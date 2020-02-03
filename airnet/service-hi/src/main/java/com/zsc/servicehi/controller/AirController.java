package com.zsc.servicehi.controller;

import com.zsc.servicehi.model.air.AirQuality;
import com.zsc.servicehi.model.pollutant.PollutantCity;
import com.zsc.servicehi.model.result.ResponseResult;
import com.zsc.servicehi.utils.GetAirData;
import com.zsc.servicehi.utils.GetPollutantData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/air")
public class AirController {

    @RequestMapping("/getAirQuality")
    public ResponseResult getAirQuality(@RequestParam String city){
        GetAirData airData = new GetAirData();
        AirQuality airQuality = airData.getAirQuality(city);
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        if(airQuality!=null){
            result.setMsg(true);
        }
        result.setData(airQuality);
        return result;
    }

}
