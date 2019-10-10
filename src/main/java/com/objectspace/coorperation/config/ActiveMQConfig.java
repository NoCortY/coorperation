package com.objectspace.coorperation.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
/** 
* @Description: ActiveMQ配置类
* @Author: NoCortY
* @Date: 2019/10/10 
*/ 
@Configuration
@EnableJms
public class ActiveMQConfig {
    /**
    * @Description: 配置验证码消息队列bean
    * @Param: []
    * @return: javax.jms.Queue
    * @Author: NoCortY
    * @Date: 2019/10/10
    */
    @Bean("captchaQueue")
    public Queue captchaQueue(){
        return new ActiveMQQueue(ConstantValue.ACTIVEMQ_QUEUE_CAPTCHA);
    }
}
