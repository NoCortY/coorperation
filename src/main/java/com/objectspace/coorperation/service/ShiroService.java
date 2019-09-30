package com.objectspace.coorperation.service;

import com.objectspace.coorperation.entity.Permission;
import com.objectspace.coorperation.entity.User;

import java.util.Set;

public interface ShiroService {
    public Set<Permission> getPermissionByUserAccount(User user);
    public User getUserByUserAccount(User user);
    //boolean needInterceptor(String requestURI);
}
