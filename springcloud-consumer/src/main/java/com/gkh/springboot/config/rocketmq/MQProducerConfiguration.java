package com.gkh.springboot.config.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author gekaihui
 * @Description
 * @date 2020/10/20
 */

@Slf4j
@SpringBootConfiguration
public class MQProducerConfiguration {

    @Value("${rocketmq1.producer.groupName}")
    private String groupName;
    @Value("${rocketmq1.producer.namesrvAddr}")
    private String namesrvAddr;
    /**
     * 消息最大大小，默认4M
     */
    @Value("${rocketmq1.producer.maxMessageSize}")
    private int maxMessageSize ;
    /**
     * 消息发送超时时间，默认3秒
     */
    @Value("${rocketmq1.producer.sendMsgTimeout}")
    private int sendMsgTimeout;
    /**
     * 消息发送失败重试次数，默认2次
     */
    @Value("${rocketmq1.producer.retryTimesWhenSendFailed}")
    private int retryTimesWhenSendFailed;

    @Bean
    public DefaultMQProducer getRocketMQProducer() {
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.namesrvAddr);
        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        //producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(this.maxMessageSize);
        producer.setSendMsgTimeout(this.sendMsgTimeout);
        producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);

        try {
            producer.start();
            log.info(String.format("===========================>producer is start ! groupName:[%s],namesrvAddr:[%s]"
                    , this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            log.error(String.format("===========================>producer is error {}", e.getMessage(),e));
        }
        return producer;
    }
}
