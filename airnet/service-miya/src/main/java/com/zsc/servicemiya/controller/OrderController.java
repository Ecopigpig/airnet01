package com.zsc.servicemiya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(getClass());



    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @GetMapping(value = "/service")
    public String serviceHi(){
        return restTemplate.getForObject("http://localhost:8763/api/v1/product/service",String.class);
    }
}
