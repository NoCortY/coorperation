package com.objectspace.coorperation.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * @author NoCortY 发送邮件验证码工具
 */
public class EmailUtil {
    private static Properties properties;
    static {
        InputStream in = EmailUtil.class.getResourceAsStream("/email.properties");
        properties = new Properties();
        try {
            properties.load(in);
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
    public static int sendVerifyCodeEMail(String userEmail) throws EmailException {
        HtmlEmail httpEmail = new HtmlEmail();
        httpEmail.setHostName(properties.getProperty("emailHost"));
        httpEmail.setCharset("utf-8");
        // 生成六位随机数
        int randomVerifyCode = new Random().nextInt(999999);
        String emailName = properties.getProperty("emailName");
        emailName = emailName.substring(0, emailName.length() - 1);
        httpEmail.addTo(userEmail);
        httpEmail.setFrom(properties.getProperty("emailAddress"), emailName, "utf-8");
        httpEmail.setAuthentication(properties.getProperty("emailAddress"),
                properties.getProperty("emailAuthentication"));
        httpEmail.setSubject("注册验证信息");
        httpEmail.setMsg(
                "尊敬的Coorperation用户:\n" + "    您好！感谢您注册Coorperation，您的邮箱验证码为:" + randomVerifyCode + "，提示：请勿泄露邮箱验证码");
        httpEmail.send();

        return randomVerifyCode;
    }
}

