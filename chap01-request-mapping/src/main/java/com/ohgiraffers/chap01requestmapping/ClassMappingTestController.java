package com.ohgiraffers.chap01requestmapping;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

    @GetMapping("/regist")
    public String registOrder(Model model) {
        model.addAttribute("message","get방식의 주문 등록용 핸들러 메소드");

        return "mappingResult";
    }

    //RequestMapping은 두개받을 수 있다
    @RequestMapping(value = {"modify","delete"},method = RequestMethod.POST)
    public String modifyOrder(Model model) {
        model.addAttribute("message","post 방식의 주문 정보 수정 ");
        return "mappingResult";
    }

    /*
    *   PathVariable
    * @PathVariable 어노테이션을 통해 변수를 받아올 수 있다
    * path variable 로 전달되는 {변수명}은 반드시 매개변수명과 동일해야한다
    * 만약 동일하지 않으면 @PathVariable("이름")을 설정해주어야한다
    * */

    //{orderNo} 우리가 만든 변수명
    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo) {
        model.addAttribute("message",orderNo+"번 주문 상세내용 조회용 핸들러 호출");
        return "mappingResult";
    }

    @RequestMapping
    public String otherRequest(Model model) {
        model.addAttribute("message", "order 요청이긴 하지만 다른 기능이 준비 되지 않음");
        return "mappingResult";
    }


}
