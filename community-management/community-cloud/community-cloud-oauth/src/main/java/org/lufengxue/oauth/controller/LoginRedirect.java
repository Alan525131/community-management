package org.lufengxue.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/1/6 08:44
 * @description 标题
 * @package com.changgou.oauth.controller
 */
@Controller
@RequestMapping("/oauth")
public class LoginRedirect {

    //展示登录的页面
    @GetMapping(value = "/login")
    public String login(String url, Model model){
        //返回逻辑视图页面
        model.addAttribute("url",url);
        return "login";
    }
}
