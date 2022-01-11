package com.bos.resttemplate.ribbonrestserver.intcepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;

public class MyLoadBalancerInterceptor implements ClientHttpRequestInterceptor {
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private LoadBalancerRequestFactory loadBalancerRequestFactory;

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancer, LoadBalancerRequestFactory loadBalancerRequestFactory) {
        this.loadBalancer = loadBalancer;
        this.loadBalancerRequestFactory = loadBalancerRequestFactory;
    }

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancer){
        this(loadBalancer,new LoadBalancerRequestFactory(loadBalancer));
    }

    public MyLoadBalancerInterceptor() {
    }

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] bytes, final ClientHttpRequestExecution
            execution) throws IOException {
        final URI originalUrl = request.getURI();
        String serviceName = originalUrl.getHost();
        System.out.println("进入自定义的请求拦截器中：" + serviceName);
        Assert.state(serviceName != null,"Request URI does not contain a valid hostname:"+originalUrl);
        return this.loadBalancer.execute(serviceName,loadBalancerRequestFactory.createRequest(request,bytes,execution));
    }
}
