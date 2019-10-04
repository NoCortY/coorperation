package com.objectspace.coorperation.entity;

import java.util.Date;
/**
* @Description: 类别 实体类
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class Category {
    private Integer categoryId;
    private String categoryName;
    private String categoryIconCode;
    private Integer parentId;
    private Integer priority;
    private Date createTime;
    private Date lastModifyTime;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIconCode() {
        return categoryIconCode;
    }

    public void setCategoryIconCode(String categoryIconCode) {
        this.categoryIconCode = categoryIconCode;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryIconCode='" + categoryIconCode + '\'' +
                ", parentId=" + parentId +
                ", priority=" + priority +
                '}';
    }
}
