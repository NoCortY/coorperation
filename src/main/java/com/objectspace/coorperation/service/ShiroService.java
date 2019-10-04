package com.objectspace.coorperation.service;

import com.objectspace.coorperation.entity.Permission;
import com.objectspace.coorperation.entity.User;

import java.util.Set;
/**
* @Description: Shiro框架业务逻辑接口
* @Author: NoCortY
* @Date: 2019/10/4
*/
public interface ShiroService {
    /**
     * @Description:  通过用户名查找用户的所有权限
     * @Param: user 用户对象
     * @return: 用户权限集合
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public Set<Permission> getPermissionByUserName(User user);
    /**
     * @Description:  通过用户名查询用户信息
     * @Param:  用户对象
     * @return: 用户信息
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public User getUserByUserName(User user);
    //boolean needInterceptor(String requestURI);
}
