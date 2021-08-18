package com.example.springcloud_client.Configuration;

import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 21510 on 2021/8/18.
 */

@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if(CollectionUtils.isEmpty(msgs)){
            logger.info("接受到的消息为空，不处理，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        MessageExt messageExt = msgs.get(0);
        logger.info("接收到消息为："+messageExt.toString());
        if (messageExt.getTopic().equals("DemoTopic")) {

            int reconsume = messageExt.getReconsumeTimes();
            if (reconsume ==3){
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
