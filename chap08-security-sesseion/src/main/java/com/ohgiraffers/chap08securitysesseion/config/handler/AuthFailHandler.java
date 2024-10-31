package com.ohgiraffers.chap08securitysesseion.config.handler;


// 사용자의 로그인 실패시 어떤 경우로 실패했는지
//메세지 담아 넘겨주는 핸들러, 요청실패 커스텀 핸들러

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Configuration
public class AuthFailHandler   extends SimpleUrlAuthenticationFailureHandler {

    //이 메소드로인해 실패시 행동을 정한다
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = null;

        if(exception instanceof BadCredentialsException){
            //사용자의 아이디가 db에 존재하지 않는 경우 or 비밀번호 맞이 않을경우
            errorMessage = "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다";

        } else if (exception instanceof InternalAuthenticationServiceException) {
            //서버에서 사용자 정보를 검증하는 과정에서 발생 에러 -- 대부분 코드 잘못
            errorMessage = "서버에서 오류 발생 ~";

        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
           //인증정보 없이 보안처리된 리소스 접근시
            errorMessage ="인증 요청이 거부 되었습니다";

        }else {
            errorMessage="알수없는 에러로인해 로그인 요청 실패";
        }
        errorMessage = URLEncoder.encode(errorMessage,"UTF-8");

        //요청 실패시 보낼곳 지정 -인코딩 후
        setDefaultFailureUrl("/auth/fail?message="+errorMessage);

        //수행 부분만 정의 나머지는 super에서 처리
        //혹시 에러처리 안될ㅒㄷ 원래 제공해주는 시큐리티 방식으로 처리해라
        super.onAuthenticationFailure(request, response, exception);
    }
}
