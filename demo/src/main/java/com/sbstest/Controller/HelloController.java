package com.sbstest.Controller;

import com.sbstest.Dao.Entity.UserEntity;
import com.sbstest.Dao.Mybatis.UserDao;
import com.sbstest.Service.RedisServiceImpl;
import com.sbstest.Service.UserService;
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

    @Autowired
    private RedisServiceImpl redisService;

    @Autowired
    private UserDao userDao;

    @RequestMapping(name = "/hello",method = RequestMethod.GET)
    public String getHello(){
        logger.info("test log");

        List<UserEntity> entity = userService.getByName("123");
        logger.info(entity.get(0).getOld());

        UserEntity userEntity = userDao.selectByName("123");
        logger.info(userEntity.getOld());

        redisService.set("test","test123",10L);

        return "Hello World";
    }

}
