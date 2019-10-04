package com.objectspace.coorperation.enums;

/**
* @Description: 用户状态枚举
* @Author: NoCortY
* @Date: 2019/10/4
*/
public enum UserStateEnum {
    REGISTERSUCCESS("1001","注册成功"),
    LOGINSUCCESS("1002","登录成功"),
    BINDSUCCESS("1003","禁用成功"),
    QUERYUSERINFOSUCCESS("1005","用户信息查询成功"),
    USERACCOUNTREPEAT("-1001","账号重复"),
    VIRIFYCODEERROR("-1002","验证码错误"),
    ISUSERNULL("-1003","用户信息为空"),
    UPLOADFALURE("-1005","头像上传失败"),
    LOGINFAILURE("-1006","认证失败"),
    USERPHONEREPEAT("-1007","手机号重复"),
    USERISBINDED("-1008","用户已被封禁"),
    SYSTEMERROR("-1010","系统错误");
    private String state;
    private String stateInfo;
    private UserStateEnum(String state,String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getStateInfo() {
        return stateInfo;
    }
    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}

