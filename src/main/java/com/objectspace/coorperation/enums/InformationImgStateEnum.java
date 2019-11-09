package com.objectspace.coorperation.enums;

/**
* @Description: 信息详情图枚举状态类
* @Author: NoCortY
* @Date: 2019/11/7
*/
public enum InformationImgStateEnum {
    GETINFORMATIONIMG_SUCCESS("1001","获取信息详情图成功"),
    SAVEINFORMATIONIMG_SUCCESS("1002","保存信息详情图成功"),
    INFORMATIONIMG_ISNULL("-1001","详情图为空"),
    INFORMATIONIMG_SAVEFAILURE("-1002","转存图片失败"),
    INFORMATIONIMG_OPERATORERROR("-1003","操作用户异常"),
    SYSTEM_ISNULL("-1010","内部系统错误");
    private String state;
    private String stateInfo;
    private InformationImgStateEnum(String state,String stateInfo){
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
