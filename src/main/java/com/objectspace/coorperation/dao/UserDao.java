package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @Description: 用户DAO
* @Author: NoCortY
* @Date: 2019/10/4
*/
@Mapper
public interface UserDao {
    /**
     * @Description: 通过用户名查询用户信息
     * @Param:  user 用户对象
     * @return: User 用户信息
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public User queryUserByUserName(User user);
    /**
     * @Description:  插入一个用户到数据库
     * @Param:  user 用户对象
     * @return: 影响的行数
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public Integer insertUser(User user) throws Exception;
    /**
     * @Description: 更新用户到数据库
     * @Param: user 用户对象
     * @return: 影响的行数
     * @Author: NoCortY
     * @Date: 2019/11/8
     */
    public Integer updateUserByUserId(User user);
}
