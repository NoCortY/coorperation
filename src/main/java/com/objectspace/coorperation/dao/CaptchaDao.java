package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.Captcha;
import org.apache.ibatis.annotations.Mapper;

/**
* @Description: 邮件验证码DAO
* @Author: NoCortY
* @Date: 2019/10/4
*/
@Mapper
public interface CaptchaDao {
    /**
     * @Description: 将发出的邮件验证码记录置入数据仓库
     * @Param: Captcha 验证码对象
     * @return: 影响的行数
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public Integer insertCaptcha(Captcha captcha);
}
