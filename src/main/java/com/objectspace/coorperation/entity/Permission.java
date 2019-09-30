package com.objectspace.coorperation.entity;

import java.io.Serializable;

public class Permission implements Serializable {
    private static final long serialVersionUID = -2550095162237578681L;
    private Integer permissionId;
    private String permissionName;
    private String permissionDesc;
    private String permissionUrl;
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
