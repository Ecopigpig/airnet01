package com.zsc.servicedata.utils;

import com.zsc.servicedata.entity.param.AlarmParam;
import com.zsc.servicedata.entity.result.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.Transport;

@Component
public class Schedule {

    @Resource
    JavaMailSender jms;

    @Scheduled(fixedRate = 2000)
    public void checkIsAlarm(){
        //每隔半小时去获取实时数据，与数据库中用户设定作对比，超过预设值就发邮件

        //发邮件

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("Ecochoupipig@163.com");

        mailMessage.setTo("1315943577@qq.com");

        mailMessage.setSubject("测试邮件");

        mailMessage.setText("Hello World");

        jms.send(mailMessage);

//        System.out.println("555555555");
    }
}
