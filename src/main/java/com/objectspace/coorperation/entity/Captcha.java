package com.objectspace.coorperation.entity;

import java.io.Serializable;

/**
* @Description: 邮件验证码实体类
* @Author: NoCortY
* @Date: 2019/10/3
*/
public class Captcha implements Serializable {
    private static final long serialVersionUID = -5147848042251679765L;
    private Integer id;
    private String code;
    private String recUserEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRecUserEmail() {
        return recUserEmail;
    }

    public void setRecUserEmail(String recUserEmail) {
        this.recUserEmail = recUserEmail;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", recUserEmail='" + recUserEmail + '\'' +
                '}';
    }

}
