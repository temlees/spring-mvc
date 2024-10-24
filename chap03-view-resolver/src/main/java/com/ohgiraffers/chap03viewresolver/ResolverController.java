package com.ohgiraffers.chap03viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {

    @GetMapping("string")
    public String stringReturning(Model model) {
        model.addAttribute("sendMessage", "문자열로 뷰 이름 반환");

        return "result";
    }

    @GetMapping("string-redirect")
    public String stringRedirect(){
        //접두사로 redirect: (원하는 경로)를 하면 redirect된다.
        //redirect는
        return "redirect:/";
    }

    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr){
        /*
        * 리다이렉트 시 flash 영역에 담아서 redirect할 수 있다//flash -1회용 세션
        * 자동으로 모델에 추가되기 때문에 requestScope 에서 값을 꺼내면 된다
        * 세션에 임시로 값을 담고 소멸하는 방식이기 때문에 session에 동일한 키값이 존재하지 않는다.
        * */
        rttr.addFlashAttribute("flashMessage1","redirect attr사용해 redirect");
        return "redirect:/";
    }

    /*
    * PRG패턴 (POST/REDIRECT/GET)
    * 서버가 POST로 받은 데이터를 처리한 후 리 다이렉트 응답을 클라이언트에게 보낸다
    * 클라이언트는 리다이렉트된 URL로 GET요청을 보내고 결과를 화면에 표시한다
    * 이렇게 되면 이후 새로고침시에도 GET요청을 보내기 때문에 중복데이터 처리가 발생하지 않는다.
    * */


    /*
    * ModelAndView - spring에서 모델과 뷰를 함께 처리하기 위한 클래스
    * */


    //데이터가 많아질수록 사용하기 좋다
    //modelandview 뷰 설정까지 같이해줄 수 있다
    //model은 데이터만 담을 수 있다
    @GetMapping("modelandview")
    public ModelAndView modelAndView(ModelAndView mv) {
        mv.addObject("sendMessage","modelAndview를 이용한 모델과 뷰 반환");
        mv.setViewName("result");
        return mv;
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv){
        mv.addObject("flashMessage2","test");
        mv.setViewName("redirect:/");
        return mv;
    }

    //리다이렉트 시에도 데이터가 유지되게 보낼수 있다 addFlashAttribute 통해서
    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectAttr(ModelAndView mv, RedirectAttributes rttr){
        rttr.addFlashAttribute("flashMessage2","modelandview를 이용한 attr");
        mv.setViewName("redirect:/");
        return mv;
    }
}
