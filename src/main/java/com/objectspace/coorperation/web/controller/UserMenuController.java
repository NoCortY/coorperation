package com.objectspace.coorperation.web.controller;

import com.objectspace.coorperation.config.ConstantValue;
import com.objectspace.coorperation.dto.UserMenuExecution;
import com.objectspace.coorperation.entity.UserMenu;
import com.objectspace.coorperation.enums.UserMenuStateEnum;
import com.objectspace.coorperation.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: 用户菜单控制器
* @Author: NoCortY
* @Date: 2019/10/5
*/
@Controller
@RequestMapping("/usermenucontroller")
public class UserMenuController {
    @Autowired
    private UserMenuService userMenuService;
    @RequestMapping(value = "/getusermenulist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserMenuList(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        UserMenuExecution userMenuExecution = userMenuService.listUserMenu();
        List<UserMenu> userMenuList = userMenuExecution.getUserMenuList();
        if(!userMenuExecution.getUserMenuStateEnum().getState().equals(UserMenuStateEnum.GETUSERMENULIST_SUCCESS.getState())){
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,false);
            modelMap.put(ConstantValue.TO_FRONTEND_MESSAGE,userMenuExecution.getUserMenuStateEnum().getStateInfo());
        }else{
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,true);
            modelMap.put(ConstantValue.TO_FRONTEND_MESSAGE,userMenuExecution.getUserMenuStateEnum().getStateInfo());
            modelMap.put("userMenuList",userMenuList);
        }
        return modelMap;
    }
}
