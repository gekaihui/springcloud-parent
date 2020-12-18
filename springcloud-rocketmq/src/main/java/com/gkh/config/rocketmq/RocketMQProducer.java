package com.gkh.config.rocketmq;

import com.gkh.constant.MQContstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
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
@Slf4j
@Service
public class RocketMQProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.send-message-timeout}")
    private int sendMsgTimeout;

    /**
     * 发送普通消息
     *
     * @param msgBody
     */
    public void sendMsg(String msgBody) {
        log.info("发送消息：{}", msgBody);
        rocketMQTemplate.syncSend(MQContstant.TOPIC_TWO, MessageBuilder.withPayload(msgBody).build());
    }


    /**
     * 发送异步消息 在SendCallback中可处理相关成功失败时的逻辑
     */
    public void sendAsyncMsg(String msgBody) {
        rocketMQTemplate.asyncSend(MQContstant.TOPIC_TWO, MessageBuilder.withPayload(msgBody).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 处理消息发送成功逻辑
            }

            @Override
            public void onException(Throwable e) {
                // 处理消息发送异常逻辑
            }
        });

    }

    /**
     * 发送延时消息<br/>
     * 在start版本中 延时消息一共分为18个等级分别为：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h<br/>
     */
    public void sendDelayMsg(String msgBody, Integer delayLevel){
        rocketMQTemplate.syncSend(MQContstant.TOPIC_TWO, MessageBuilder.withPayload(msgBody).build(),sendMsgTimeout,delayLevel);
    }

    /**
     * 发送带tag的消息,直接在topic后面加上":tag"
     */
    public void sendTagMsg(String msgBody){
        rocketMQTemplate.syncSend(MQContstant.TOPIC_TWO, MessageBuilder.withPayload(msgBody).build());
    }
}
