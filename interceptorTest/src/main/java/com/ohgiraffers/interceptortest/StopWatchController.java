package com.ohgiraffers.interceptortest;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StopWatchController {

    @PostMapping("stopwatch")
    public String stopwatch() {


        return "result";
    }
}
