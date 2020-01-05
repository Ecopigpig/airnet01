package com.zsc.servicefeign.service;

import com.zsc.servicefeign.service.impl.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value="service-hi") //通过@ FeignClient（“服务名”），来指定调用哪个服务
@FeignClient(value = "service-hi",fallback = SchedualServiceHiHystric.class)
@Service
public interface SchedualServiceHi {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name")String name);
}
