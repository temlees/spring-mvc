package com.ohgiraffers.chap02handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@RequestMapping("/first/*")
@SessionAttributes("id")  //여기 컨트롤로 내부에서만 유지되는 세션
public class FirstController {

    @GetMapping("regist")
    public void regist(){
    }

    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request){
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = name+"을(를) 신규 메뉴의 목록의 "+categoryCode+"번 카테고리에 "+price+"원으로 등록하셨습니다";
        System.out.println(message);
        model.addAttribute("message",message);
        return "first/messagePrinter";
    }

    @GetMapping("modify")
    public void modify(){}


    // required = 파라미터 포함여부 , name =이름 , defaultValue = 기본값
//    @PostMapping("modify")
//    public String modifyMenu(Model model, @RequestParam(required = false ,name = "modifyName") String modifyName,@RequestParam(defaultValue = "0") int modifyPrice){
//        String message = modifyName + "메뉴 가격을 " + modifyPrice+"으로 변경";
//        System.out.println(message);
//        model.addAttribute("message",message);
//        return "first/messagePrinter";
//    }


    //key 는 name속성
    //파라미터는 모두 String 이다
    @PostMapping("modify")
    public String modifyMenu(Model model, @RequestParam Map<String,String>parameters){
        String modifyName = parameters.get("modifyName");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice"));
        String message = modifyName + "메뉴 가격을 " + modifyPrice+"으로 변경";
        System.out.println(message);
       model.addAttribute("message",message);
          return "first/messagePrinter";
    }


    @GetMapping("search")
    public void search(){}

    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu){
        System.out.println(menu);
        return "first/searchResult";
    }

    //4. session 이용하기
    // session 에는 두가지 방법이 존재한다
    @GetMapping("login")
    public void login(){}



    //4-1 httpseseeion을 매개변수로 선언하면 핸들러 메소드 호출시
    // 세션 객체를 호출함
    @PostMapping("login")
    public String sessionTest1(HttpSession session, @RequestParam String id){
        //세션 추가
        session.setAttribute("id",id);
        return "first/loginResult";
    }

    @GetMapping("logout1")
    public String logoutTest1(HttpSession session){
        //세션 만료..
        session.invalidate();
        return "first/login";
    }

    //맨위의 @SessionAttributes("id") 이 아이디와 아래처럼 같으면
    //그 세션을 사용가능
    /*
    *  2번째 방법
    * 클래스 레벨에 @SessionAttributes 어노테이션을 이용하여 세션에 값을 담을 key를
    * 설정해두면, model 영역에 해당하는 key로 값이 추가되는경우 Session에 자동등록한다
    * */
    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id){
        model.addAttribute("id",id);
        return "first/loginResult";

    }

    //SessionAttribute  로 등록된 값은 session의 상태를 관리하는
    //sessionStatus의 setComplete() 메소드를 호출해야 만료된다
    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "first/loginResult";
    }

    @GetMapping("body")
    public void body(){}


    /*
        5. @RequestBody 를 이용하는 방법
            해당 어노테이션은 http 본문 자체를 읽는 부분을
            모델로 변환시켜주는 어노테이션이다
     */

    @PostMapping("body")
    public void bodyTest(@RequestBody String body){
        System.out.println(body);
    }



}
