package com.objectspace.coorperation.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.objectspace.coorperation.activemq.QueueProduce;
import com.objectspace.coorperation.annotation.DefendRepeatRequest;
import com.objectspace.coorperation.config.ConstantValue;
import com.objectspace.coorperation.entity.Captcha;
import com.objectspace.coorperation.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
* @Description: 邮箱验证码控制器
* @Author: NoCortY
* @Date: 2019/10/4
*/
@Controller
@RequestMapping("/captchacontroller")
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private QueueProduce queueProduce;
    /**
     * @Description:  获取邮箱验证码
     * @Param: [request]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @RequestMapping(value = "/getcaptcha",method = RequestMethod.POST)
    @ResponseBody
    @DefendRepeatRequest
    public Map<String,Object> getCaptcha(HttpServletRequest request) throws JsonProcessingException {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Captcha captcha = new Captcha();
        String recUserEmail = request.getParameter("userEmail");
        captcha.setRecUserEmail(recUserEmail);
        /*
        2019.10.10改为发送给消息队列,由消息队列的消费者来完成发送邮件的操作。;
        //调用业务逻辑 发送验证码
        boolean flag = captchaService.sendCaptcha(captcha);*/
        boolean flag = queueProduce.captchaQueueProduce(captcha);
        if(flag){
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,true);
            modelMap.put(ConstantValue.TO_FRONTEND_MESSAGE,"发送成功");
        }else{
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,false);
            modelMap.put(ConstantValue.TO_FRONTEND_MESSAGE,"发送失败,请联系管理员");
        }
        return modelMap;
    }
    /**
     * @Description:  查看验证码是否已经发送
     * @Param: [request]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @RequestMapping(value = "/iscaptchaexist",method = RequestMethod.POST)
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
