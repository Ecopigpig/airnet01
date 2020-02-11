package com.zsc.servicedata.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsc.servicedata.entity.MonitorMark;
import com.zsc.servicedata.entity.PollutantCity;
import com.zsc.servicedata.entity.PollutionEpisode;
import com.zsc.servicedata.entity.UserMark;
import com.zsc.servicedata.entity.data.Pollutant;
import com.zsc.servicedata.entity.enums.AirQualityEnum;
import com.zsc.servicedata.service.PollutionService;
import com.zsc.servicedata.service.UserService;
import org.apache.tomcat.jni.Poll;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

import static com.zsc.servicedata.entity.enums.AirQualityEnum.FINE_QUALITY;

@Component
public class Schedule {

    @Resource
    JavaMailSender jms;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PollutionService pollutionService;

    @Resource
    private UserService userService;


    //    @Scheduled(fixedRate = 2000)
    @Scheduled(fixedRate = 2000000)
    public void checkIsAlarm() throws IOException {
        //每隔半小时去获取实时数据，与数据库中用户设定作对比，超过预设值就发邮件
        //先从缓存里头获取全国实时排行榜，从排行榜里头获取所有城市的监测点的浓度
        String result = restTemplate.getForObject("http://localhost:8763/pollutant/offerNationPollutant", String.class);
        List<PollutionEpisode> cityList = new ArrayList<>();
        JSONArray jsonArray = JSONObject.parseArray(result);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject job = jsonArray.getJSONObject(i);
            ObjectMapper objectMapper = new ObjectMapper();
            String str = job.getString("pollutionEpisode");
            PollutionEpisode city = objectMapper.readValue(str, PollutionEpisode.class);
            cityList.add(city);
        }
        //扫描数据库中各个用户的监测数据
        List<Pollutant> pollutantList = pollutionService.getAllMonitors();
        //记录需要发送邮件的用户ID
        Set<Long> userIdList = new HashSet<>();
        //记录用户所监测的城市和超过阈值的参数值
        //其中的key,String = id + area;
        Map<UserMark, MonitorMark> markMap = new HashMap<>();
        //数据库和实时数据进行比对
        cityList.forEach(city -> {
            for (Pollutant pollutant : pollutantList) {
                //是同一个城市,可以开始比对
                if (city.getArea().equals(pollutant.getArea())) {
                    //特殊的空气质量情况
                    int cityQualityNum = changeIntoQualityNum(city.getQuality());
                    int monitorQualityNum = changeIntoQualityNum(pollutant.getQuality());
                    if (cityQualityNum > monitorQualityNum) {
                        userIdList.add(pollutant.getUserId());
                        markDownOverData(markMap,city,pollutant,"quality");
//                        MonitorMark monitorMark =  new MonitorMark();
//                        monitorMark.setArea(pollutant.getArea());
//                        monitorMark.setAlarmText("当前空气质量："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality());
//                        UserMark userMark = new UserMark();
//                        userMark.setUserId(pollutant.getUserId());
//                        userMark.setMarkCity(pollutant.getUserId()+pollutant.getArea());
//                        markMap.put(userMark,monitorMark);
                        break;
                    }
                    if (Float.valueOf(city.getSo2()) > pollutant.getSo2()) {
                        //将该用户记录在需要发送邮件的名单中
                        userIdList.add(pollutant.getUserId());
                        break;
                    }
                    if (Float.valueOf(city.getO3()) > pollutant.getO3()) {
                        userIdList.add(pollutant.getUserId());
                        break;
                    }
                    if (Float.valueOf(city.getPm25()) > pollutant.getPm25()) {
                        userIdList.add(pollutant.getUserId());
                        break;
                    }
                    if (Float.valueOf(city.getCo()) > pollutant.getCo()) {
                        userIdList.add(pollutant.getUserId());
                        break;
                    }
                    if (Float.valueOf(city.getNo2()) > pollutant.getNo2()) {
                        userIdList.add(pollutant.getUserId());
                        break;
                    }
                    if (Float.valueOf(city.getAqi()) > pollutant.getAqi()) {
                        userIdList.add(pollutant.getUserId());
                        break;
                    }
                    if (Float.valueOf(city.getPm10()) > pollutant.getPM10()) {
                        userIdList.add(pollutant.getUserId());
                        break;
                    }
                    if (Float.valueOf(city.getO3Per8h()) > pollutant.getO3per8h()) {
                        userIdList.add(pollutant.getUserId());
                        break;
                    }
                }
            }
        });
//        System.out.println(userIdList);
        //发邮件get
        //根据用户ID,批量获取其邮件地址
        Map<Long, String> map = userService.getAllUserEmail(userIdList);
        for (Long id : map.keySet()) {
            for(UserMark userMark:markMap.keySet()){
                if(id.equals(userMark.getUserId())){
                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setFrom("Ecochoupipig@163.com");
                    mailMessage.setTo(map.get(id));
                    mailMessage.setSubject("报警邮件");
                    MonitorMark monitorMark = markMap.get(userMark);
                    mailMessage.setText(monitorMark.getAlarmText());
                    jms.send(mailMessage);
                }
            }
        }
    }



    private static int changeIntoQualityNum(String quality) {
        int qualityNum = 0;
        switch (quality) {
            case "优质":
                qualityNum = 1;
                break;
            case "良好":
                qualityNum = 2;
                break;
            case "轻度污染":
                qualityNum = 3;
                break;
            case "中度污染":
                qualityNum = 4;
                break;
            case "重度污染":
                qualityNum = 5;
                break;
            case "严重污染":
                qualityNum = 6;
                break;
            default:
                break;
        }
        return qualityNum;
    }

    private static Map<UserMark, MonitorMark> markDownOverData(Map<UserMark, MonitorMark> markMap, PollutionEpisode city,Pollutant pollutant,String pollutantName){
        MonitorMark monitorMark =  new MonitorMark();
        monitorMark.setArea(pollutant.getArea());
        StringBuilder stringBuilder = new StringBuilder();
        switch (pollutantName){
            case "quality":{
                if(markMap.isEmpty()){
                    stringBuilder.append("您所监测的城市："+pollutant.getArea()+"，当前空气质量："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality()+"\n");
                }else{
                    String existedStr = markMap.get(pollutant.getUserId()).getAlarmText()+"\n";
                    stringBuilder.append(existedStr);
                }
                monitorMark.setAlarmText(stringBuilder.toString());
                break;
            }
            case "so2": {
                if(markMap==null){
                    stringBuilder.append("您所监测的城市："+pollutant.getArea()+"，当前SO2浓度：" + city.getQuality() + "，超过了您所设置的警报阈值：" + pollutant.getQuality()+"\n");
                }else{
                    String existedStr = markMap.get(pollutant.getUserId()).getAlarmText()+"\n";
                    stringBuilder.append(existedStr);
                }
                monitorMark.setAlarmText(stringBuilder.toString());
                break;
            }
            case "O3": {
                if(markMap==null){
                    stringBuilder.append("您所监测的城市："+pollutant.getArea()+"，当前O3浓度：" + city.getQuality() + "，超过了您所设置的警报阈值：" + pollutant.getQuality()+"\n");
                }else{
                    String existedStr = markMap.get(pollutant.getUserId()).getAlarmText()+"\n";
                    stringBuilder.append(existedStr);
                }
                monitorMark.setAlarmText(stringBuilder.toString());
                break;
            }
            case "PM25":
                monitorMark.setAlarmText("您所监测的城市："+pollutant.getArea()+"，当前pm2.5浓度："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality());
                break;
            case "CO":
                monitorMark.setAlarmText("您所监测的城市："+pollutant.getArea()+"，当前CO浓度："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality());
                break;
            case "NO2":
                monitorMark.setAlarmText("您所监测的城市："+pollutant.getArea()+"，当前NO2浓度："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality());
                break;
            case "AQI":
                monitorMark.setAlarmText("您所监测的城市："+pollutant.getArea()+"，您所监测的城市："+pollutant.getArea()+"，当前AQI指数："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality());
                break;
            case "PM10":
                monitorMark.setAlarmText("您所监测的城市："+pollutant.getArea()+"，您所监测的城市："+pollutant.getArea()+"，当前pm10的浓度："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality());
                break;
            case "O3PER8H":
                monitorMark.setAlarmText("您所监测的城市："+pollutant.getArea()+"，当前O3每8小时的浓度："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality());
                break;
            default:
                break;
        }
//        monitorMark.setAlarmText("当前空气质量："+city.getQuality()+"，超过了您所设置的警报阈值："+pollutant.getQuality());
        UserMark userMark = new UserMark();
        userMark.setUserId(pollutant.getUserId());
        userMark.setMarkCity(pollutant.getUserId()+pollutant.getArea());
//        monitorMark.setAlarmText(stringBuilder.toString());
        markMap.put(userMark,monitorMark);
        return markMap;
    }
}
