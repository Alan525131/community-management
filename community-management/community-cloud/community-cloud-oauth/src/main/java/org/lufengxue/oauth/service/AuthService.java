package org.lufengxue.oauth.service;

import org.lufengxue.pojo.user.userBo.AuthToken;

/*****
 * @Author: www.itheima
 * @Date: 2019/7/7 16:23
 * @Description: com.changgou.oauth.service
 ****/
public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
