package com.objectspace.coorperation.enums;

public enum InformationStateEnum {
    GETINFORMATION_SUCCESS("1001","获取信息成功"),
    SAVEINFORMATION_SUCCESS("1002","发布信息成功"),
    UPDATEINFORMATION_SUCCESS("1003","更新信息成功"),
    INFORMATIONIMG_ERROR("-1001","缩略图存储失败"),
    INFORMATIONIMG_FORMATERROR("-1002","缩略图格式错误"),
    INFORMATION_ISNULL("-1003","信息为空"),
    INNERSYSTEM_ERROR("-1010","系统错误");
    private String state;
    private String stateInfo;
    private InformationStateEnum(String state, String stateInfo) {
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
