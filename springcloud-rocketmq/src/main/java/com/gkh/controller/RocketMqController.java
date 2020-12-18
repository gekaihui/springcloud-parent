package com.gkh.controller;

import com.gkh.config.rocketmq.RocketMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gekaihui
 * @Description
 * @date 2020/10/16
 */
@RestController
@RequestMapping("/rocketmq")
public class RocketMqController {

    @Autowired
    private RocketMQProducer rocketMQProducer;

    @RequestMapping("send")
    public String sendMsg2(@RequestParam(value = "msg") String msg) throws Exception {
        rocketMQProducer.sendMsg("msg");
        return "发送成功！";
    }
}
