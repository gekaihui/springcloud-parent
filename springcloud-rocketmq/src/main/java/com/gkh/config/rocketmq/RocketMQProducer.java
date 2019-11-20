package com.gkh.springboot.config.rocketmq2;

import com.gkh.springboot.constant.MQContstant;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author gekaihui
 * @Description
 * @date 2020/10/20
 */
@Service
public class RocketMQProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.send-message-timeout}")
    private int sendMsgTimeout;

    /**
     * 发送普通消息
     * @param msg
     */
    public void sendMsg(String msg) {
        rocketMQTemplate.syncSend(MQContstant.TOPIC_TWO, MessageBuilder.withPayload(msg).build());
    }


}
