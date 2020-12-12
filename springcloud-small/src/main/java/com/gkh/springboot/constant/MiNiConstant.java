package com.gkh.springboot.constant;

/**
 * @author gekaihui
 * @date 2020/5/13 17:23
 */
public class MiNiConstant {
    //小程序appId
    public static String APPID = "wx91ff75d49c88d4d7";
    //小程序appSecret
    public static String APP_SECRET = "965ad60ea0f0f0b727a2213925a4fa3b";
    //获取session_key
    public static String SESSION_KEY_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    //发送订阅消息
    public static String SUBSCRIBE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
    //获取微信小程序二维码
    public static String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=";
    public static String WXACODE_UNLIMIT_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
}
