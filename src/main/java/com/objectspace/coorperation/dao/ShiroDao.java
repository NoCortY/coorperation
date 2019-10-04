package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.Permission;
import com.objectspace.coorperation.entity.UrlFilter;
import com.objectspace.coorperation.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/** 
* @Description: Shiro框架所需的数据库DAO
* @Author: NoCortY
* @Date: 2019/10/4
*/
@Mapper
public interface ShiroDao {
    /** 
     * @Description:  查询某个用户拥有的所有权限
     * @Param: user 用户对象 
     * @return: 该用户匹配的所有权限信息
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public Set<Permission> queryPermissionByUserName(User user);
    /** 
     * @Description:  查询权限表中的所有权限
     * @Param:  
     * @return: 权限列表
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public List<Permission> listPermission();
    /** 
     * @Description: 根据用户名查询用户信息
     * @Param:  user 用户
     * @return: 用户
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public User queryUserByUserName(User user);
    /**
     * @Description:  查询所有url和拦截器的对应信息
     * @Param:
     * @return: 拦截器和url信息
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public List<UrlFilter> listUrlFilter();
}