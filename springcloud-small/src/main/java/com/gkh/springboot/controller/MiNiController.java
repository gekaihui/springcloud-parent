package com.gkh.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkh.springboot.service.MiNiService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mini")
public class MiNiController {

    @Autowired
    private MiNiService miniService;
    
    /***
     * 小程序登录
     * @Author: gekaihui
     * @Date: 2020/5/27
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public JSONObject login(@RequestParam(value="code") String code) {
        JSONObject json = new JSONObject();
        if(StringUtils.isEmpty(code)){
            json.put("result", "0");
            return json;
        }
        String sessionId = miniService.miniLogin(code);
        json.put("result", "1");
        json.put("sessionId", sessionId);
        return json;
    }
    
    /***
     * 校验会话是否过期
     * @Author: gekaihui
     * @Date: 2020/5/27
     */
    @ResponseBody
    @RequestMapping("/checkSession")
    public JSONObject checkSession(@RequestParam(value = "sessionId") String sessionId){
        JSONObject json = miniService.miniCheckSession(sessionId);
        JSONObject jsonResult = new JSONObject();
        if(json == null){
            jsonResult.put("result", "0");
        }else{
            jsonResult.put("result", "1");
            jsonResult.put("openId", json.getString("openId"));
            jsonResult.put("session_key", json.getString("session_key"));
        }
        return jsonResult;

    }
    
    /***
     * 解密用户敏感信息
     * @Author: gekaihui
     * @Date: 2020/5/27
     */
    @ResponseBody
    @RequestMapping("/decryptUserInfo")
    public JSONObject decryptUserInfo(@RequestParam(value = "sessionId") String sessionId, @RequestParam(value = "userData") String userData){
        JSONObject json = new JSONObject();
        if(StringUtils.isEmpty(sessionId) || StringUtils.isEmpty(userData)){
            json.put("result", "0");
            return json;
        }
        String userInfo = miniService.decryptUserInfo(sessionId, userData);
        if(userInfo == null){
            json.put("result", "0");
            return json;
        }
        json.put("result", "1");
        json.put("userInfo", userInfo);
        return json;
    }
    
    /***
     * 获取accessToken
     * @Author: gekaihui
     * @Date: 2020/5/27
     */
    @ResponseBody
    @RequestMapping("/getAccessToken")
    public JSONObject getAccessToken(){
        String accessToken = miniService.getAccessToken();
        JSONObject json = new JSONObject();
        if(StringUtils.isEmpty(accessToken)){
            json.put("result", "0");
            return json;
        }
        json.put("result", "1");
        json.put("accessToken", accessToken);
        return json;
    }

    /***
     * 发送订阅消息
     * @Author: gekaihui
     * @Date: 2020/5/27
     */
    @ResponseBody
    @RequestMapping("/sendSubscribeMessage")
    public JSONObject sendSubscribeMessage(@RequestParam(value = "sessionId") String sessionId, @RequestParam(value = "templateId") String templateId){
        JSONObject json = new JSONObject();
        if(StringUtils.isEmpty(templateId) || StringUtils.isEmpty(sessionId)){
            json.put("result", "0");
            json.put("errmsg", "参数不能为空！");
            return json;
        }
        JSONObject result = miniService.sendSubscribeMessage(sessionId,templateId);
        return result;
    }
    
    /***
     * 获取小程序二维码
     * @Author: gekaihui
     * @Date: 2020/6/3
     */
    @ResponseBody
    @RequestMapping("/createQRCode")
    public JSONObject createQRCode(@RequestParam(value = "page") String page, @RequestParam(value = "width") String width){
        JSONObject json = new JSONObject();
        if(StringUtils.isEmpty(page) || StringUtils.isEmpty(width)){
            json.put("result", "0");
            json.put("errmsg", "参数不能为空！");
            return json;
        }
        JSONObject result = miniService.createQRCode(page,width);
        return result;
    }
    
}
