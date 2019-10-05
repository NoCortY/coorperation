package com.objectspace.coorperation.service.impl;

import com.objectspace.coorperation.dao.UserMenuDao;
import com.objectspace.coorperation.dto.UserMenuExecution;
import com.objectspace.coorperation.entity.UserMenu;
import com.objectspace.coorperation.enums.UserMenuStateEnum;
import com.objectspace.coorperation.service.UserMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: 用户菜单业务逻辑——实现类
* @Author: NoCortY
* @Date: 2019/10/5
*/
@Service
public class UserMenuServiceImpl implements UserMenuService {
    @Autowired
    UserMenuDao userMenuDao;
    Logger logger = LoggerFactory.getLogger(UserMenuServiceImpl.class);
    /**
     * @Description:  获取所有用户菜单
     * @Param: []
     * @return: java.util.List<com.objectspace.coorperation.entity.UserMenu>
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    @Override
    public UserMenuExecution listUserMenu() {
        UserMenuExecution userMenuExecution = null;
        List<UserMenu> userMenuList = null;
        try {
            userMenuList = userMenuDao.getAllUserMenu();
        }catch (Exception e){
            logger.error("获取用户菜单列表异常!");
            logger.error("异常信息:"+e.getMessage());
            userMenuExecution = new UserMenuExecution(UserMenuStateEnum.SYSTEM_ERROR);
            return userMenuExecution;
        }
        if(userMenuList==null||userMenuList.size()<=0){
            userMenuExecution = new UserMenuExecution(UserMenuStateEnum.USERMENULIST_ISNULL);
            logger.info("用户菜单列表为空");
            return userMenuExecution;
        }else{
            userMenuExecution = new UserMenuExecution(userMenuList,UserMenuStateEnum.GETUSERMENULIST_SUCCESS);
            logger.info("获取用户菜单列表成功!");
            logger.info("用户菜单列表："+userMenuList);
        }
        return userMenuExecution;
    }
}
