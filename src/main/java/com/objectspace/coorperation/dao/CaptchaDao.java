package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.Captcha;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CaptchaDao {
    public Integer insertCaptcha(Captcha captcha);
}
