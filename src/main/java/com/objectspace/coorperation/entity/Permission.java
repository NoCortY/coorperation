package com.objectspace.coorperation.entity;

import java.io.Serializable;
import java.util.Date;

/**
* @Description: 权限实体类
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class Permission implements Serializable {
    private static final long serialVersionUID = -2550095162237578681L;
    private Integer permissionId;
    private String permissionName;
    private String permissionDesc;
    private String permissionUrl;
    private Date createTime;
    private Date lastModifyDate;
    public Integer getPermissionId() {
        return permissionId;
    }
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    public String getPermissionName() {
        return permissionName;
    }
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    public String getPermissionDesc() {
        return permissionDesc;
    }
    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }
    public String getPermissionUrl() {
        return permissionUrl;
    }
    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDesc='" + permissionDesc + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                '}';
    }
}
