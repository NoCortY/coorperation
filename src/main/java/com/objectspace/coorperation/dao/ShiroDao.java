package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.Permission;
import com.objectspace.coorperation.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface ShiroDao {
    public Set<Permission> queryPermissionByUserName(User user);
    public List<Permission> listPermission();
    public User queryUserByUserName(User user);
}