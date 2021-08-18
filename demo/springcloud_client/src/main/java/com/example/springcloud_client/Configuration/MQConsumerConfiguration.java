package com.example.springcloud_client.Configuration;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 21510 on 2021/8/18.
 */

@Configuration
public class MQConsumerConfiguration {

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketmq.consumer.topics}")
    private String topics;
    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;
    @Autowired
    private MQConsumeMsgListenerProcessor mqMessageListenerProcessor;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.subscribe(topics,"*");
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(mqMessageListenerProcessor);

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);

        try {
            consumer.start();
        } catch (Exception e){
            logger.error(e.getMessage());
        }

        return consumer;
    }

}
