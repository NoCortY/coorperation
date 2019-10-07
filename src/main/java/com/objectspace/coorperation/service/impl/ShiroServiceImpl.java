package com.objectspace.coorperation.service.impl;

import com.objectspace.coorperation.dao.ShiroDao;
import com.objectspace.coorperation.entity.Permission;
import com.objectspace.coorperation.entity.User;
import com.objectspace.coorperation.service.ShiroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
* @Description: Shiro框架业务逻辑实现类
* @Author: NoCortY
* @Date: 2019/10/4
*/
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    ShiroDao shiroDao;
    Logger logger = LoggerFactory.getLogger(ShiroServiceImpl.class);
    /**
     * @Description: 使用用户名获取该用户下的所有权限
     * @Param: [user]
     * @return: java.util.Set<com.objectspace.coorperation.entity.Permission>
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Override
    public Set<Permission> getPermissionByUserName(User user) {
        if(user==null || "".equals(user.getUserName()))
            return null;
        Set<Permission> permissions = shiroDao.queryPermissionByUserName(user);
        return permissions;
    }
    /*@Override
    public boolean needInterceptor(String requestURI) {
        // TODO Auto-generated method stub
        List<Permission> permissionList = shiroDao.listPermission();
        for(Permission p : permissionList) {
            if(p.getPermissionUrl().equals(requestURI)) {
                return true;
            }
        }
        return false;
    }*/
    /**
     * @Description: 通过用户名获取用户信息
     * @Param: [user]
     * @return: com.objectspace.coorperation.entity.User
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Override
    public User getUserByUserName(User user) {
        // TODO Auto-generated method stub
        if(user == null || "".equals(user.getUserName())){
            logger.debug("权限验证：用户账户不能为空");
        }
        User result = shiroDao.queryUserByUserName(user);
        return result;
    }

}

