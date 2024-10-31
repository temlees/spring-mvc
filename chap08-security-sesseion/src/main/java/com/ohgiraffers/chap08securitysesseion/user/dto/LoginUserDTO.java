package com.ohgiraffers.chap08securitysesseion.user.dto;


//로그인 시 시큐리티가 사용할 dto

import com.ohgiraffers.chap08securitysesseion.common.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginUserDTO {

    private int userCode;
    private String userId;
    private String userName;
    private String password;
    private UserRole userRole;

    public LoginUserDTO() {
    }

    public LoginUserDTO(int userCode, String userId, String userName, String password, UserRole userRole) {
        this.userCode = userCode;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    //0보다 크다는 소리는  getRole()호출시 권한이 있다는 걸 말한다
    //List로 넘기는 이유는 SECURITY가 LIST로 달라고 요구를 하기 때문이다..
    public List<String> getRole(){
        if(this.userRole.getRole().length() >0){
            return Arrays.asList(this.userRole.getRole().split(","));
        }
        return new ArrayList<>();
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
