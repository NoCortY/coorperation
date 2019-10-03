package com.objectspace.coorperation.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.awt.*;

/**
* @Description: Kaptcha的配置类
* @Author: Apiece
* @Date: 2019/10/3
*/

@Component
public class KaptchaConfig {
    @Bean
    public ServletRegistrationBean<KaptchaServlet> kaptchaServlet() {
        ServletRegistrationBean<KaptchaServlet> registrationBean = new ServletRegistrationBean<>(
                new KaptchaServlet(), "/captcha/kaptcha.jpg");
        registrationBean.addInitParameter(Constants.KAPTCHA_SESSION_CONFIG_KEY, Constants.KAPTCHA_SESSION_KEY);
        //图形宽度
        registrationBean.addInitParameter(Constants.KAPTCHA_IMAGE_WIDTH, "200");
        //图形高度
        registrationBean.addInitParameter(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
        //有边框
        registrationBean.addInitParameter(Constants.KAPTCHA_BORDER, "yes");
        //边框粗细度
        registrationBean.addInitParameter(Constants.KAPTCHA_BORDER_THICKNESS, "1");
/*      //背景色调
        registrationBean.addInitParameter(Constants.KAPTCHA_BACKGROUND_CLR_FROM, Color.);
        //背景色调
        registrationBean.addInitParameter(Constants.KAPTCHA_BACKGROUND_CLR_TO, "");*/
        //噪点颜色
        registrationBean.addInitParameter(Constants.KAPTCHA_NOISE_COLOR, "blue");
        //文字大小
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");
        //文字颜色
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "red");
        //文字长度
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        //字符间距
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "4");

        return registrationBean;
    }
}
