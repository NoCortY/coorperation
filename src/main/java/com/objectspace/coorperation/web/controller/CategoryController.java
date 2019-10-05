package com.objectspace.coorperation.web.controller;

import com.objectspace.coorperation.config.ConstantValue;
import com.objectspace.coorperation.dto.CategoryExecution;
import com.objectspace.coorperation.entity.Category;
import com.objectspace.coorperation.enums.CategoryStateEnum;
import com.objectspace.coorperation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: 分类控制器
* @Author: NoCortY
* @Date: 2019/10/5
*/
@Controller
@RequestMapping("/categorycontroller")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * @Description: 获取最高级分类列表
     * @Param: [request]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: NoCortY
     * @Date: 2019/10/5
     */
    @RequestMapping(value = "/getallcategory",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllCategory(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        CategoryExecution categoryExecution = categoryService.getCategoryList();
        List<Category> categoryList = categoryExecution.getCategoryList();
        if(!categoryExecution.getCategoryStateEnum().getState().equals(CategoryStateEnum.GETCATEGORYLIST_SUCCESS.getState())){
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE,categoryExecution.getCategoryStateEnum().getStateInfo());
        }else{
            modelMap.put("categoryList",categoryList);
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,true);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE,categoryExecution.getCategoryStateEnum().getStateInfo());
        }
        return modelMap;
    }
}
