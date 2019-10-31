package com.objectspace.coorperation.enums;


public enum InformationImgStateEnum {

    GETINFORMATIONIMG_SUCCESS("1001","获取信息详情图成功"),
    INFORMATIONIMG_ISNULL("-1001","该信息没有详情图"),
    SYSTEM_ISNULL("-1010","内部系统错误");
    private String state;
    private String stateInfo;
    private InformationImgStateEnum(){ }
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
