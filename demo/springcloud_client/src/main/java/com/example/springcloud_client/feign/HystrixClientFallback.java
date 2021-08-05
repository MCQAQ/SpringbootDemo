package com.example.springcloud_client.feign;

import org.springframework.stereotype.Component;

/**
 * Created by 21510 on 2021/8/5.
 */

@Component
public class HystrixClientFallback implements FeignClients{

    @Override
    public String getHelloWorld(){
        return "Hystrix fallback";
    }

}
