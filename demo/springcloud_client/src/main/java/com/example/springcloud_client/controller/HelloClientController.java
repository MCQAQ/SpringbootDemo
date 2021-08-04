package com.example.springcloud_client.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 21510 on 2021/8/3.
 */

@RestController
public class HelloClientController {

    private static final String REST_URL_PREFIX = "http://localhost:7001";

    @RequestMapping(value = "/Hello",method = RequestMethod.GET)
    public String getHello(){
        RestTemplate template = new RestTemplate();
        return template.getForObject(REST_URL_PREFIX+"/hello",String.class);
    }

}
