package com.example.springcloud_client.controller;

import com.example.springcloud_client.feign.FeignClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by 21510 on 2021/8/3.
 */

@RestController
public class HelloClientController {

    private static final String REST_URL_PREFIX = "http://cloud-provider-8000";

    @Autowired
    RestTemplate template;

    @Autowired
    FeignClients clients;

    @RequestMapping(value = "/Hello",method = RequestMethod.GET)
    public String getHello(){
        return template.getForObject(REST_URL_PREFIX+"/hello",String.class);
    }

    @RequestMapping(value = "/ReignHello",method = RequestMethod.GET)
    public String getReignHello(){
        return clients.getHelloWorld();
    }

}
