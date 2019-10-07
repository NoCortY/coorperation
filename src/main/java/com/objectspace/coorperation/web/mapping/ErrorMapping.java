package com.objectspace.coorperation.web.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @Description: 错误界面映射器
* @Author: NoCortY
* @Date: 2019/10/7
*/
@Controller
@RequestMapping("/error")
public class ErrorMapping {
    @RequestMapping(value="/404",method= RequestMethod.GET)
    public String error404(){
        return "/error/404";
    }
    @RequestMapping(value="/403",method=RequestMethod.GET)
    public String error403(){
        return "/error/403";
    }
    @RequestMapping(value="/500",method = RequestMethod.GET)
    public String error500(){
        return "/error/500";
    }
}
