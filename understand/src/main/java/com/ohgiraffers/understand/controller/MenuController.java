package com.ohgiraffers.understand.controller;

import com.ohgiraffers.understand.dto.MenuDTO;
import com.ohgiraffers.understand.exception.NotInsertNameException;
import com.ohgiraffers.understand.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/menus/*")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("menus")
    public ModelAndView selectMenus(ModelAndView mv){
        List<MenuDTO> menus = menuService.selectAllMenu();
        if (Objects.isNull(menus)) {
            throw new NullPointerException();
        }
        mv.addObject("menus", menus);
        mv.setViewName("menus/allMenus");
        return mv;
    }

    @GetMapping("onemenu")
    public  ModelAndView oneMenu(ModelAndView mv){
        mv.setViewName("menus/onemenu");
        return mv;
    }

    @GetMapping("onemenuaction")
    public ModelAndView selectOneMenu( ModelAndView mv,  MenuDTO menuDTO){
        int code =menuDTO.getCode();
        System.out.println(code);
        MenuDTO menus = menuService.selectOneMenu(code);

        if (Objects.isNull(menus)) {
            throw new NullPointerException();
        }
        mv.addObject("menus", menus);
        mv.setViewName("menus/allMenus");
        return mv;
    }

    @GetMapping("regist")
    public ModelAndView insert(ModelAndView mv){
        mv.setViewName("menus/regist");
        return mv;
    }

    @PostMapping("regist")
    public ModelAndView insertMenu(ModelAndView mv, MenuDTO menuDTO) throws NotInsertNameException {
        int regist = menuService.regist(menuDTO);

        if (regist<=0){
            mv.addObject("message","가격 음수 안대");
            mv.setViewName("/error/errorMessage");
        }else {

            mv.setViewName("/menus/returnMessage");
        }


        return mv;
    }

    @GetMapping("update")
    public ModelAndView update(ModelAndView mv){
        mv.setViewName("menus/update");
        return mv;
    }

    @PostMapping("update")
    public ModelAndView updateMenu(ModelAndView mv,
                                   @RequestParam int code,
                                   @RequestParam(defaultValue = " ") String name,
                                   @RequestParam(defaultValue = "0")int price,
                                   @RequestParam(defaultValue = "0") int categoryCode) throws NotInsertNameException {


       MenuDTO menuDTO = new MenuDTO();
       menuDTO.setCode(code);
       menuDTO.setName(name);
       menuDTO.setPrice(price);
       menuDTO.setCategoryCode(categoryCode);
        int update = menuService.update(menuDTO);

        if (update<=0){
            mv.addObject("message","업데이트 실패");
            mv.setViewName("/error/errorMessage");
        }else {

            mv.setViewName("/menus/returnMessage");
        }
        return mv;
    }

    @GetMapping("delete")
    public ModelAndView delete(ModelAndView mv){
        mv.setViewName("menus/delete");
        return mv;
    }

    @PostMapping("delete")
    public ModelAndView code(ModelAndView mv , MenuDTO menuDTO) throws NotInsertNameException {
        int delete = menuService.delete(menuDTO);
        if (delete<=0){
            mv.addObject("message","삭 실패");
            mv.setViewName("/error/errorMessage");
        }else {

            mv.setViewName("/menus/returnMessage");
        }
        return mv;
        }
}
