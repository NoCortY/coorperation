package com.objectspace.coorperation.service;

import com.objectspace.coorperation.entity.Captcha;
/**
* @Description: 验证码业务逻辑接口
* @Author: NoCortY
* @Date: 2019/10/4
*/
public interface CaptchaService {
    /**
     * @Description:  发送验证码
     * @Param: captcha
     * @return: 成功 or 失败
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public boolean sendCaptcha(Captcha captcha);
    /**
     * @Description:  查看是否已发送验证码到用户邮箱
     * @Param: userEmail 用户邮箱
     * @return: yes or no
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public boolean getCaptchaStatus(String userEmail);
}
