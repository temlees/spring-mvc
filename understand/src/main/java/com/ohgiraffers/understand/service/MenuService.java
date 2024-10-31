package com.ohgiraffers.understand.service;

import com.ohgiraffers.understand.dto.MenuDTO;
import com.ohgiraffers.understand.exception.NotInsertNameException;
import com.ohgiraffers.understand.model.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MenuService {

    @Autowired
    private MenuDAO menuDAO;

    public List<MenuDTO> selectAllMenu() {
        List<MenuDTO> menus = menuDAO.selectAllMenu();
        return menus;
    }

    public MenuDTO selectOneMenu(int code) {
        MenuDTO menus = menuDAO.selectOneMenu(code);
        return menus;
    }

    public int regist(MenuDTO menuDTO) throws NotInsertNameException {
        List<MenuDTO> menus = menuDAO.selectAllMenu();
        //이름만 불러오는 요청이 더 좋다...
        List<String> names = new ArrayList<>();

        for (MenuDTO menu : menus) {
            names.add(menu.getName());
        }
        if (names.contains(menuDTO.getName()) || menuDTO.getName().isEmpty()) {
            throw new NotInsertNameException("");
        }

        if (menuDTO.getPrice() <=0){
            return 0;
        }

        int result = menuDAO.regist(menuDTO);
        return result;
    }

    public int update(MenuDTO menuDTO) {

        int menu = menuDAO.updateMenu(menuDTO);

        return menu;

    }

    public int delete(MenuDTO menuDTO) {
        int menu = menuDAO.delete(menuDTO);

        return menu;
    }
}
