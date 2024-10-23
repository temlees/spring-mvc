package com.ohgiraffers.chap01requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MethodMappingTestController {

    //Model응답을 위한 객체 만드는게 아니라 넣어줌
    //  @RequestMapping쓰면 get post 둘다 받을 수 있다..
    @RequestMapping("/menu/regist")
    public String registMenu(Model model){
        model.addAttribute("message","신규 메뉴 등록용 핸들러 메소드 호출");

        //Controller 가 있는곳에서 return값이 String 일때
        //html붙여서 원하는 곳으로 보내준다.
        return "mappingResult";
    }

    @RequestMapping(value = "/menu/modify",method = RequestMethod.GET)
    public String modifyMenu(Model model){
        model.addAttribute("message","GET 방식의 메뉴 수정 호출");
        return "mappingResult";
    }

    /*
    * 요청 메소드 전용 어노테이션
    * 요청 메소드            어노테이션
    *   post             @PostMapping//수정 삭제 등등
    *   get              @GetMapping//단순 조회는 보통 get
    *   Put              @PutMapping //수정 리소스의 모든 것을 업데이트
    *   Delete           @DeleteMapping
    *   Patch            @PatchMapping // 수정 리소스의 일부 업데이트
    * */

    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model){
        model.addAttribute("message","Get 방식 메뉴 삭제 요청");
        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model){
        model.addAttribute("message","Post 방식 메뉴 삭제 요청");
        return "mappingResult";

    }
}
