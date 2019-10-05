package com.objectspace.coorperation.dto;

import com.objectspace.coorperation.entity.Category;
import com.objectspace.coorperation.enums.CategoryStateEnum;

import java.util.List;

/**
* @Description: 分类DTO类，用于Service层和Web层的数据交互
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class CategoryExecution {
    private Category category;
    private List<Category> categoryList;
    private CategoryStateEnum categoryStateEnum;
    private Integer categoryCount;
    /**
     * @Description: 失败的构造器，只封装错误状态
     * @Param: [categoryStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public CategoryExecution(CategoryStateEnum categoryStateEnum){
       this.categoryStateEnum = categoryStateEnum;
    }
    /**
     * @Description: 成功的构造器，封装分类信息和分类状态枚举
     * @Param: [category, categoryStateEnum] 
     * @return:
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    public CategoryExecution(Category category,CategoryStateEnum categoryStateEnum){
        this.category = category;
        this.categoryStateEnum = categoryStateEnum;
    }
    public CategoryExecution(List<Category> categoryList,CategoryStateEnum categoryStateEnum){
        /** 
         * @Description:  成功的构造器，封装分类列表信息
         * @Param: [categoryList, categoryStateEnum]
         * @return: 
         * @Author: NoCortY
         * @Date: 2019/10/5
         */
        this.categoryList = categoryList;
        this.categoryStateEnum = categoryStateEnum;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryStateEnum getCategoryStateEnum() {
        return categoryStateEnum;
    }

    public void setCategoryStateEnum(CategoryStateEnum categoryStateEnum) {
        this.categoryStateEnum = categoryStateEnum;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }
}
