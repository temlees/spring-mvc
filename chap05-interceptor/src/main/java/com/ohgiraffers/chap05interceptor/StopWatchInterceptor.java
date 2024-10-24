package com.ohgiraffers.chap05interceptor;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/*
*   default 메소드가 존재하기 이전에는 모두 오버라이딩을
* 해야하는 책임을 가지기 때문에 JHANDLERInterceptorAdaptor
* 를 이용해야 했으나 default 메소드가 인터페이스에 사용 가능하게 된
* java 8 버전 이후에는 인터페이스에만 구현하여
* 필요한 메소드만 오버라이딩 해서 사용할 수 있다.
* */
@Component
public class StopWatchInterceptor implements HandlerInterceptor {


    //전처리 메소드 - 지정된 컨트롤러의 동작 이전에 수행할 내용
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getParameter("auth").equals("admin")) {
            response.sendRedirect("/");  //루트경로 redorect
            return  false;
        }
        System.out.println("preHandle 호출됨..");
        long startTime = System.currentTimeMillis();// 요청처리 시작시간 기록
        request.setAttribute("startTime", startTime);
        //컨트롤러를 이어서 호출한다
        return true;
    }

    //후처리 메소드 -- 지정된 컨트롤러 동작 이후 처리할 동작 제어
    //실행이 완료 되었지만 아직 view가 생성되기 전에 호출
    //디스패치 서블릿이 화면을 띄우기전에 동작함
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("모델 확인용"+modelAndView.getModelMap());
        System.out.println("post  핸들러 호출");
        long startTime =(long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        modelAndView.addObject("interval", endTime - startTime);
    }


    //모든 요청 처리가 완료 된 후에 실행되는 메소드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 호출...");
    }
}
