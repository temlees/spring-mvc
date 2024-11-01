package com.ohgiraffers.chap08securitysesseion.user.controller;


import com.ohgiraffers.chap08securitysesseion.user.dto.SignupDTO;
import com.ohgiraffers.chap08securitysesseion.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("signup")
    public ModelAndView signup(ModelAndView mv) {
        mv.setViewName("user/signup");
        return mv;
    }

// 리다이렉트 해도 잠깐 살이있는 어티리뷰트
    @PostMapping("signup")
    public String signup(SignupDTO signupDTO , RedirectAttributes redirectAttributes) {
        int result = userService.regist(signupDTO);
        System.out.println(result);
        String message;

        if(result >0 ) {
            message ="회원 가입 완료";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/auth/login";
        }else{
            message="회원가입 실패";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/user/signup";

        }
    }
}
