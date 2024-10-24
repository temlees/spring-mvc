package com.ohgiraffers.chap04exception;


public class MemberRegistException extends Exception {
//예외 만들려면 상속 받아야한다

//생성자에서 두번째인 message선택
    public MemberRegistException(String message) {
        super(message);
    }

}
