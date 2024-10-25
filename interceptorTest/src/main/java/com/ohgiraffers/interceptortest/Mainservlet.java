package com.ohgiraffers.interceptortest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Mainservlet {

    @RequestMapping(value = {"/","/main"})
    public String main() {
        return "main";
    }

}
