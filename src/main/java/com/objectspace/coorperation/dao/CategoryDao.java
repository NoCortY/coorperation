package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
* @Description: 分类Dao
* @Author: NoCortY
* @Date: 2019/10/4
*/
@Mapper
public interface CategoryDao {
    /**
     * @Description: 查询分类列表
     * @Param: []
     * @return: List
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public List<Category> listCategory();
}
