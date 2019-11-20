package com.gkh.springboot.config.rocketmq2;

import com.gkh.springboot.constant.MQContstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author gekaihui
 * @Description
 * @date 2020/10/20
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = MQContstant.TOPIC_TWO, selectorExpression = "*", consumerGroup = "MyConsumer2")
public class RocketMQMsgListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        String msg = new String(body);
        log.info("接收到的消息: {}", msg);
    }
}
