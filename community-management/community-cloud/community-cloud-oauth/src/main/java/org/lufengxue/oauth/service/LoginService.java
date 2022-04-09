package org.lufengxue.oauth.service;

import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/1/3 15:28
 * @description 标题
 * @package com.changgou.oauth.service
 */
public interface LoginService {

    Map<String, String> login(String username, String password, String grantType, String clientId, String clientSecret);
}
