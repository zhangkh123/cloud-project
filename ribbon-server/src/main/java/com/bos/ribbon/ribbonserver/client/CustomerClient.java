package com.bos.ribbon.ribbonserver.client;

import com.google.common.collect.Lists;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import rx.Observable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CustomerClient {
    public static void main(String[] args) {
        client();
    }
    public static void client(){
        List<Server> serverList = Lists.newArrayList(
                new Server("localhost",8081),new Server("localhost",8083));
        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().
                buildFixedServerListLoadBalancer(serverList);
        for (int i = 0; i < 5; i++) {
            String result = LoadBalancerCommand.<String>builder().
                    withLoadBalancer(loadBalancer).build().submit(new ServerOperation<String>() {
                        public Observable<String> call(Server server){
                            try{
                                String addr = "http://"+server.getHost()+":"+server.getPort()+"/user/hello";
                                System.out.println("调用地址："+addr);
                                URL url = new URL(addr);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setRequestMethod("GET");
                                conn.connect();
                                InputStream in = conn.getInputStream();
                                byte[] data = new byte[in.available()];
                                in.read(data);
                                return Observable.just(new String(data));
                            }catch (Exception e){
                                return Observable.error(e);
                            }
                        }
            }).toBlocking().first();
            System.out.println(result);
        }
    }


}
