package com.bos.resttemplate.ribbonrestserver.client;

import com.bos.resttemplate.ribbonrestserver.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "SPRING-CLOUD-PROVIDER" , configuration = FeignConfiguration.class,
        fallbackFactory = UserRemoteClientFallbackFactory.class)
public interface UserRemoteClient {
    @GetMapping("/user/hello")
    String hello();
}
