package com.bos.resttemplate.ribbonrestserver.client;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserRemoteClientFallbackFactory implements FallbackFactory<UserRemoteClient> {
    private Logger logger = LoggerFactory.getLogger(UserRemoteClientFallbackFactory.class);
    @Override
    public UserRemoteClient create(final Throwable throwable) {
        logger.error("UserRemoteClient 回退：",throwable);
        return new UserRemoteClient() {
            @Override
            public String hello() {
                String flag = "Fail";
                return flag;
            }
        };
    }
}
