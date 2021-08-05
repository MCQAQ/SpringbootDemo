package com.example.springcloud_client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 21510 on 2021/8/5.
 */

@FeignClient(value = "CLOUD-PROVIDER-8000", fallback = HystrixClientFallback.class)
public interface FeignClients {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String getHelloWorld();

}
