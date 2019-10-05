package com.objectspace.coorperation.service;

import com.objectspace.coorperation.dto.CategoryExecution;

/**
* @Description: 分类 业务逻辑接口
* @Author: NoCortY
* @Date: 2019/10/4
*/
public interface CategoryService {
    /**
     * @Description: 获取最高级分类列表
     * @Param:
     * @return:
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    public CategoryExecution getCategoryList();
}
