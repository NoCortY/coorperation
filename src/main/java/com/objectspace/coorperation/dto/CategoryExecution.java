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
    public CategoryExecution(Category category,CategoryStateEnum categoryStateEnum){

    }

}
