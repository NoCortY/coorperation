package com.objectspace.coorperation.web.controller;

import com.objectspace.coorperation.config.ConstantValue;
import com.objectspace.coorperation.entity.Captcha;
import com.objectspace.coorperation.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/captchacontroller")
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;
    @RequestMapping("/getcaptcha")
    @ResponseBody
    public Map<String,Object> getCaptcha(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Captcha captcha = new Captcha();
        String recUserEmail = request.getParameter("userEmail");
        captcha.setRecUserEmail(recUserEmail);
        boolean flag = captchaService.sendCaptcha(captcha);
        if(flag){
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,true);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE,"发送成功");
        }else{
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE,"发送失败");
        }
        return modelMap;
    }
    @RequestMapping("/iscaptchaexist")
    @ResponseBody
    public Map<String,Object> isCaptchaExist(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        String userEmail = request.getParameter("userEmail");
        boolean captchaStatus = captchaService.getCaptchaStatus(userEmail);
        if(captchaStatus){
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,true);
        }else{
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,false);
        }
        return modelMap;
    }
}
