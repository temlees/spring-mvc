package com.ohgiraffers.understand.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointExceptionHandler(NullPointerException e , Model model) {
        System.out.println("global 레벨의 NullPointerException 처리");
        model.addAttribute("message","빈 값이 존재합니다");
        return "error/errorMessage";
    }
    //DB 제약조건 위반 시 발생 에러
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e , Model model) {
        model.addAttribute("message","db 제약조건 위반 발생!");
        return "error/errorMessage";
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public String sqlSyntaxErrorExceptionHandler(SQLSyntaxErrorException e , Model model) {
        model.addAttribute("message","쿼리작성문이 잘못됨");
        return "error/errorMessage";
    }

    //우리가 만든 에러
    @ExceptionHandler(NotInsertNameException.class)
    public String notInsertNameExceptionHandler(NotInsertNameException e , Model model) {
        model.addAttribute("message","이미 있는 메뉴 이거나 비었음~");
        return "error/errorMessage";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String NoResourceFoundExceptionHandler(NoResourceFoundException e , Model model) {
        model.addAttribute("message", "요청하신 페이지 찾을 수 없습니다");
        return "error/errorMessage";
    }

    //그외 나머지
//    @ExceptionHandler(Exception.class)
//    public String exceptionHandler(Exception e , Model model) {
//        model.addAttribute("message" ,"오류 발생/~~~~~");
//        return "error/errorMessage";
//    }
}
