package com.zsc.servicedata.controller;

import com.zsc.servicedata.entity.Pollutant;
import com.zsc.servicedata.entity.UserEntity;
import com.zsc.servicedata.entity.param.PollutionMonitorParam;
import com.zsc.servicedata.entity.result.ResponseResult;
import com.zsc.servicedata.service.Impl.UserServiceImpl;
import com.zsc.servicedata.service.PollutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "MonitorController",tags = "监测控制器")
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private UserServiceImpl userServer;

    @Resource
    private PollutionService pollutionService;

    @ApiOperation(value = "根据用户ID获取用户的监测情况列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/getMonitorListByUser",method = RequestMethod.GET)
    public ResponseResult getMonitorListByUser(@RequestParam Long userId){
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        List<Pollutant> pollutantList = pollutionService.getMonitorListByUser(userId);
        if(pollutantList.size()>0){
            result.setMsg(true);
            result.setData(pollutantList);
            result.setTotal(Long.valueOf(pollutantList.size()));
        }
        return result;
    }

    @ApiOperation(value = "用户自行添加监测点")
    @RequestMapping(value = "/setMonitor",method = RequestMethod.POST)
    public ResponseResult setMonitor(@RequestBody PollutionMonitorParam param){
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        int response = pollutionService.setMonitor(param);
        if(response > 0){
            result.setMsg(true);
        }
        return result;
    }

    /**
     * 提取各个城市的监测点，用于用户选择
     */
    @ApiOperation(value = "通过城市名称获取旗下的监测点列表" )
    @RequestMapping(value = "/getMonitorPointInCity")
    public ResponseResult getMonitorPointInCity(@RequestParam String city){
        ResponseResult result = new ResponseResult();
        result.setMsg(false);
        Map<String,List<String>> map = pollutionService.getMonitorPointInCity(city);
//        int response = pollutionService.setMonitor(param);
//        if(response > 0){
//            result.setMsg(true);
//        }
        return result;
    }




    /**
     * 测试用
     * @return
     */
    @RequestMapping("/getuserlist")
    public List<UserEntity> getUserList()
    {
        List<UserEntity> list = userServer.getUserList();
        list.forEach(item->{
            System.out.println(item.getUsername()+","+item.getSex());
        });
        return userServer.getUserList();
    }

    /**
     * 测试用
     * @param user
     * @return
     */
    @PostMapping("/adduser")
    public String addUser(UserEntity user)
    {
        return userServer.AddUser(user);
    }

}
