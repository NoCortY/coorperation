package com.objectspace.coorperation.enums;
/**
* @Description: 用户菜单状态枚举
* @Author: NoCortY
* @Date: 2019/10/5
*/
public enum UserMenuStateEnum {
    GETUSERMENULIST_SUCCESS("1001","获取用户菜单列表成功"),
    GETUSERMENU_SUCCESS("1002","获取用户菜单成功"),
    ADDUSERMENU_SUCCESS("1003","新增用户菜单成功"),
    DELUSERMENU_SUCCESS("1004","删除用户菜单成功"),
    UPDATEUSERMENU_SUCCESS("1005","修改用户菜单成功"),
    USERMENULIST_ISNULL("-1001","用户菜单列表为空"),
    SYSTEM_ERROR("-1010","系统内部错误");
    private String state;
    private String stateInfo;
    private UserMenuStateEnum(){}
    private UserMenuStateEnum(String state,String stateInfo){
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
