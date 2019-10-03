package com.objectspace.coorperation.service;

import com.objectspace.coorperation.entity.Captcha;

public interface CaptchaService {
    public boolean sendCaptcha(Captcha captcha);
    public boolean getCaptchaStatus(String userEmail);
}
