package com.ohgiraffers.chap05interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class InterceptorTestController {

    @Autowired
    private InterceptorService interceptorService;


    @PostMapping("stopwatch")
    public String handlerMethod(Model model) throws InterruptedException {
        // addAttribute : 객체에 데이터를 추가하고 이를 뷰에서 사용할 수 있게 됩니다.
       model.addAttribute("test","모델테스트");
        System.out.println("핸들러 메소드 호출함");
        interceptorService.method();
        Thread.sleep(1000);//프로그램 1초 대기
        return "result";
    }
}
