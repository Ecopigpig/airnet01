package com.zsc.servicehi.controller;

import com.zsc.servicehi.model.PollutionEpisode;
import com.zsc.servicehi.model.ResponseResult;
import com.zsc.servicehi.utils.GetData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/getCity")
    public ResponseResult index(@RequestParam String city){
        GetData getData = new GetData();
        PollutionEpisode pollutionEpisode = getData.getCityPollutionEpisode(city);
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        if(pollutionEpisode!=null){
            result.setMsg(true);
        }
        result.setData(pollutionEpisode);
        return result;
    }

}
