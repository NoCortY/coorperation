package com.objectspace.coorperation.service;

import com.objectspace.coorperation.dto.UserMenuExecution;

/**
* @Description: 用户菜单业务逻辑——接口
* @Author: NoCortY
* @Date: 2019/10/5
*/
public interface UserMenuService {
    /**
     * @Description: 获取所有用户菜单
     * @Param:
     * @return: UserMenuExecution
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    public UserMenuExecution listUserMenu();
}
