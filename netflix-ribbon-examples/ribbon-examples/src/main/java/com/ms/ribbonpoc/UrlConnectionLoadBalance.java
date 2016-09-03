package com.ms.ribbonpoc;

import com.google.common.collect.ImmutableList;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import rx.Observable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UrlConnectionLoadBalance {

    static List<Server> serverList = ImmutableList.of(
            new Server("www.google.com", 80),
            new Server("localhost", 9000),
            new Server("www.linkedin.com", 80));

    public static void main(String args[]){

        BaseLoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder()
                .buildFixedServerListLoadBalancer(serverList);

        for (int i = 0; i < 10; i++) {
            System.out.println(callApi(loadBalancer));
        }

        System.out.println(loadBalancer.getLoadBalancerStats());

    }

    private static String callApi(BaseLoadBalancer loadBalancer) {
        ServerOperation<String> submitToServer = new ServerOperation<String>() {
            @Override
            public Observable<String> call(Server server) {
                URL url;
                try {
                    url = new URL("http://" + server.getHost() + ":" + server.getPort() + "/");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    return Observable.just(conn.getResponseMessage());
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        };
        return LoadBalancerCommand.<String>builder()
                .withLoadBalancer(loadBalancer)
                .build()
                .submit(submitToServer)
                .toBlocking()
                .first();
    }


}
