package com.gkh.springboot.service;

import com.alibaba.fastjson.JSONObject;

public interface MiNiService {

    /**
     * 小程序登录
     * 
     * @Author: gekaihui
     * @Date: 2020/5/13 17:16
     */
    String miniLogin(String code);
    
    /**
     * 小程序登录校验
     * 
     * @Author: gekaihui
     * @Date: 2020/5/13 17:17
     */
    JSONObject miniCheckSession(String sessionId);
    
    /***
     * 
     * 解密小程序用户敏感信息
     * @Author: gekaihui
     * @Date: 2020/5/20 16:23
     */
    String decryptUserInfo(String sessionId, String userData);
    
    /***
     * 
     * 获取微信小程序accessToken
     * @Author: gekaihui
     * @Date: 2020/5/27 15:57
     */
    String getAccessToken();
    
    /***
     * 发送订阅消息
     * @Author: gekaihui
     * @Date: 2020/5/27
     */
    JSONObject sendSubscribeMessage(String sessionId, String templateId);
    
    /***
     * 获取小程序二维码
     * @Author: gekaihui
     * @Date: 2020/6/3
     */
    JSONObject createQRCode(String page, String width);
}
