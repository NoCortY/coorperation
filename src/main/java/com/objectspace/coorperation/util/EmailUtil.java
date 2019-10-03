package com.objectspace.coorperation.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
* @Description: 原来是专用于发送邮件验证码的Util，现在用来发送任何邮件。
* @Author: Object
* @Date: 2019/10/3
*/
public class EmailUtil {
    private static Properties properties;
    private static HtmlEmail httpEmail;
    static {
        InputStream in = EmailUtil.class.getResourceAsStream("/config/config.properties");
        properties = new Properties();
        httpEmail = new HtmlEmail();
        try {
            properties.load(in);
            httpEmail.setHostName(properties.getProperty("emailHost"));
            httpEmail.setCharset("utf-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 发送验证码
     *
     * @param userEmail 用户邮箱
     * @return 发送的随机邮件验证码
     * @throws EmailException
     */
    public static synchronized void sendEmail(String userEmail,String emailTitle,String emailContent) throws EmailException {
        //设置邮箱名
        String emailName = properties.getProperty("emailName");
        emailName = emailName.substring(0, emailName.length() - 1);
        httpEmail.addTo(userEmail);
        httpEmail.setFrom(properties.getProperty("emailAddress"), emailName, "utf-8");
        httpEmail.setAuthentication(properties.getProperty("emailAddress"),
                properties.getProperty("emailAuthentication"));
        httpEmail.setSubject(emailTitle);
        httpEmail.setMsg(emailContent);
        httpEmail.send();
    }
}

