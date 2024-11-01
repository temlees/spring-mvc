package com.ohgiraffers.chap10websocketreact.config;


import com.ohgiraffers.chap10websocketreact.handler.ChatWebsocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket // WebSocket 관련 기능이 자동으로 구성되도록 합니다.
public class WebSocketConfig  implements WebSocketConfigurer {



    @Bean
    public ChatWebsocketHandler chatWebsocketHandler(){
        return  new ChatWebsocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebsocketHandler(),
                "/chattingServer").setAllowedOrigins("*");
    }
}
