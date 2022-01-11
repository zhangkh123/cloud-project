package com.bos.resttemplate.ribbonrestserver.controller;

import com.bos.resttemplate.ribbonrestserver.bean.HouseInfo;
import com.bos.resttemplate.ribbonrestserver.client.UserRemoteClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class HouseClientController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private UserRemoteClient userRemoteClient;
    @GetMapping("/call/data")
    public HouseInfo getData(@RequestParam("name") String name){
        HouseInfo info =  restTemplate.getForObject
                ("http://ribbon-rest-server/house/data?name="+name,HouseInfo.class);
        System.out.println(info.getProvice());
        return info;
    }
    @GetMapping("/call/data/{name}")
    public String getData2(@PathVariable("name") String name){
        return restTemplate.getForObject
                ("http://ribbon-rest-server/house/data/{name}",String.class,name);
    }
    @GetMapping("/call/dataEntity")
    public HouseInfo getData3(@RequestParam("name") String name){
        ResponseEntity<HouseInfo> responseEntity = restTemplate.getForEntity
                ("http://ribbon-rest-server/house/data?name="+name,HouseInfo.class);
        if(responseEntity.getStatusCodeValue() == 200){
            return responseEntity.getBody();
        }
        return null;
    }
    @GetMapping("/callHello")
    public String callHello(){
        String result = userRemoteClient.hello();
        System.out.println("调用结果为："+result);
        return result;
    }
}
