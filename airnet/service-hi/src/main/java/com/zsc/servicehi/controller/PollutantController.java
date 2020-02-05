package com.zsc.servicehi.controller;

import com.zsc.servicehi.model.pollutant.PollutantCity;
import com.zsc.servicehi.model.result.ResponseResult;
import com.zsc.servicehi.utils.GetPollutantData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "PollutantController", tags = "空气污染情况控制器")
@RestController
@RequestMapping("/pollutant")
public class PollutantController {

    @ApiOperation(value = "根据城市中文名获取该城市的空气污染情况")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "city", value = "城市名称", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getCity",method = RequestMethod.GET)
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

    @ApiOperation(value = "获取全国PM2.5指数排行榜")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "页码,默认1", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "size", value = "页面大小,默认20", dataType = "int")
    })
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
