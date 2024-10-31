package com.ohgiraffers.chap08securitysesseion.user.service;

import com.ohgiraffers.chap08securitysesseion.user.dao.UserMapper;
import com.ohgiraffers.chap08securitysesseion.user.dto.LoginUserDTO;
import com.ohgiraffers.chap08securitysesseion.user.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    // Transactional 메소드가 정삭적으로 완료되면 커밋함. 실행중 예외발생시 롤백

    @Transactional
    public int regist(SignupDTO signupDTO) {

       if(signupDTO.getUserId()==null || signupDTO.getUserId().isEmpty()){
           return  0;
       } if(signupDTO.getUserName()==null || signupDTO.getUserName().isEmpty()){
            return  0;
        } if(signupDTO.getUserPass()==null || signupDTO.getUserPass().isEmpty()){
            return  0;
        }
       signupDTO.setUserPass(encoder.encode(signupDTO.getUserPass()));
       int result = userMapper.regist(signupDTO);
       return result;
    }

    public LoginUserDTO findByUserName(String username) {

        LoginUserDTO login = userMapper.findByUserName(username);
        if (Objects.isNull(login)){
            return null;
        }else {
            return login;
        }

    }
}
