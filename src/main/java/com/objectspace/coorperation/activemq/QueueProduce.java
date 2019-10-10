package com.objectspace.coorperation.activemq;

import com.objectspace.coorperation.entity.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
/**
* @Description:  MQ生产者
* @Author: NoCortY
* @Date: 2019/10/10
*/
@Component
public class QueueProduce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    @Qualifier("captchaQueue")
    private Queue captchaQueue;
    Logger logger = LoggerFactory.getLogger(QueueProduce.class);
    /**
     * @Description: 发送消息
     * @Param: [message]
     * @return: void
     * @Author: NoCortY
     * @Date: 2019/10/10
     */
    public boolean captchaQueueProduce(Captcha objectMessage){
        try{
            jmsMessagingTemplate.convertAndSend(captchaQueue,objectMessage);
        }catch (Exception e){
            logger.error("发送验证码-消息队列异常");
            logger.error("异常信息:"+e.getMessage());
            return false;
        }
        return true;
    }
}
