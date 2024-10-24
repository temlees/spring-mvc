package com.ohgiraffers.chap04exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Exception이 발생했을 때 핸들링 해 주는 클래스를만드는 어노테이션
//순서는 지역 예외처리 우선 그 다음 전역 예외처리가 나온다.
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerException(NullPointerException e) {
        System.out.println("글로벌 레벨의 익셉션 처리");
        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String memberRegistException(Model model, MemberRegistException e) {
        System.out.println("글로벌 레벨의 예외처리");
        model.addAttribute("exception", e);
        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        System.out.println("나머지 예외들 처리 ");
        return "error/default";
    }


}
