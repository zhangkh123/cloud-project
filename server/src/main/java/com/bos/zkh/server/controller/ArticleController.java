package com.bos.zkh.server.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ArticleController {
    @Resource
    private EurekaClient eurekaClient;
    @GetMapping("/article/infos")
    public Object serviceUrl(){
        return eurekaClient.getInstancesByVipAddress("spring-cloud-provider",false);
    }
}
