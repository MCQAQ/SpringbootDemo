package com.sbstest.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 21510 on 2021/7/2.
 */

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(name = "/hello",method = RequestMethod.GET)
    public String getHello(){
        logger.info("test log");

        return "Hello World";
    }

}
