package com.objectspace.coorperation.dto;

import com.objectspace.coorperation.entity.UserMenu;
import com.objectspace.coorperation.enums.UserMenuStateEnum;

import java.util.List;
/**
* @Description: 用户菜单DTO类，用户Service层和Web层之间的数据交互
* @Author: NoCortY
* @Date: 2019/10/5
*/
public class UserMenuExecution {
    private UserMenu userMenu;
    private List<UserMenu> userMenuList;
    private UserMenuStateEnum userMenuStateEnum;
    /**
     * @Description: 失败的构造器，封装用户菜单状态
     * @Param: [userMenuStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    public UserMenuExecution(UserMenuStateEnum userMenuStateEnum){
        this.userMenuStateEnum = userMenuStateEnum;
    }
    /**
     * @Description:  成功的构造器，封装单个用户菜单和用户菜单状态
     * @Param: [userMenu, userMenuStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    public UserMenuExecution(UserMenu userMenu,UserMenuStateEnum userMenuStateEnum){

        this.userMenu = userMenu;
        this.userMenuStateEnum = userMenuStateEnum;
    }
    /**
     * @Description:  成功的构造器，封装用户菜单列表和用户状态枚举
     * @Param: [userMenuList, userMenuStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    public UserMenuExecution(List<UserMenu> userMenuList,UserMenuStateEnum userMenuStateEnum){

        this.userMenuList = userMenuList;
        this.userMenuStateEnum = userMenuStateEnum;
    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    public void setUserMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
    }

    public List<UserMenu> getUserMenuList() {
        return userMenuList;
    }

    public void setUserMenuList(List<UserMenu> userMenuList) {
        this.userMenuList = userMenuList;
    }

    public UserMenuStateEnum getUserMenuStateEnum() {
        return userMenuStateEnum;
    }

    public void setUserMenuStateEnum(UserMenuStateEnum userMenuStateEnum) {
        this.userMenuStateEnum = userMenuStateEnum;
    }
}
