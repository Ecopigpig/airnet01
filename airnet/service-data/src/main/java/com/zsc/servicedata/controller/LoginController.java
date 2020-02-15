package com.zsc.servicedata.controller;

import com.alibaba.fastjson.JSONObject;
import com.zsc.servicedata.entity.data.UserInfo;
import com.zsc.servicedata.service.TokenService;
import com.zsc.servicedata.service.UserService;
import com.zsc.servicedata.tag.UserLoginToken;
import com.zsc.servicedata.utils.TokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(value = "LoginController",tags = "登录控制器")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    // 登录
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Object login(UserInfo user) {
        JSONObject jsonObject=new JSONObject();
        UserInfo userForBase=userService.confirmUser(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    /***
     * 这个请求需要验证token才能访问
     *
     * @author: MRC
     * @date 2019年5月27日 下午5:45:19
     * @return String 返回类型
     */
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {

        // 取出token中带的用户id 进行操作
        System.out.println(TokenUtil.getTokenUserId());
        return "你已通过验证";
    }
}
