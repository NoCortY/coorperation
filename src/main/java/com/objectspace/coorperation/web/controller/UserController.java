package com.objectspace.coorperation.web.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.objectspace.coorperation.config.ConstantValue;
import com.objectspace.coorperation.dto.UserExecution;
import com.objectspace.coorperation.entity.User;
import com.objectspace.coorperation.enums.UserStateEnum;
import com.objectspace.coorperation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/** 
* @Description: 
* @Author: Object
* @Date: 2019/10/2
*/
@Controller
@RequestMapping("/usercontroller")
public class UserController {
    @Autowired
    UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping(value="/registeruser",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> registerUser(HttpServletRequest request){
        //包含要返回的各种信息
        Map<String,Object> modelMap = new HashMap<String,Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        //创建文件接收器
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //创建SpringMVC文件对象
        CommonsMultipartFile userProfile = null;
        User user = null;
        UserExecution userExecution = null;
        String captchaCode = request.getParameter("captcha");
        try {
            //将前台传送的JSON串转换为User对象
            user = objectMapper.readValue(request.getParameter("userInfo"), User.class);
        } catch (JsonParseException e) {
            logger.error("前台传递的JSON串语法错误!");
            logger.error(e.getMessage());
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, "系统错误，请稍后再试");
            return modelMap;
        } catch (JsonMappingException e) {
            logger.error("JSON转换对象时映射失败!");
            logger.error(e.getMessage());
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, "系统错误，请稍后再试");
            return modelMap;
        } catch (IOException e) {
            logger.error("User.class读取失败");
            logger.error(e.getMessage());
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, "系统错误，请稍后再试");
            return modelMap;
        }
        if(commonsMultipartResolver.isMultipart(request)) {
            //如果文件接收器中含有文件，则将request转为MultipartRequest
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            userProfile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("userProfile");
        }
        userExecution = userService.addUser(user, captchaCode,userProfile);
        if(userExecution.getUserState()== UserStateEnum.REGISTERSUCCESS) {
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, true);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, "注册成功!");
        }else {
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, false);
            logger.debug(userExecution.getUserState().getStateInfo());
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, userExecution.getUserState().getStateInfo());
        }
        return modelMap;
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        UserExecution userExecution= null;
        String captcha = request.getParameter("captcha");
        try {
            user = objectMapper.readValue(request.getParameter("userInfo"),User.class);
        }  catch (JsonParseException e) {
            logger.error("前台传递的JSON串语法错误!");
            logger.error(e.getMessage());
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, "系统错误，请稍后再试");
            return modelMap;
        } catch (JsonMappingException e) {
            logger.error("JSON转换对象时映射失败!");
            logger.error(e.getMessage());
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, "系统错误，请稍后再试");
            return modelMap;
        } catch (IOException e) {
            logger.error("User.class读取失败");
            logger.error(e.getMessage());
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, "系统错误，请稍后再试");
            return modelMap;
        }
        userExecution = userService.userLogin(user,captcha);
        if(userExecution.getUserState() == UserStateEnum.LOGINSUCCESS) {
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, true);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, userExecution.getUserState().getStateInfo());
        }else {
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG, false);
            modelMap.put(ConstantValue.TO_FRONTEND_MASSAGE, userExecution.getUserState().getStateInfo());
        }
        return modelMap;
    }
}

