package com.ohgiraffers.chap10websocketreact.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.chap10websocketreact.dto.Message;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class ChatWebsocketHandler extends TextWebSocketHandler {
    //세션 관리 set
    private static Set<WebSocketSession> clients = Collections.synchronizedSet(new HashSet<>());
    //json으로 넘어오는 데이터를 매핑시키기위한 객체
    private static ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        clients.add(session);
        System.out.println("웹소켓 연결 : "+session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //메세지 받을때
        System.out.println("메세지 전송 : "+session.getId() + " : "+message.getPayload());
        String payload = message.getPayload();
        //키벨류로 넘어오는 getPayload를 dto와 매핑

        Message msg = objectMapper.readValue(payload, Message.class);

        //이 msg dto에 담겨있다

        synchronized (clients){
            for (WebSocketSession client : clients){
                if (!client.equals(session)){
                    if("join".equals(msg.getType())){
                        client.sendMessage(new TextMessage(msg.getUserId()+"님이 입장"));
                    } else if ("leave".equals(msg.getType())){
                        client.sendMessage(new TextMessage(msg.getUserId()+"님이 퇴장"));
                    } else if ("message".equals(msg.getType())) {
                        client.sendMessage(new TextMessage(msg.getUserId()+" : "+msg.getMessage()));
                    }
                }
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("에러발생 : " +session.getId());
        //에러 내역
        exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //클라이언트가 웹소켓 연결을 닫았을때 호출되는 메소드
        //채팅방 안닫고싶으면 안써주면 된다
        //채팅방 끌때 db 저장로직 추가해주자
        clients.remove(session);
        System.out.println("웹소켓 종료 : "+session.getId());
    }
}
