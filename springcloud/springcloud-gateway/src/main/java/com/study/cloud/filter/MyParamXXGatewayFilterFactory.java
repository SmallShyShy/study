package com.study.cloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;

/**
 * @author zjl
 * @date 2020/07/23 14:01
 */
@Component
public class MyParamXXGatewayFilterFactory extends AbstractGatewayFilterFactory<MyParamXXGatewayFilterFactory.Config> {
    //与config中param一致
    static final String PARAM_NAME="param";
    public MyParamXXGatewayFilterFactory(){
        super(Config.class);
    }
    @Override
    public List<String> shortcutFieldOrder(){
        return Arrays.asList(PARAM_NAME);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{
            ServerHttpRequest request = exchange.getRequest();
            if(request.getQueryParams().containsKey(config.param)){
                request.getQueryParams().get(config.param).forEach(value->{
                    System.out.println("局部过滤器参数名称："+config.param+",value="+value);
                });
            }
            return  chain.filter(exchange);
        };
    }




    static class Config{
        private String param;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }
}
