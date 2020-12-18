package com.gkh.springboot.config.rocketmq;

import com.gkh.springboot.constant.MQContstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author gekaihui
 * @Description
 * @date 2020/10/20
 */
@Slf4j
@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {
    /**
     *  默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息<br/>
     *  不要抛异常，如果没有return CONSUME_SUCCESS ，consumer会重新消费该消息，直到return CONSUME_SUCCESS
     */

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if(CollectionUtils.isEmpty(msgs)){
            log.info("===========================>接受到的消息为空，不处理，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        for (MessageExt msg: msgs) {
            log.info("===========================>接受到的消息为：" + msg.toString());
            if(msg.getTopic().equals(MQContstant.TOPIC_ONE)){
                if(msg.getTags().equals(MQContstant.TAG_ONE)){
                    //TODO 判断该消息是否重复消费（RocketMQ不保证消息不重复，如果你的业务需要保证严格的不重复消息，需要你自己在业务端去重）
                    //TODO 获取该消息重试次数
                    int reconsume = msg.getReconsumeTimes();
                    if(reconsume ==3){//消息已经重试了3次，如果不需要再次消费，则返回成功
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    //TODO 处理对应的业务逻辑
                }
            }
        }
        // 如果没有return success ，consumer会重新消费该消息，直到return success
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
