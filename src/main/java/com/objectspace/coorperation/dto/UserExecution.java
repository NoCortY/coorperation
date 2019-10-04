package com.objectspace.coorperation.dto;

import com.objectspace.coorperation.entity.User;
import com.objectspace.coorperation.enums.UserStateEnum;

import java.util.List;

/**
* @Description: DTO类，用于Service层和Web层之间数据的交互
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class UserExecution {
    //用户（增删改时会使用到）
    private User user;
    //用户列表（查询用户列表会使用到）
    private List<User> userList;
    //状态
    private UserStateEnum userState;
    //用户数量
    private Integer userCount;
    /**
     * @Description:  默认构造器
     * @Param:
     * @return:
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public UserExecution() 	{}

    /**
     * @Description: 失败的构造器，注册失败或者登录失败时使用，直接返回一个错误枚举
     * @Param: [stateEnum] 状态枚举
     * @return: DTO对象，里面封装了一个错误信息
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public UserExecution(UserStateEnum stateEnum) {
        this.userState = stateEnum;
    }
    /**
     * @Description: 成功的构造器，登陆成功或者注册成功时使用
     * @Param: [stateEnum, user] 状态枚举，用户对象
     * @return: DTO
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public UserExecution(UserStateEnum stateEnum,User user) {
        this.userState = stateEnum;
        this.user = user;
    }
    /**
     * @Description: 返回用户列表时使用
     * @Param: [stateEnum, userList]
     * @return: 返回Web层的DTO，封装状态码和用户列表
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public UserExecution(UserStateEnum stateEnum,List<User> userList) {
        this.userState = stateEnum;
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public UserStateEnum getUserState() {
        return userState;
    }

    public void setUserState(UserStateEnum userState) {
        this.userState = userState;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

}

