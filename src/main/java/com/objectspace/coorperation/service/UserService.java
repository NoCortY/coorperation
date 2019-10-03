package com.objectspace.coorperation.service;

import com.objectspace.coorperation.dto.UserExecution;
import com.objectspace.coorperation.entity.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface UserService {
    public UserExecution getUserByUserAccount(User user);
    public UserExecution addUser(User user, String captchaCode, CommonsMultipartFile userProfile);
    public UserExecution userLogin(User user,String captcha);
}
