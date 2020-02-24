package com.zsc.servicedata.controller;

import com.zsc.servicedata.service.CityService;
import com.zsc.servicedata.tag.PassToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import model.result.ResponseResult;
import model.weather.AreaCode;
import model.weather.InstanceWeather;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "CityController",tags = "城市相关控制器")
@RestController
@RequestMapping("/city")
public class CityController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CityService cityService;

    @PassToken
    @ApiOperation(value = "通过城市名称获取其对应的实时天气areaCode")
    @RequestMapping(value = "/getAreaCode", method = RequestMethod.GET)
    public ResponseResult getAreaCode(@RequestParam("city") String city,
                                      @RequestParam("area")String area) {
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        //先去数据库里面查询,是否有这个城市的areaCode
        //有,直接返回
        AreaCode areaCode = cityService.selectCodeByAreaName(city,area);
        //没有,去调API查一次再存到数据库里头
        if(areaCode==null) {
            List<AreaCode> areaCodeList = restTemplate.getForObject("http://localhost:8763/weather/getAreaCode?city=" + city, List.class);
            int i = cityService.insertAreaCode(areaCodeList);
            if(i>0) {
                areaCode = cityService.selectCodeByAreaName(city,area);
            }
            result.setMsg(true);
            result.setData(areaCode);
        }
        else {
            result.setMsg(true);
            result.setData(areaCode);
        }
        return result;
    }



    //城市的实时天气情况
    @ApiOperation(value = "根据城市中文名获取该城市的实时天气情况")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "city", value = "所在市名,不必带市字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "area", value = "市内城市名称", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getActualTimeWeather",method = RequestMethod.GET)
    public ResponseResult getActualTimeWeather(@RequestParam String city,@RequestParam String area) {
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        //先去获取城市的代码
        AreaCode areaCode = cityService.selectCodeByAreaName(city,area);
        //没有,去调API查一次再存到数据库里头
        if(areaCode==null) {
            List<AreaCode> areaCodeList = restTemplate.getForObject("http://localhost:8763/weather/getAreaCode?city=" + city, List.class);
            int i = cityService.insertAreaCode(areaCodeList);
            if(i>0) {
                areaCode = cityService.selectCodeByAreaName(city,area);
            }
        }
        //在去用代码查询实时天气
        InstanceWeather instanceWeather = restTemplate.getForObject("http://localhost:8763/weather/getInstanceWeather?areaCode="+areaCode.getAreaCode()+"&postalCode="+areaCode.getPostalCode(), InstanceWeather.class);
        if(instanceWeather!=null){
            instanceWeather.setCity(city+area);
            result.setMsg(true);
            result.setData(instanceWeather);
        }
        return result;
    }
}
