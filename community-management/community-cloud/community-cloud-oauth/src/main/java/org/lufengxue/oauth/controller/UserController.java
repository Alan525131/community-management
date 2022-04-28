package org.lufengxue.oauth.controller;


import org.lufengxue.enums.StatusCode;
import org.lufengxue.oauth.service.LoginService;
import org.lufengxue.response.Result;
import org.lufengxue.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 为了演示 登录的过程
 * @author ljh
 * @version 1.0
 * @date 2021/1/3 15:23
 * @description 标题
 * @package com.changgou.oauth.controller
 */
@RestController
@RequestMapping("/userx")
public class UserController {

    @Autowired
    private LoginService loginService;

    private static final String GRANT_TYPE="password";
    private static final String CLIENT_ID="changgou";
    private static final String CLIENT_SECRET="changgou";

    //Cookie存储的域名
    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    //Cookie生命周期
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @PostMapping("/login")
    public Result login(String username, String password){
        //1.模拟POSTman发送请求（申请令牌）
        Map<String,String> map = loginService.login(username,password,GRANT_TYPE,CLIENT_ID,CLIENT_SECRET);
        //2.获取到令牌数据进行解析
        String access_token = map.get("access_token");
        //3.将数据存储到cookie中，并返回给页面
        saveCookie(access_token);
        return new Result("DEFAULT_SUCCEED_CODE","DEFAULT_SUCCEED_MSG",access_token);

    }

    private void saveCookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.addCookie(response,cookieDomain,"/","Authorization",token,cookieMaxAge,false);
    }
}
