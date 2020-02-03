package com.zsc.servicehi.controller;

import com.zsc.servicehi.model.pollutant.PollutantCity;
import com.zsc.servicehi.model.result.ResponseResult;
import com.zsc.servicehi.utils.GetPollutantData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pollutant")
public class PollutantController {

    @RequestMapping("/getCity")
    public ResponseResult getCity(@RequestParam String city){
        GetPollutantData getPollutantData = new GetPollutantData();
        PollutantCity pollutantCity = getPollutantData.getCityPollutionEpisode(city);
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        if(pollutantCity!=null){
            result.setMsg(true);
        }
        result.setData(pollutantCity);
        return result;
    }

    @RequestMapping("/getNation")
    public ResponseResult getNation(){
        GetPollutantData getPollutantData = new GetPollutantData();
        List<PollutantCity> pollutantCityList = getPollutantData.getNationPollutantRank();
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        if(pollutantCityList!=null){
            result.setMsg(true);
        }
        result.setData(pollutantCityList);
        return result;
    }

}
