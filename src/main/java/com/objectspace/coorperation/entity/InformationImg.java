package com.objectspace.coorperation.entity;

import java.io.Serializable;
import java.util.Date;

/**
* @Description: 供需信息详情图实体类
* @Author: NoCortY
* @Date: 2019/10/17
*/
public class InformationImg implements Serializable {
    private static final long serialVersionUID = -4455451341595008536L;
    private Integer informationImgId;
    /*路径*/
    private String informationImgPath;
    private String informationImgDesc;
    /*图片所属信息*/
    private String informationImgOwner;
    /*图片状态：false，禁用  true 正常*/
    private Boolean informationImgStatus;
    private Date informationImgCreateTime;

    public Integer getInformationImgId() {
        return informationImgId;
    }

    public void setInformationImgId(Integer informationImgId) {
        this.informationImgId = informationImgId;
    }

    public String getInformationImgPath() {
        return informationImgPath;
    }

    public void setInformationImgPath(String informationImgPath) {
        this.informationImgPath = informationImgPath;
    }

    public String getInformationImgDesc() {
        return informationImgDesc;
    }

    public void setInformationImgDesc(String informationImgDesc) {
        this.informationImgDesc = informationImgDesc;
    }

    public String getInformationImgOwner() {
        return informationImgOwner;
    }

    public void setInformationImgOwner(String informationImgOwner) {
        this.informationImgOwner = informationImgOwner;
    }

    public Boolean getInformationImgStatus() {
        return informationImgStatus;
    }

    public void setInformationImgStatus(Boolean informationImgStatus) {
        this.informationImgStatus = informationImgStatus;
    }

    public Date getInformationImgCreateTime() {
        return informationImgCreateTime;
    }

    public void setInformationImgCreateTime(Date informationImgCreateTime) {
        this.informationImgCreateTime = informationImgCreateTime;
    }
}
