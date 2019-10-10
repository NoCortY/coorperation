package com.objectspace.coorperation.activemq;

import com.objectspace.coorperation.config.ConstantValue;
import com.objectspace.coorperation.entity.Captcha;
import com.objectspace.coorperation.service.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * @Description: ActiveMQ队列消费者
 * @Author: NoCortY
 * @Date: 2019/10/10
 */
@Component
public class QueueCunsumer {
    @Autowired
    private CaptchaService captchaService;
    Logger logger = LoggerFactory.getLogger(QueueCunsumer.class);
    /**
     * @Description: 消费验证码队列内容
     * @Param: [textMessage]
     * @return: void
     * @Author: NoCortY
     * @Date: 2019/10/10
     */
    @JmsListener(destination = ConstantValue.ACTIVEMQ_QUEUE_CAPTCHA)
    public void captchaReceive(Captcha objectMessage) throws JMSException {
        Captcha captcha =objectMessage;
        boolean flag = captchaService.sendCaptcha(captcha);
        if(flag){
            logger.info("消费者——发送验证码成功");
        }else{
            logger.error("消费者——发送验证码失败");
        }
    }
}
