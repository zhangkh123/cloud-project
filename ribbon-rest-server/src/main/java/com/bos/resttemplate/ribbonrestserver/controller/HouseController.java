package com.bos.resttemplate.ribbonrestserver.controller;

import com.bos.resttemplate.ribbonrestserver.bean.HouseInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HouseController {
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("/house/data")
    public HouseInfo getData(@RequestParam("name") String name){
        HouseInfo info = new HouseInfo("上海","上海","虹口区","闸北1号");
        return info;
    }

    @GetMapping("/house/data/{name}")
    public String getData1(@PathVariable("name") String name){
        return name;
    }

    @GetMapping("/choose")
    public Object chooseUrl(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("ribbon-rest-server");
        return serviceInstance;
    }



}
