package com.zsc.servicehi.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsc.servicehi.model.pollutant.PollutantCity;
import com.zsc.servicehi.model.result.ResponseResult;
import com.zsc.servicehi.utils.GetPollutantData;
import com.zsc.servicehi.utils.PageUtil;
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
    public ResponseResult getNation(@RequestParam(value = "page", defaultValue = "1") int pageIndex,
                                    @RequestParam(value="size", defaultValue = "20") int pageSize){
        GetPollutantData getPollutantData = new GetPollutantData();
        List<PollutantCity> pollutantCityList = getPollutantData.getNationPollutantRank();
        List<PollutantCity> pageList = pollutantCityList.subList((pageIndex-1)*pageSize,pageSize);
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        if(pollutantCityList!=null){
            result.setMsg(true);
            result.setTotal(Long.valueOf(pageList.size()));
        }
        result.setData(pageList);
        return result;
    }

}
