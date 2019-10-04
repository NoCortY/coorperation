package com.objectspace.coorperation.service;

import com.objectspace.coorperation.dto.UserExecution;
import com.objectspace.coorperation.entity.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
* @Description: 用户业务逻辑接口
* @Author: NoCortY
* @Date: 2019/10/4
*/
public interface UserService {
    /**
     * @Description: 通过用户名获取用户信息
     * @Param:  用户对象
     * @return: 用户DTO对象
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public UserExecution getUserByUserName(User user);
    /**
     * @Description:  添加一个用户
     * @Param: [user,captchaCode,userProfile]
     * @return: 用户DTO对象
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public UserExecution addUser(User user, String captchaCode, CommonsMultipartFile userProfile);
    /**
     * @Description:  用户登录
     * @Param:  [user,captcha]
     * @return: 用户DTO对象
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public UserExecution userLogin(User user,String captcha);
}
