package com.ohgiraffers.chat09websocket.server;


import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {

   // 웹소켓 연결을 통해 접속한 사용자들의 세션을 관리하는 코드입니다. 다음은 각 부분의 역할에 대한 설명입니다:
    //clients Set은 웹소켓 서버에 연결된 모든 사용자 세션을 보관하고 관리합니다.
    //synchronizedSet 동기화셋 안전하게 ㅓ리
    private static Set<WebSocketSession> clients = Collections.synchronizedSet(new HashSet<>());


    //4가지 오버라이드한다

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //클라이언트가 websocket연결을 성공적으로 수행되었을때 호출되는 메소드이다
        //사용자가 들어올때
        //소켓연결 성공할때마다 생기는 세션의 id
        clients.add(session);
        System.out.println("웹소켓연결 : "+session.getId());
        // 웹소켓연결 : eef002a7-d2bf-f2d4-1134-ba4160becf08
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //클라이언트로부터 텍스트 메세지를 수신했을때 호출되는 메소드
        //누구라도 메세지 보내면 열로 온다
        //message.getPayload() 본문
        //db 저장로직 여기서 한다

        System.out.println("메세지 출력 :" +session.getId()+ " : "+ message.getPayload());

        //synchronized 안전을 위해 처리 getPayload:본문내용
        synchronized (clients) {
            for (WebSocketSession client : clients) {
                if (!client.equals(session)) {
                    //메세지를 자기 자신을 제외하고 전송
                    client.sendMessage(new TextMessage(message.getPayload()));
                }
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        //통신중 특정 클라이언트 에러가 발생할때 메소드
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
