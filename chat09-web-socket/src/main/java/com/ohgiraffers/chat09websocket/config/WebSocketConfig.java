package com.ohgiraffers.chat09websocket.config;


import com.ohgiraffers.chat09websocket.server.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    //구현시 알아서 스프링이
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //websocket 핸들러를 등록하는 메소드
        //어떤 요청이 왓을때 server 패키지의 핸들러 사용하겠다

        //.setAllowedOrigins("*");
        //모든 출처에서 등록하겠다는 뜻
        registry.addHandler(new ChatWebSocketHandler(),
                "/chattingServer").setAllowedOrigins("*");
    }
}
