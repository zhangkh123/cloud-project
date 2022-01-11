package com.bos.resttemplate.ribbonrestserver.config;

import com.bos.resttemplate.ribbonrestserver.intcepter.MyLoadBalanced;
import com.bos.resttemplate.ribbonrestserver.intcepter.MyLoadBalancerInterceptor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class MyLoadBalancerAutoConfiguration {
    @Autowired(required = false)
    @MyLoadBalanced
    private List<RestTemplate> restTemplates = Collections.emptyList();
    @Bean
    public MyLoadBalancerInterceptor myLoadBalancerInterceptor(){
        return new MyLoadBalancerInterceptor();
    }
    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer(){
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate restTemplate:MyLoadBalancerAutoConfiguration.this.restTemplates) {
                    List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
                    list.add(myLoadBalancerInterceptor());
                    restTemplate.setInterceptors(list);
                }
            }
        };
    }
}

