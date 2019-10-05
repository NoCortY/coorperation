package com.objectspace.coorperation.service.impl;

import com.objectspace.coorperation.dao.CategoryDao;
import com.objectspace.coorperation.dto.CategoryExecution;
import com.objectspace.coorperation.entity.Category;
import com.objectspace.coorperation.enums.CategoryStateEnum;
import com.objectspace.coorperation.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: 分类业务逻辑——实现类
* @Author: NoCortY
* @Date: 2019/10/5
*/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    /**
     * @Description: 获取最高级分类列表
     * @Param: []
     * @return: com.objectspace.coorperation.dto.CategoryExecution
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    @Override
    public CategoryExecution getCategoryList() {
        CategoryExecution categoryExecution = null;
        List<Category> categoryList = null;
        try{
            categoryList = categoryDao.listCategory();
            logger.info("分类信息："+categoryList);
        }catch (Exception e){
            logger.error("获取分类信息异常");
            logger.error("异常信息："+e.getMessage());
            categoryExecution = new CategoryExecution(CategoryStateEnum.SYSTEM_ERROR);
            return categoryExecution;
        }
        if(categoryList==null||categoryList.size()<=0){
            categoryExecution = new CategoryExecution(CategoryStateEnum.CATEGORY_ISNULL);
            return categoryExecution;
        }else{
            categoryExecution = new CategoryExecution(categoryList,CategoryStateEnum.GETCATEGORYLIST_SUCCESS);
        }
        return categoryExecution;
    }
}
