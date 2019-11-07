package com.objectspace.coorperation.entity;

import java.io.Serializable;
import java.util.Date;
/**
* @Description: 供需信息实体类
* @Author: NoCortY
* @Date: 2019/10/17
*/
public class Information implements Serializable {
    private static final long serialVersionUID = 1241000579390329082L;
    private Integer informationId;
    private String informationName;
    private String informationDesc;
    /*缩略图*/
    private String informationImg;
    /*供需类型：1供应，0需求*/
    private Integer informationType;
    /*信息状态：true正常，false不正常*/
    private Boolean informationStatus;
    /*发布者*/
    private User informationOwner;
    /*信息类型*/
    private Integer informationCategory;
    private Date informationCreateTime;
    private Date informationModifyTime;
    /*优先级*/
    private Integer informationPriority;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public String getInformationDesc() {
        return informationDesc;
    }

    public void setInformationDesc(String informationDesc) {
        this.informationDesc = informationDesc;
    }

    public String getInformationImg() {
        return informationImg;
    }

    public void setInformationImg(String informationImg) {
        this.informationImg = informationImg;
    }

    public Integer getInformationType() {
        return informationType;
    }

    public void setInformationType(Integer informationType) {
        this.informationType = informationType;
    }

    public Boolean getInformationStatus() {
        return informationStatus;
    }

    public void setInformationStatus(Boolean informationStatus) {
        this.informationStatus = informationStatus;
    }

    public User getInformationOwner() {
        return informationOwner;
    }

    public void setInformationOwner(User informationOwner) {
        this.informationOwner = informationOwner;
    }

    public Integer getInformationCategory() {
        return informationCategory;
    }

    public void setInformationCategory(Integer informationCategory) {
        this.informationCategory = informationCategory;
    }

    public Date getInformationCreateTime() {
        return informationCreateTime;
    }

    public void setInformationCreateTime(Date informationCreateTime) {
        this.informationCreateTime = informationCreateTime;
    }

    public Date getInformationModifyTime() {
        return informationModifyTime;
    }

    public void setInformationModifyTime(Date informationModifyTime) {
        this.informationModifyTime = informationModifyTime;
    }

    public Integer getInformationPriority() {
        return informationPriority;
    }

    public void setInformationPriority(Integer informationPriority) {
        this.informationPriority = informationPriority;
    }
}
