package com.objectspace.coorperation.dto;

import com.objectspace.coorperation.entity.User;
import com.objectspace.coorperation.enums.UserStateEnum;

import java.util.List;

/**
 * 用户数据传输类
 * 用来在web层传输用户信息
 * @author Object
 *
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

    public UserExecution() 	{}

    /**
     * 注册失败的构造器
     * @param stateEnum
     */
    public UserExecution(UserStateEnum stateEnum) {
        this.userState = stateEnum;
    }
    /**
     * 注册成功的构造器
     * @param stateEnum
     * @param user
     */
    public UserExecution(UserStateEnum stateEnum,User user) {
        this.userState = stateEnum;
        this.user = user;
    }
    /**
     * 获取用户列表
     * @param stateEnum
     * @param userList
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

