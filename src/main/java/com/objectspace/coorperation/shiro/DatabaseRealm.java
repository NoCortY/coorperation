package com.objectspace.coorperation.shiro;

import com.objectspace.coorperation.entity.Permission;
import com.objectspace.coorperation.entity.User;
import com.objectspace.coorperation.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
/**
* @Description:  Shiro核心：自定义Realm 用于身份认证和权限认证
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class DatabaseRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    /**
     * @Description:  权限认证
     * @Param: [principalCollection]
     * @return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户账户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = new User();
        user.setUserName(userName);
        //从数据库中获取角色和权限
        Set<Permission> permissions = shiroService.getPermissionByUserName(user);
        Set<String> stringPermissions = new HashSet<String>();
        /*
        Exception: null pointer:

        if(permissions == null || permissions.size()<=0) {
            System.out.println("权限列表为空");
            throw new AuthorizationException();
        }*/
        if(permissions!=null) {
            for (Permission p : permissions) {
                stringPermissions.add(p.getPermissionUrl());
            }
        }

        //建立简单授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        //设置权限和角色
        s.setStringPermissions(stringPermissions);
        //授权
        return s;
    }

    /**
     * @Description:  用户认证
     * @Param: [token]
     * @return: org.apache.shiro.authc.AuthenticationInfo
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String userName = t.getUsername();
        User u = new User();
        u.setUserName(userName);
        User user = shiroService.getUserByUserName(u);
        if("-1".equals(user.getUserStatus())) {
            System.out.println("账号:"+user.getUserName()+"已经被禁止登录!");
            throw new DisabledAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt),
                getName());
        return a;
    }
}
