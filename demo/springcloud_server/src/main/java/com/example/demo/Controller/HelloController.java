package com.example.demo.Controller;


import com.example.demo.Dao.Entity.UserEntity;
import com.example.demo.Dao.Mybatis.UserDao;
import com.example.demo.Service.RedisServiceImpl;
import com.example.demo.Service.UserService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 21510 on 2021/7/2.
 */

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

//    @Autowired
//    private DiscoveryClient client;

    @Autowired
    private RedisServiceImpl redisService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getHello() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        logger.info("test log");

        List<UserEntity> entity = userService.getByName("123");
        logger.info(entity.get(0).getOld());

        UserEntity userEntity = userDao.selectByName("123");
        logger.info(userEntity.getOld());

        redisService.set("test","test123",10L);

        String msg = "demo msg test";
        Message sendMsg = new Message("DemoTopic","DemoTag",msg.getBytes());
        SendResult sendResult = defaultMQProducer.send(sendMsg);
        logger.info("消息响应时间："+sendResult.toString());

        return "Hello World";
    }

//    @RequestMapping(value="/discovery", method = RequestMethod.GET)
//    public Object discovery() {
//        List<String> list = client.getServices();
//        List<ServiceInstance> servList = client.getInstances("cloud-provider-8000");
//
//        return this.client;
//    }

}
