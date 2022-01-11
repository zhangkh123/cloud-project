package com.bos.zuulroute.config;

import com.bos.zuulroute.filter.IpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public IpFilter ipFilter(){
        return new IpFilter();
    }
}
