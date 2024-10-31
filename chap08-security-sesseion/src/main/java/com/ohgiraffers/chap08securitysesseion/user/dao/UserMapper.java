package com.ohgiraffers.chap08securitysesseion.user.dao;

import com.ohgiraffers.chap08securitysesseion.user.dto.LoginUserDTO;
import com.ohgiraffers.chap08securitysesseion.user.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {


    int regist(SignupDTO signupDTO);

    LoginUserDTO findByUserName(String username);
}
