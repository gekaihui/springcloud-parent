package com.gkh.springboot.controller;

import com.gkh.springboot.constant.MQContstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gekaihui
 * @Description
 * @date 2020/10/16
 */
@RestController
@RequestMapping("/rocketmq")
public class RocketMqController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping("send")
    public String sendMsg () throws Exception  {
        List<String> msgs = new ArrayList<String>();
        msgs.add("mq消息：1");
//        msgs.add("mq消息：2");
//        msgs.add("mq消息：3");
//        msgs.add("mq消息：4");
//        msgs.add("mq消息：5");

        for(String msg: msgs) {
            Message message = new Message(MQContstant.TOPIC_ONE, MQContstant.TAG_ONE, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = defaultMQProducer.send(message);
            System.out.printf("%s%n", sendResult);
        }
        return "发送成功！";
    }
}
