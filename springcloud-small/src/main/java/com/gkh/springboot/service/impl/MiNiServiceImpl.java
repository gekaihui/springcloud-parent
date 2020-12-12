package com.gkh.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gkh.cipher.AesCbcUtil;
import com.gkh.cipher.CipherUtil;
import com.gkh.http.HttpClientUtil;
import com.gkh.springboot.config.redis.RedisUtil;
import com.gkh.springboot.constant.MiNiConstant;
import com.gkh.springboot.entity.SubscribeMessageDto;
import com.gkh.springboot.service.MiNiService;
import com.gkh.springboot.util.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MiNiServiceImpl implements MiNiService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String miniLogin(String code) {
        //调小程序API获取session_key和openid
        String param = "appid="+ MiNiConstant.APPID +"&secret="+ MiNiConstant.APP_SECRET +"&js_code="+ code +"&grant_type=authorization_code";
        String result = HttpClientUtil.get(MiNiConstant.SESSION_KEY_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String session_key = jsonObject.getString("session_key");
        String openid = jsonObject.getString("openid");
        System.out.println("session_key:"+session_key+"====openid:"+openid);

        String uuId = UUID.randomUUID().toString();
        String keyAndOpenId = openid + "&" + session_key;
        redisUtil.set(uuId, keyAndOpenId, 7200);
        return uuId;
    }

    @Override
    public JSONObject miniCheckSession(String sessionId) {
        String keyAndOpenId = redisUtil.get(sessionId);
        if(StringUtils.isEmpty(keyAndOpenId)){
            return null;
        }
        String[] array = keyAndOpenId.split("&");
        JSONObject json = new JSONObject();
        json.put("openId", array[0]);
        json.put("session_key", array[1]);
        return json;
    }

    @Override
    public String decryptUserInfo(String sessionId, String userData) {
        //todo 测试一下根据session_key校验并解密用户敏感信息
        String keyAndOpenId = redisUtil.get(sessionId);
        if(StringUtils.isEmpty(keyAndOpenId)){
            return null;
        }
        String[] array = keyAndOpenId.split("&");
        String session_key = array[1];
        JSONObject jsonUser = JSONObject.parseObject(userData);
        String rawData = jsonUser.getString("rawData");
        String signature = jsonUser.getString("signature");
        String signature2 = CipherUtil.encryString(rawData+session_key, CipherUtil.SHA_1);
        String resultData = null;
        if(signature.equals(signature2)){//校验通过
            String encryptedData = jsonUser.getString("encryptedData");
            String iv = jsonUser.getString("iv");
            try{
                resultData = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
                if (null != resultData && resultData.length() > 0) {
                    System.out.println(resultData);
                } else {
                    System.out.println("解密失败！");
                }
            }catch (Exception e){

            }
        }
        return resultData;
    }

    @Override
    public String getAccessToken() {
        String access_token = "";
        access_token = redisUtil.get("access_token");
        if(StringUtils.isNotEmpty(access_token)){
            return access_token;
        }
        String param = "grant_type=client_credential&appid="+ MiNiConstant.APPID +"&secret="+ MiNiConstant.APP_SECRET ;
        String result = HttpClientUtil.get(MiNiConstant.ACCESS_TOKEN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(result);
        access_token = jsonObject.getString("access_token");
        redisUtil.set("access_token", access_token, 7200);
        return access_token;
    }

    @Override
    public JSONObject sendSubscribeMessage(String sessionId, String templateId) {
        String access_token = getAccessToken();
        JSONObject json = miniCheckSession(sessionId);
        String openId = json.getString("openId");

        SubscribeMessageDto subMsgDto = new SubscribeMessageDto();
        subMsgDto.setTouser(openId);
        subMsgDto.setTemplate_id(templateId);
        subMsgDto.setLang("zh_CN");
        HashMap<String, Object>  data = new HashMap<>();
        JSONObject amount5 = new JSONObject();
        amount5.put("value", "500.48");
        JSONObject phone_number6 = new JSONObject();
        phone_number6.put("value", "17743872824");
        data.put("amount5", amount5);
        data.put("phone_number6", phone_number6);
        subMsgDto.setData(data);
        String param = JSON.toJSONString(subMsgDto);
        System.out.println(param);
        String url = MiNiConstant.SUBSCRIBE_MESSAGE_URL + access_token;
        String result = HttpClientUtil.postJson(url, param);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String errcode = jsonObject.getString("errcode");
        String errmsg = jsonObject.getString("errmsg");
        JSONObject jsonObj = new JSONObject();
        if(errcode.equals("0")){//发送成功
            jsonObj.put("result", "1");
            jsonObj.put("errmsg", errmsg);
        }else{
            System.out.println(errcode);
            jsonObj.put("result", "0");
            jsonObj.put("errmsg", errmsg);
        }
        return jsonObj;
    }

    @Override
    public JSONObject createQRCode(String path, String width) {
        JSONObject msg = new JSONObject();
        try {
            //这里调用的是上面的获取access_token方法
            String access_token = getAccessToken();
            String url = MiNiConstant.CREATE_QRCODE_URL+access_token;
            Map<String, String> param = new HashMap<>();
            //这里的page如果没有的话可以不写，默认是跳主页，如果写了没有的页面的话，会返回错误信息
            param.put("path",path);
            param.put("width",width);
            String json = JSON.toJSONString(param);
            ByteArrayInputStream inputStream = HttpUtil.sendPost(url, json);
            //这里判断的是返回的图片还是错误信息，一般错误信息不会大于200
            if (inputStream.available() <= 200){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int i;
                byte[] buffer = new byte[200];
                while ((i = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer,0,i);
                }
                String str = new String(byteArrayOutputStream.toByteArray());
                //错误信息的格式在官方文档里有
                JSONObject jsonObject = JSONObject.parseObject(str);
                if ("45029".equals(jsonObject.getString("errcode"))){
                    msg.put("result", "0");
                    msg.put("errmsg", "生成码个数总和到达最大个数限制");
                }else {
                    msg.put("result", "0");
                    msg.put("errmsg", "调微信获取二维码失败");
                }
                byteArrayOutputStream.close();
                return msg;
            }
            //这里我选择是上传到了oss，你也可以选择输出到本地
//            String fileName = "wx_qrcode"+".jpeg";
//            String path = "D:/wxMini";
//            String imgUrl = ossClientUtil.UploadImgAndReturnImgUrlInputStream(inputStream, fileName, path);

            //输出到本地的代码
            FileOutputStream fileOutputStream = new FileOutputStream("D:/wxMini/wx_qrcode.png");
            int i;
            byte[] buffer = new byte[200];
            while ((i = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,i);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

            inputStream.close();
            msg.put("result", "1");
            msg.put("errmsg", "获取二维码成功");
        }catch (Exception e){
            msg.put("result", "0");
            msg.put("errmsg", "获取二维码异常");
        }
        return msg;
    }
}
