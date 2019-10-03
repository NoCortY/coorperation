package com.objectspace.coorperation.shiro;


import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
* @Description:  验证码拦截器
* @Author: Apiece
* @Date: 2019/10/3
*/

public class CaptchaValidateFilter extends AccessControlFilter {
    private String captchaParam = "captchaCode";    //验证码参数
    private String failureKeyAttribute = "shiroLoginFailure";   //验证失败后的属性名
    public String getCaptchaCode(ServletRequest request){
        return WebUtils.getCleanParam(request,getCaptchaParam());
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
        throws Exception{
        Session session = SecurityUtils.getSubject().getSession();  //获取正确验证码

        String captchaCode = getCaptchaCode(request);     //输入的验证码
        String validateCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);

        /**
         * 验证码是否单表提交
         */
        if(!"post".equalsIgnoreCase(httpServletRequest.getMethod())){
            return true;
        }

        /**
         * 输入验证码为空或错误时
         * 返回 false
         */
        if(captchaCode == null){
            return false;
        }   else if(validateCode !=null){
            captchaCode = captchaCode.toLowerCase();
            validateCode = validateCode.toLowerCase();
            if(!captchaCode.equals(validateCode)){
                return false;
            }
        }
        return true;
    }
    /**
     * 若验证码错误
     * 存储失败key属性
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletRequest.setAttribute("failurekeyAttribute","验证码错误");
        return true;
    }
    public String getCaptchaParam(){
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam){
        this.captchaParam = captchaParam;
    }
}
