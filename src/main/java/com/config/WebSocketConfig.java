package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.util.List;

@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //服务端发送消息给客户端的域,多个用逗号隔开 客户端订阅地址以 topic或user开头
        registry.enableSimpleBroker("/topic","/user");
        //定义一对一推送的时候前缀 其中 以user开头的为指定用户推送
        registry.setUserDestinationPrefix("/user");
        //定义websoket前缀 客服端发消息到服务端的前缀 /app
        registry.setApplicationDestinationPrefixes("/app");
    }
}