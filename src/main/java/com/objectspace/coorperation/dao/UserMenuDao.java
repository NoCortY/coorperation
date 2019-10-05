package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.UserMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @Description: 用户菜单DAO
* @Author: NoCortY
* @Date: 2019/10/5
*/
@Mapper
public interface UserMenuDao {
    /**
     * @Description: 获取所有用户菜单信息
     * @Param:
     * @return: [List]
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    public List<UserMenu> getAllUserMenu();
}
