package com.ohgiraffers.chap08securitysesseion.auth.service;

import com.ohgiraffers.chap08securitysesseion.auth.model.AuthDetail;
import com.ohgiraffers.chap08securitysesseion.user.dto.LoginUserDTO;
import com.ohgiraffers.chap08securitysesseion.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;

    //로그인 요청시 security가 전달된 사용자 id를
    //매개변수로 db에서 사용자의 정보를 찾는다
    //구현 해 주어야한다
    //전달된 사용자의 갹체 타입은 userDetail를 구현한 구현체가 되어야한다.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserDTO login = userService.findByUserName(username);

        if(Objects.isNull(login)){
            throw new UsernameNotFoundException("회원정보 존재 x");
        }
        return new AuthDetail(login);
    }
}
