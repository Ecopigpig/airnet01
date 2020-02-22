package com.zsc.servicedata;

import com.zsc.servicedata.config.CacheConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@RestController
@EnableHystrix  //开启断路器
@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
@MapperScan("com.zsc.servicedata.mapper")
@EnableScheduling
//@ServletComponentScan
//@ComponentScan(basePackages={"com.zsc.servicedata.config","com.zsc.servicedata.service",
//        "com.zsc.servicedata.service.Impl","com.zsc.servicedata.tag"})
public class ServiceDataApplication {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceDataApplication.class, args);
    }

}
