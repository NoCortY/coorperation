package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public User queryUserByUserAccount(User user);
    public Integer insertUser(User user) throws Exception;
}
