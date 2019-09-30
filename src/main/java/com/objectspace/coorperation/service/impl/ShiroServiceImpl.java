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

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    ShiroDao shiroDao;
    Logger logger = LoggerFactory.getLogger(ShiroServiceImpl.class);
    @Override
    public Set<Permission> getPermissionByUserAccount(User user) {
        if(user==null || "".equals(user.getUserId()))
            return null;
        Set<Permission> permissions = shiroDao.queryPermissionByUserAccount(user);
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
    @Override
    public User getUserByUserAccount(User user) {
        // TODO Auto-generated method stub
        if(user == null || "".equals(user.getUserName())){
            logger.debug("权限验证：用户账户不能为空");
        }
        User result = shiroDao.queryUserByUserAccount(user);
        return result;
    }

}

