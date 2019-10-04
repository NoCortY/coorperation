package com.objectspace.coorperation.service.impl;

import com.objectspace.coorperation.dao.CaptchaDao;
import com.objectspace.coorperation.entity.Captcha;
import com.objectspace.coorperation.service.CaptchaService;
import com.objectspace.coorperation.util.EmailUtil;
import com.objectspace.coorperation.util.RedisUtil;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
* @Description: 验证码业务逻辑实现类
* @Author: NoCortY
* @Date: 2019/10/4
*/
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private CaptchaDao captchaDao;
    @Autowired
    private RedisUtil redisUtil;
    private Logger logger = LoggerFactory.getLogger(CaptchaServiceImpl.class);
    /**
     * @Description:  发送验证码
     * @Param: [captcha]
     * @return: boolean
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Override
    public boolean sendCaptcha(Captcha captcha) {
        if(captcha==null||captcha.getRecUserEmail()==null||"".equals(captcha.getRecUserEmail()))
            return false;
        // 生成六位随机数
        int randomCaptcha = new Random().nextInt(999999);
        try {
            EmailUtil.sendEmail(captcha.getRecUserEmail(),"万物-验证码", "尊敬的万物用户:\n" + "    您好！感谢您注册万物，您的邮箱验证码为:" + randomCaptcha + "，提示：请勿泄露邮箱验证码");
            captcha.setCode(String.valueOf(randomCaptcha));
            //将验证码存入缓存，时间为15分钟。
            redisUtil.set("captcha:"+captcha.getRecUserEmail(),captcha.getCode(),15*60);
            captchaDao.insertCaptcha(captcha);
        } catch (EmailException e) {
            logger.error("邮件验证码发送失败。");
            logger.error("异常信息:"+e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * @Description:检查redis中是否已经存了该邮箱的验证码
     * @Param: [userEmail]
     * @return: boolean 存在返回true，不存在返回false
     * @Author: NoCortY
     * @Date: 2019/10/3
     */
    @Override
    public boolean getCaptchaStatus(String userEmail) {
        if(userEmail==null||"".equals(userEmail))
            return false;
        String result = redisUtil.get("captcha:" + userEmail);
        if(result==null)
            return false;
        return true;
    }
}
