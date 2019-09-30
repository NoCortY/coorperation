package com.objectspace.coorperation.entity;

import java.io.Serializable;
/**
* @Description:  用户实体类
* @Author: Object
* @Date: 2019/9/30
*/
public class User implements Serializable {
    private static final long serialVersionUID = 3269376802709252095L;
    private Integer userId;
    private String userName;
    private String password;
    private String userEmail;
    private String userPhoneNumber;
    private String realName;
    private String profileImg;
    private String userType;
    private Boolean userStatus;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }


    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", realName='" + realName + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", userType='" + userType + '\'' +
                ", userStatus=" + userStatus +
                ", salt='" + salt + '\'' +
                '}';
    }
}