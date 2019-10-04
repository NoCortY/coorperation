package com.objectspace.coorperation.enums;
/**
* @Description: 分类状态枚举
* @Author: NoCortY
* @Date: 2019/10/4
*/
public enum CategoryStateEnum {
    GETCATEGORYLIST_SUCCESS("1001","获取分类列表成功"),
    GETCATEGORY_SUCCESS("1002","获取分类成功"),
    ADDCATEGORY_SUCCESS("1003","新增分类成功"),
    DELCATEGORY_SUCCESS("1005","删除分类成功"),
    MODIFYCATEGORY_SUCCESS("1006","修改分类成功"),
    CATEGORY_ISNULL("-1001","分类为空");
    private String state;
    private String stateInfo;
    private CategoryStateEnum(String state,String stateInfo){
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
