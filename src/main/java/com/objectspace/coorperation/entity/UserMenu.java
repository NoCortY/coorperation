package com.objectspace.coorperation.entity;

import java.io.Serializable;
import java.util.Date;

/**
* @Description: 用户菜单配置实体类
* @Author: NoCortY
* @Date: 2019/10/5
*/
public class UserMenu implements Serializable {

    private static final long serialVersionUID = -1866627179438315255L;
    private Integer menuItemId;
    private String munuItemName;
    private String menuItemIconCode;
    private Integer parentId;
    private Boolean isNew;
    private Integer priority;
    private Date createTime;
    private Date lastModifyTime;

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMunuItemName() {
        return munuItemName;
    }

    public void setMunuItemName(String munuItemName) {
        this.munuItemName = munuItemName;
    }

    public String getMenuItemIconCode() {
        return menuItemIconCode;
    }

    public void setMenuItemIconCode(String menuItemIconCode) {
        this.menuItemIconCode = menuItemIconCode;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        return "UserMenu{" +
                "menuItemId=" + menuItemId +
                ", munuItemName='" + munuItemName + '\'' +
                ", menuItemIconCode='" + menuItemIconCode + '\'' +
                ", parentId=" + parentId +
                ", isNew=" + isNew +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
