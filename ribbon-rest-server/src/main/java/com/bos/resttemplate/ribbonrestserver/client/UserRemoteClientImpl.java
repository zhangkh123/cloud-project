package com.bos.resttemplate.ribbonrestserver.client;

import org.springframework.stereotype.Component;

@Component
public class UserRemoteClientImpl implements UserRemoteClient{
    @Override
    public String hello() {
        String flag = "触发熔断-Fail";
        return flag;
    }
}
